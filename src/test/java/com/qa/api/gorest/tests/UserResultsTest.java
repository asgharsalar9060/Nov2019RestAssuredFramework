package com.qa.api.gorest.tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.BookingDates;
import com.qa.api.gorest.pojo.UserInfo;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserResultsTest {
	
	
	@Test
	public void createBookingTest() {
		
		BookingDates bd = new BookingDates("2022-09-01", "2022-09-10");
		UserInfo uf = new UserInfo("Khan", "J", 111, true, bd);
		
		String userJsonPayload = TestUtil.getSerializedJson(uf);
		System.out.println(userJsonPayload);
		
//		Map<String, String> tokenMap = new HashMap<String, String>();
//		tokenMap.put("Authorization", "Bearer " + )
		
		Response response = RestClient.doPost("JSON", "https://restful-booker.herokuapp.com", "/booking", null, null, true, userJsonPayload);
////		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
//		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
//		
//		given().log().all()
//			.contentType(ContentType.JSON)
//			.body(userJsonPayload)
//		.when().log().all()
//			.post("/booking")
//		.then().log().all()
//			.assertThat()
//				.contentType(ContentType.JSON)
//				.statusCode(200);
	}

}
