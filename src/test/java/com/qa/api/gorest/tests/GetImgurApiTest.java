package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.Token;

import io.restassured.response.Response;

public class GetImgurApiTest {

	Map<Object, Object> tokenMap;
	String accessToken;
	String accountUsername;
	String refreshToken;

	@BeforeMethod
	public void setUp() {
		tokenMap = Token.getAccessToken();
		accessToken = tokenMap.get("access_token").toString();
		accountUsername = tokenMap.get("account_username").toString();
		refreshToken = tokenMap.get("refresh_token").toString();
	}

	@Test
	public void getAccountBlockStatusTest() {

		Map<String, String> authTokenMap = Token.getAuthToken();
		Response response = RestClient.doGet(null, "https://api.imgur.com", "/3/account/" + accountUsername + "/block",
				authTokenMap, null, true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void getAccountImagesTest() {

		Map<String, String> authTokenMap = Token.getAuthToken(); 
		Response response = RestClient.doGet(null, "https://api.imgur.com", "/3/account/me/images",
				authTokenMap, null, true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void uploadImagePostApiTest() {
		
		Map<String, String> clientIdMap = Token.getClientId();
		Map<String, String> formMap = new HashMap<String, String>();
		
		Response response = RestClient.doPost("multipart", "https://api.imgur.com", "/3/upload", clientIdMap, null, true, formMap);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
	}

}
