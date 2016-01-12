package com.sw.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sw.models.PeopleApi;
import com.sw.models.RootApi;

@Component
public class ApiDAO<T> {

	public RootApi getApiSampleHttpClient() throws ClientProtocolException, IOException {
		String url = "http://swapi.co/api/";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		request.addHeader("User-Agent", "Mozilla/5.0");
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(result.toString(), RootApi.class);
	}

	public RootApi getRootApiSampleRestTemplate() {
		String URI = "http://swapi.co/api/";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(getMessageConverters());
		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "Mozilla/5.0");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange(URI, HttpMethod.GET, entity, RootApi.class).getBody();
	}
	
	
	@SuppressWarnings("unchecked")
	public T getApiSampleRestTemplate(String url, Class<?> clazz) {
		String URI = url;
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(getMessageConverters());
		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "Mozilla/5.0");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return (T) restTemplate.exchange(URI, HttpMethod.GET, entity, clazz).getBody();
	}

	private List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJackson2HttpMessageConverter());
		return converters;
	}
	
	
	public PeopleApi getPeopleApi(String id) {
		return (PeopleApi) getApiSampleRestTemplate("http://swapi.co/api/people/"+id, PeopleApi.class);
	}
}
