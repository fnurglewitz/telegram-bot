package it.pwned.telegram.samplebot.handler;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.MessageHandler;
import it.pwned.telegram.bot.TelegramBot;
import it.pwned.telegram.bot.api.type.ChatAction;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.SendAction;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.command.BotCommand;
import it.pwned.telegram.samplebot.handler.ImgurHandler.ImgurApi.GalleryImage;
import it.pwned.telegram.samplebot.handler.ImgurHandler.ImgurApi.ImgurResponse;

@Order(value=3)
@BotCommand(command = "/imgur", description = "Prints avaible imgur commands")
public class ImgurHandler extends MessageHandler {

	static class ImgurApi {

		// nested class abuse
		static class Comment {

			public final Integer id;
			public final String image_id;
			public final String comment;
			public final String author;
			public final Integer author_id;
			public final Boolean on_album;
			public final String album_cover;
			public final Integer ups;
			public final Integer downs;
			public final Float points;
			public final Integer datetime;
			public final Integer parent_id;
			public final Boolean deleted;
			public final String vote;
			public final Comment[] children;

			public Comment(@JsonProperty("id") Integer id, @JsonProperty("image_id") String image_id,
					@JsonProperty("comment") String comment, @JsonProperty("author") String author,
					@JsonProperty("author_id") Integer author_id, @JsonProperty("on_album") Boolean on_album,
					@JsonProperty("album_cover") String album_cover, @JsonProperty("ups") Integer ups,
					@JsonProperty("downs") Integer downs, @JsonProperty("points") Float points,
					@JsonProperty("datetime") Integer datetime, @JsonProperty("parent_id") Integer parent_id,
					@JsonProperty("deleted") Boolean deleted, @JsonProperty("vote") String vote,
					@JsonProperty("children") Comment[] children) {
				this.id = id;
				this.image_id = image_id;
				this.comment = comment;
				this.author = author;
				this.author_id = author_id;
				this.on_album = on_album;
				this.album_cover = album_cover;
				this.ups = ups;
				this.downs = downs;
				this.points = points;
				this.datetime = datetime;
				this.parent_id = parent_id;
				this.deleted = deleted;
				this.vote = vote;
				this.children = children;
			}

		}

		static class GalleryImage {

			public final String id;
			public final String title;
			public final String description;
			public final BigInteger datetime;
			public final String type;
			public final Boolean animated;
			public final Integer width;
			public final Integer height;
			public final Integer size;
			public final Integer views;
			public final BigInteger bandwidth;
			public final String deletehash;
			public final String link;
			public final String gifv;
			public final String mp4;
			public final String webm;
			public final Boolean looping;
			public final String vote;
			public final Boolean favorite;
			public final Boolean nsfw;
			public final Integer comment_count;
			public final Comment[] comment_preview;
			public final String topic;
			public final BigInteger topic_id;
			public final String section;
			public final String account_url;
			public final BigInteger account_id;
			public final Integer ups;
			public final Integer downs;
			public final Integer score;
			public final Boolean is_album;

			public GalleryImage(@JsonProperty("id") String id, @JsonProperty("title") String title,
					@JsonProperty("description") String description, @JsonProperty("datetime") BigInteger datetime,
					@JsonProperty("type") String type, @JsonProperty("animated") Boolean animated,
					@JsonProperty("width") Integer width, @JsonProperty("height") Integer height,
					@JsonProperty("size") Integer size, @JsonProperty("views") Integer views,
					@JsonProperty("bandwidth") BigInteger bandwidth, @JsonProperty("deletehash") String deletehash,
					@JsonProperty("link") String link, @JsonProperty("gifv") String gifv, @JsonProperty("mp4") String mp4,
					@JsonProperty("webm") String webm, @JsonProperty("looping") Boolean looping,
					@JsonProperty("vote") String vote, @JsonProperty("favorite") Boolean favorite,
					@JsonProperty("nsfw") Boolean nsfw, @JsonProperty("comment_count") Integer comment_count,
					@JsonProperty("comment_preview") Comment[] comment_preview, @JsonProperty("topic") String topic,
					@JsonProperty("topic_id") BigInteger topic_id, @JsonProperty("section") String section,
					@JsonProperty("account_url") String account_url, @JsonProperty("account_id") BigInteger account_id,
					@JsonProperty("ups") Integer ups, @JsonProperty("downs") Integer downs, @JsonProperty("score") Integer score,
					@JsonProperty("is_album") Boolean is_album) {
				this.id = id;
				this.title = title;
				this.description = description;
				this.datetime = datetime;
				this.type = type;
				this.animated = animated;
				this.width = width;
				this.height = height;
				this.size = size;
				this.views = views;
				this.bandwidth = bandwidth;
				this.deletehash = deletehash;
				this.link = link;
				this.gifv = gifv;
				this.mp4 = mp4;
				this.webm = webm;
				this.looping = looping;
				this.vote = vote;
				this.favorite = favorite;
				this.nsfw = nsfw;
				this.comment_count = comment_count;
				this.comment_preview = comment_preview;
				this.topic = topic;
				this.topic_id = topic_id;
				this.section = section;
				this.account_url = account_url;
				this.account_id = account_id;
				this.ups = ups;
				this.downs = downs;
				this.score = score;
				this.is_album = is_album;
			}

		}

