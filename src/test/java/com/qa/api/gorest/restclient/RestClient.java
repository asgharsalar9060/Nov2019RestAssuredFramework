package com.qa.api.gorest.restclient;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

import org.hamcrest.core.IsInstanceOf;

import com.qa.api.gorest.util.TestUtil;

/**
 * This class is has all the HTTP methods which call the APIs Also has the
 * generic methods to get the response Also it fetches the values from the
 * response
 * 
 * @author User
 *
 */

public class RestClient {

	// HTTP Methods: POST GET PUT DELETE

	/**
	 * This method is used to call GET API
	 * 
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @return This method returns response from the GET call
	 */
	@Step("GET call with {0} , {1} , {2} , {3} , {4} , {5}")
	public static Response doGet(String contentType, String baseURI, String basePath, Map<String, String> token,
			Map<String, String> paramsMap, boolean log) {

		if (setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType, token, paramsMap, log);
			return getResponse("GET", request, basePath);
		}
		return null;
	}

	/**
	 * This method is used to call POST API
	 * 
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @param obj
	 * @return This method returns response from the POST call
	 */
	public static Response doPost(String contentType, String baseURI, String basePath, Map<String, String> token,
			Map<String, String> paramsMap, boolean log, Object obj) {

		if (setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType, token, paramsMap, log);
			addRequestPayload(request, obj);
			return getResponse("POST", request, basePath);
		}
		return null;
	}

	private static void addRequestPayload(RequestSpecification request, Object obj) {

		if (obj instanceof Map) {
			request.formParams((Map<String, String>) obj);
		} else {
			String jsonPayload = TestUtil.getSerializedJson(obj);
			request.body(jsonPayload);
		}
	}

	private static boolean setBaseURI(String baseURI) {

		if (baseURI == null || baseURI.isEmpty()) {
			System.out.println("Please pass the correct base URI - either it is null or blank/empty...");
			return false;
		}

		try {
			RestAssured.baseURI = baseURI;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("some exception occured while assigning the base URI with Rest Assured...");
			return false;
		}
	}

	private static RequestSpecification createRequest(String contentType, Map<String, String> token,
			Map<String, String> paramsMap, boolean log) {

		RequestSpecification request;
		if (log) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}

		if (token != null) {
			if (token.size() > 0) {
//			request.header("Authorization", "Bearer " + token);
				request.headers(token);
			}
		}

		if (!(paramsMap == null)) {
			request.queryParams(paramsMap);
		}

		if (contentType != null) {
			if (contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			} else if (contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.XML);
			} else if (contentType.equalsIgnoreCase("TEXT")) {
				request.contentType(ContentType.TEXT);
			} else if (contentType.equalsIgnoreCase("multipart")) {
				request.multiPart("image", new File(
						"C:\\Users\\User\\OneDrive\\Documents\\JavaTraining\\Nov2019RestAssuredApiFramework\\src\\test\\resources\\images\\Terilj.jpg"));
			}
		}

		return request;
	}

	private static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
		return executeApi(httpMethod, request, basePath);
	}

	private static Response executeApi(String httpMethod, RequestSpecification request, String basePath) {

		Response response = null;
		switch (httpMethod) {
		case "GET":
			response = request.get(basePath);
			break;
		case "POST":
			response = request.post(basePath);
			break;
		case "PUT":
			response = request.put(basePath);
			break;
		case "DELETE":
			response = request.delete(basePath);
			break;
		default:
			System.out.println("Please pass the correct HTTP method.");
			break;
		}
		return response;
	}
}
