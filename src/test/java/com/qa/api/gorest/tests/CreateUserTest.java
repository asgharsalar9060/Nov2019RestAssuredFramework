package com.qa.api.gorest.tests;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateUserTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "d77b9d87f21adb7d46dc404883b12387af6330783a973d7702d08950570efbbe";
	
	@DataProvider
	public Object[][] getUserData() {
		Object userData[][] =  ExcelUtil.getTestData("userdata");
		return userData;
	}

	
	@Test(dataProvider = "getUserData")
	public void createUserApiTest(String name, String email, String gender, String status) {
		
		User user = new User(name, email, gender, status);
		
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+ token);
		
		Response response = RestClient.doPost("JSON", baseURI, basePath, authTokenMap, null, true, user);
		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
	}
}
