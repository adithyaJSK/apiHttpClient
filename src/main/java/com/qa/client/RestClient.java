package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// GET METHOD WITHOUT HEADERS

	public CloseableHttpResponse getMethod(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault(); // create http connection
		HttpGet httpget = new HttpGet(url); // get request
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); // hit the GET url

		return closeableHttpResponse;

	}

	// GET METHOD WITH HEADERS

	public CloseableHttpResponse getMethod(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault(); // create http connection
		HttpGet httpget = new HttpGet(url); // get request
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); // hit the GET url

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {

			httpget.addHeader(entry.getKey(), entry.getValue());
		}

		return closeableHttpResponse;

	}

	// POST METHOD WITH HEADERS

	public CloseableHttpResponse postMethod(String url, String entityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url); // http post request
		httpPost.setEntity(new StringEntity(entityString)); // send request payload

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {

			httpPost.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
		return closeableHttpResponse;
	}
}
