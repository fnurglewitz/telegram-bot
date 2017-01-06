package it.pwned.telegram.bot.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import it.pwned.telegram.bot.api.rest.log.LoggingRequestInterceptor;
import it.pwned.telegram.bot.api.type.MultipartDataEntityHttpMessageConverter;

public final class DefaultConfig {

	public static RestTemplate defaultRestTemplate() {
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

		/*
		 * TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String
		 * authType) -> true;
		 * 
		 * SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null,
		 * acceptingTrustStrategy).build();
		 * 
		 * SSLConnectionSocketFactory csf = new
		 * SSLConnectionSocketFactory(sslContext);
		 * 
		 * CloseableHttpClient httpClient =
		 * HttpClients.custom().setSSLSocketFactory(csf).build();
		 */

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		// requestFactory.setHttpClient(httpClient);
		requestFactory.setConnectTimeout(10000);

		// the read timeout must be set to 0, otherwise it will interfere with the
		// long polling on the GetUpdates call
		requestFactory.setReadTimeout(0);

		ClientHttpRequestInterceptor ri = new LoggingRequestInterceptor();
		List<ClientHttpRequestInterceptor> ris = new ArrayList<ClientHttpRequestInterceptor>();
		ris.add(ri);

		rest.setInterceptors(ris);
		rest.setRequestFactory(requestFactory);

		return rest;
	}

}
