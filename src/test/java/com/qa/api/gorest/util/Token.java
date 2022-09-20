package com.qa.api.gorest.util;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import io.restassured.path.json.JsonPath;

public class Token {

	public static Map<Object, Object> appTokenMap;
	public static Map<String, String> tokenMap = new HashMap<String, String>();
	public static String clientId = "546c25a59c58ad7";

	public static Map<Object, Object> getAccessToken() {

		Map<String, String> formParams = new HashMap<String, String>();
		formParams.put("refresh_token", "c4e597d53a1b9a77051f557ab2aadbc319353c37");
		formParams.put("client_id", "00155a9ca3475d6");
		formParams.put("client_secret", "9ad1b6b20488a0d68685016681366c9b1e4563f1");
		formParams.put("grant_type", "refresh_token");

		JsonPath tokenJson = given().formParams(formParams).when().post("https://api.imgur.com/oauth2/token").then()
				.extract().jsonPath();

		System.out.println(tokenJson.getMap(""));
		appTokenMap = tokenJson.getMap("");
		return appTokenMap;
	}

	public static Map<String, String> getAuthToken() {
		String authToken = appTokenMap.get("access_token").toString();
		System.out.println("Auth Token ===> " + authToken);
		tokenMap.put("Authorization", "Bearer " + authToken);
		return tokenMap;
	}
	

	public static Map<String, String> getClientId() {
		System.out.println("Client ID ===> " + clientId);
		tokenMap.put("Authorization", "Client-ID " + clientId);
		return tokenMap;
	}

}
