package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class getAPITest extends TestBase {

	TestBase testBase;
	String url;
	String serviceURL;
	String actURL;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		url = prop.getProperty("URL");
		serviceURL = prop.getProperty("ServiceURL");

		actURL = url + serviceURL;

	}

	@Test
	public void getApiTest() throws ClientProtocolException, IOException {

		restClient = new RestClient();
		closeableHttpResponse = restClient.getMethod(actURL);

		// Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code of response --> " + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		// Response
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("JSON Output Response --> " + responseJson);
		
		//single value assertion
		
		String perPage = TestUtil.getValueByJpath(responseJson, "/per_page"); // JSON Object String
		
		//getting value from JSON Array
		
		String last_name = TestUtil.getValueByJpath(responseJson, "/data[0]/last_name"); //getting the particular object

		// Headers
		Header[] responseHeaderArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : responseHeaderArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

	}
	
	@Test
	public void getApiTestWithHeaders() throws ClientProtocolException, IOException {

		restClient = new RestClient();
		
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-type", "application/JSON");
		headerMap.put("authorization", "asdfasdwqfdaz13e3rwe");
		
		closeableHttpResponse = restClient.getMethod(actURL, headerMap);

		// Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code of response --> " + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		// Response
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("JSON Output Response --> " + responseJson);
		
		//single value assertion
		
		String perPage = TestUtil.getValueByJpath(responseJson, "/per_page"); // JSON Object String
		
		//getting value from JSON Array
		
		String last_name = TestUtil.getValueByJpath(responseJson, "/data[0]/last_name"); //getting the particular object

		// Headers
		Header[] responseHeaderArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : responseHeaderArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

	}
}