		static class ImgurResponse {
			public final GalleryImage[] data;
			public final Boolean success;
			public final Integer status;

			public ImgurResponse(@JsonProperty("data") GalleryImage[] data, @JsonProperty("success") Boolean success,
					@JsonProperty("status") Integer status) {
				this.data = data;
				this.success = success;
				this.status = status;
			}

		}

		private final String client_id;
		private RestTemplate rest_template;
		private final UriTemplate service_uri = new UriTemplate(
				"https://api.imgur.com/3/gallery/r/{subreddit}/{sort}/{window}/{page}");

		public ImgurApi(String client_id) {
			this.client_id = client_id;
			this.rest_template = new RestTemplate();
		}

		public ImgurResponse getSubredditPhotosByPage(String subreddit, Integer page, String sort, String window) {

			if (page == null)
				page = 0;

			MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Client-ID " + this.client_id);

			Map<String, String> uriVariables = new HashMap<String, String>();
			uriVariables.put("subreddit", subreddit);
			uriVariables.put("sort", sort);
			uriVariables.put("window", window);
			uriVariables.put("page", page.toString());

			HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);

			ImgurResponse res = null;

			try {
				res = rest_template.exchange(service_uri.expand(uriVariables), HttpMethod.GET, httpEntity,
						new ParameterizedTypeReference<ImgurResponse>() {
						}).getBody();

			} catch (RestClientException e) {
				int local_status = -1;
				if (e instanceof HttpClientErrorException) {
					local_status = ((HttpClientErrorException) e).getStatusCode().value();
				}

				res = new ImgurResponse(new GalleryImage[0], false, local_status);
			}

			return res;
		}
	}

	private static final Logger log = LoggerFactory.getLogger(ImgurHandler.class);
	private ImgurApi api;
	private final Random rand;
	private final Map<String, String[]> subreddits;
	private String avaible_cmds;

	public ImgurHandler(TelegramBot bot, BlockingQueue<Message> message_queue) {
		super(bot, message_queue);

		this.subreddits = new HashMap<String, String[]>();
		this.rand = new Random();
	}

	@Autowired
	public void setImgurApi(@Value("${imgur.client-id}") String client_id) {
		this.api = new ImgurApi(client_id);
	}

	@Autowired
	public void setCommands(@Value("${imgur.commands-file}") String file) {
		log.info(String.format("Loading imgur commands from file: %s", file));

		StringBuilder sb = new StringBuilder("Avaible imgur commands:\n");

		try (Stream<String> lines = Files.lines(Paths.get(file))) {
			for (String line : (Iterable<String>) lines::iterator) {
				String[] splitted = line.split(",");
				sb.append(String.format("/%s : %s\n", splitted[0], splitted[1]));
				subreddits.put(String.format("/%s", splitted[0]), Arrays.copyOfRange(splitted, 2, splitted.length));
				bot.registerCommand(String.format("/%s", splitted[0]), splitted[1], this);
			}
		} catch (IOException e) {
			e.printStackTrace(); // shit happens
		}

		this.avaible_cmds = sb.toString();

		log.info(this.avaible_cmds);
	}

	@Override
	protected boolean processMessage(Message m) {

		if (m.is_command && "/imgur".equals(m.text)) {
			try {
				bot.api.sendMessage(m.chat.id, this.avaible_cmds, null, null, null, null);
			} catch (TelegramBotApiException e) {
			}
		} else {

			final String[] array_ref = subreddits.get(m.command);

			bot.submitToExecutor(() -> {
				String subreddit = array_ref[new Random().nextInt(array_ref.length)];

				try {

					bot.api.sendChatAction(m.chat.id, ChatAction.upload_photo);

					try {
						int retry = 5;
						ImgurResponse gi = null;
						do {
							gi = api.getSubredditPhotosByPage(subreddit, rand.nextInt(101), "time", "all");
							if (gi != null && gi.success && gi.data.length > 0)
								retry = -1;
							else
								retry--;
						} while (retry >= 0);

						if (gi != null && gi.success && gi.data.length > 0) {
							GalleryImage rand_img = gi.data[rand.nextInt(gi.data.length)];

							SendAction sa = SendAction.getSendActionFromMimeType(rand_img.type);

							switch (sa) {
							case Photo:
								bot.api.sendPhoto(m.chat.id, new UrlResource(rand_img.link), rand_img.title, null, null);
								break;
							default:
								bot.api.sendDocument(m.chat.id, new UrlResource(rand_img.link), null, null);
							}

						} else
							bot.api.sendMessage(m.chat.id, "Error while fetching the image.", null, null, null, null);

					} catch (Exception e) {
						log.error("Error while fetching the image.", e);
						bot.api.sendMessage(m.chat.id, "Error while fetching the image.", null, null, null, null);
					}

				} catch (TelegramBotApiException ae) {

				}

			});

		}

		return true;
	}

}
