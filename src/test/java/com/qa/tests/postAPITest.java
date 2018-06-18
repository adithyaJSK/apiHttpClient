package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.requestPayload;

public class postAPITest extends TestBase {

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
	public void postAPITests() throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-type", "application/JSON");
		headerMap.put("authorization", "asdfasdwqfdaz13e3rwe");

		// jackson API utility needed to convert pojo class to JSON Object
		ObjectMapper mapper = new ObjectMapper();
		requestPayload payload = new requestPayload("Adithya", "Bengaluru");

		// object to json file conversion
		mapper.writeValue(new File(
				"D:\\Mission SDET\\selenium\\Projects\\APIAutomationHttp\\src\\main\\java\\com\\qa\\data\\request.json"),
				payload);
		
		// convert to json in string
		String reqPayload = mapper.writeValueAsString(payload);

		closeableHttpResponse = restClient.postMethod(actURL, reqPayload, headerMap);
		
		//1.gets Status code from response
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		
		//2. JSON string 
		String response = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		JSONObject jsonResponse = new JSONObject(response);
		
		//3. json to Java Object
		requestPayload reqPay = mapper.readValue(response, requestPayload.class);
		System.out.println(payload.getName().equals(reqPay.getName()));
	}
}
