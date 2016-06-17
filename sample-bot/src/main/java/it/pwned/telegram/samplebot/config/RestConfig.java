package it.pwned.telegram.samplebot.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import it.pwned.telegram.bot.api.log.LoggingRequestInterceptor;
import it.pwned.telegram.bot.api.type.MultipartDataEntityHttpMessageConverter;

@Configuration
public class RestConfig {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate rest = new RestTemplate();

		FormHttpMessageConverter fc = null;

		for (HttpMessageConverter<?> c : rest.getMessageConverters()) {
			if (c instanceof FormHttpMessageConverter) {
				fc = (FormHttpMessageConverter) c;
			}
		}

		List<HttpMessageConverter<?>> lc = new LinkedList<HttpMessageConverter<?>>();

		lc.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		lc.add(new ByteArrayHttpMessageConverter());
		lc.add(new ResourceHttpMessageConverter());
		lc.add(new MultipartDataEntityHttpMessageConverter());
		lc.add(new MappingJackson2HttpMessageConverter());

		fc.setPartConverters(lc);

		((SimpleClientHttpRequestFactory) rest.getRequestFactory()).setConnectTimeout(10000);
		((SimpleClientHttpRequestFactory) rest.getRequestFactory()).setReadTimeout(10000);

		ClientHttpRequestInterceptor ri = new LoggingRequestInterceptor();
		List<ClientHttpRequestInterceptor> ris = new ArrayList<ClientHttpRequestInterceptor>();
		ris.add(ri);
		rest.setInterceptors(ris);
		rest.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

		return rest;
	}

}
