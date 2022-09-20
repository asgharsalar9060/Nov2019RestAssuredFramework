package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Epic("This is a get user call epic")
@Feature("This is get user feature")
public class GetUserTest {

	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "d77b9d87f21adb7d46dc404883b12387af6330783a973d7702d08950570efbbe";

	@Description("Get all users - get call")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=1)
	public void getAllUsersListApiTest() {

		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+ token);
		
		Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Description("Get a users with query parameters - get call")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void getUserWithQueryParamApiTest() {
		
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+ token);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "Smita");
		params.put("gender", "male");

		Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, params, true);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void getGorestPostsTest() {
		
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("id", "1405");
		
		Response response = RestClient.doGet("JSON", "https://gorest.co.in", "/public/v2/posts", authTokenMap, paramsMap, true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
	}

}
