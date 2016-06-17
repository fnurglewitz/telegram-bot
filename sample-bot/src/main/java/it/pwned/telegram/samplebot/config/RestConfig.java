package it.pwned.telegram.samplebot.config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import it.pwned.telegram.bot.api.log.LoggingRequestInterceptor;
import it.pwned.telegram.bot.api.type.ChatId;

@Configuration
public class RestConfig {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate rest = new RestTemplate();

		SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) rest.getRequestFactory();

		rf.setConnectTimeout(10000);
		rf.setReadTimeout(10000);

		List<HttpMessageConverter<?>> lc = new LinkedList<HttpMessageConverter<?>>();
		lc.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		lc.add(new ByteArrayHttpMessageConverter());
		lc.add(new ResourceHttpMessageConverter());
		lc.add(new MappingJackson2HttpMessageConverter());
		lc.add(new HttpMessageConverter<ChatId>() {

			@Override
			public boolean canRead(Class<?> arg0, MediaType arg1) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean canWrite(Class<?> arg0, MediaType arg1) {
				return arg0 == ChatId.class;
			}

			@Override
			public List<MediaType> getSupportedMediaTypes() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ChatId read(Class<? extends ChatId> arg0, HttpInputMessage arg1)
					throws IOException, HttpMessageNotReadableException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void write(ChatId arg0, MediaType arg1, HttpOutputMessage arg2)
					throws IOException, HttpMessageNotWritableException {
				arg2.getBody().write(arg0.toString().getBytes());
			}
		});

		for (HttpMessageConverter<?> c : rest.getMessageConverters()) {
			if (c instanceof FormHttpMessageConverter) {
				FormHttpMessageConverter fc = (FormHttpMessageConverter) c;
				fc.setPartConverters(lc);
			}
		}

		for (HttpMessageConverter<?> c : rest.getMessageConverters()) {
			if (c instanceof FormHttpMessageConverter) {
				FormHttpMessageConverter fc = (FormHttpMessageConverter) c;
				fc.setPartConverters(lc);
			}
		}

		ClientHttpRequestInterceptor ri = new LoggingRequestInterceptor();
		List<ClientHttpRequestInterceptor> ris = new ArrayList<ClientHttpRequestInterceptor>();
		ris.add(ri);
		rest.setInterceptors(ris);
		rest.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

		return rest;
	}

}
