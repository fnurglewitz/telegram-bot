package it.pwned.telegram.bot.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;

import it.pwned.telegram.bot.api.method.AnswerCallbackQuery;
import it.pwned.telegram.bot.api.method.ForwardMessage;
import it.pwned.telegram.bot.api.method.GetChat;
import it.pwned.telegram.bot.api.method.GetChatAdministrators;
import it.pwned.telegram.bot.api.method.GetChatMember;
import it.pwned.telegram.bot.api.method.GetChatMembersCount;
import it.pwned.telegram.bot.api.method.GetFile;
import it.pwned.telegram.bot.api.method.GetMe;
import it.pwned.telegram.bot.api.method.GetUpdates;
import it.pwned.telegram.bot.api.method.GetUserProfilePhotos;
import it.pwned.telegram.bot.api.method.KickChatMember;
import it.pwned.telegram.bot.api.method.LeaveChat;
import it.pwned.telegram.bot.api.method.SendAudio;
import it.pwned.telegram.bot.api.method.SendChatAction;
import it.pwned.telegram.bot.api.method.SendContact;
import it.pwned.telegram.bot.api.method.SendDocument;
import it.pwned.telegram.bot.api.method.SendLocation;
import it.pwned.telegram.bot.api.method.SendMessage;
import it.pwned.telegram.bot.api.method.SendPhoto;
import it.pwned.telegram.bot.api.method.SendSticker;
import it.pwned.telegram.bot.api.method.SendVenue;
import it.pwned.telegram.bot.api.method.SendVideo;
import it.pwned.telegram.bot.api.method.SendVoice;
import it.pwned.telegram.bot.api.method.UnbanChatMember;
import it.pwned.telegram.bot.api.method.game.GetGameHighScores;
import it.pwned.telegram.bot.api.method.game.SendGame;
import it.pwned.telegram.bot.api.method.game.SetGameScore;
import it.pwned.telegram.bot.api.method.inline.AnswerInlineQuery;
import it.pwned.telegram.bot.api.method.update.EditMessageCaption;
import it.pwned.telegram.bot.api.method.update.EditMessageReplyMarkup;
import it.pwned.telegram.bot.api.method.update.EditMessageText;
import it.pwned.telegram.bot.api.type.BooleanOrMessage;
import it.pwned.telegram.bot.api.type.Chat;
import it.pwned.telegram.bot.api.type.ChatMember;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.Response;
import it.pwned.telegram.bot.api.type.TelegramFile;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.api.type.UserProfilePhotos;
import it.pwned.telegram.bot.api.type.game.GameHighScore;

public final class ApiMethodReturnTypes {

	/*
	 * For anyone wondering why this map exists:
	 * http://stackoverflow.com/questions/21987295/using-spring-resttemplate-in-
	 * generic-method-with-generic-parameter
	 * 
	 * ParameterizedTypeReference uses Class#getGenericSuperclass which states:
	 * Returns the Type representing the direct superclass of the entity (class,
	 * interface, primitive type or void) represented by this Class. If the
	 * superclass is a parameterized type, the Type object returned must
	 * accurately reflect the actual type parameters used in the source code.
	 */
	@SuppressWarnings("rawtypes")
	public static final Map<Class, ParameterizedTypeReference> typeRefs = initializeParameterizedTypeReferences();

	@SuppressWarnings("rawtypes")
	private static Map<Class, ParameterizedTypeReference> initializeParameterizedTypeReferences() {

		Map<Class<?>, ParameterizedTypeReference> tmp = new HashMap<Class<?>, ParameterizedTypeReference>();

		tmp.put(GetUpdates.class, new ParameterizedTypeReference<Response<List<Update>>>() {
		});

		tmp.put(GetMe.class, new ParameterizedTypeReference<Response<User>>() {
		});

		tmp.put(SendMessage.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(ForwardMessage.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(SendPhoto.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(SendAudio.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(SendDocument.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(SendSticker.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(SendVideo.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(SendVoice.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(SendLocation.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(SendVenue.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(SendContact.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(SendChatAction.class, new ParameterizedTypeReference<Response<Boolean>>() {
		});

		tmp.put(GetUserProfilePhotos.class, new ParameterizedTypeReference<Response<UserProfilePhotos>>() {
		});

		tmp.put(GetFile.class, new ParameterizedTypeReference<Response<TelegramFile>>() {
		});

		tmp.put(KickChatMember.class, new ParameterizedTypeReference<Response<Boolean>>() {
		});

		tmp.put(LeaveChat.class, new ParameterizedTypeReference<Response<Boolean>>() {
		});

		tmp.put(UnbanChatMember.class, new ParameterizedTypeReference<Response<Boolean>>() {
		});

		tmp.put(GetChat.class, new ParameterizedTypeReference<Response<Chat>>() {
		});

		tmp.put(GetChatAdministrators.class, new ParameterizedTypeReference<Response<List<ChatMember>>>() {
		});

		tmp.put(GetChatMembersCount.class, new ParameterizedTypeReference<Response<Integer>>() {
		});

		tmp.put(GetChatMember.class, new ParameterizedTypeReference<Response<ChatMember>>() {
		});

		tmp.put(AnswerCallbackQuery.class, new ParameterizedTypeReference<Response<Boolean>>() {
		});

		tmp.put(EditMessageText.class, new ParameterizedTypeReference<Response<BooleanOrMessage>>() {
		});

		tmp.put(EditMessageCaption.class, new ParameterizedTypeReference<Response<BooleanOrMessage>>() {
		});

		tmp.put(EditMessageReplyMarkup.class, new ParameterizedTypeReference<Response<BooleanOrMessage>>() {
		});

		tmp.put(AnswerInlineQuery.class, new ParameterizedTypeReference<Response<Boolean>>() {
		});

		tmp.put(SendGame.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(SetGameScore.class, new ParameterizedTypeReference<Response<BooleanOrMessage>>() {
		});

		tmp.put(GetGameHighScores.class, new ParameterizedTypeReference<Response<List<GameHighScore>>>() {
		});

		return Collections.unmodifiableMap(tmp);
	}

}
