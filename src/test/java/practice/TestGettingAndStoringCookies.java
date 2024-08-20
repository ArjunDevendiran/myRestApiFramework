package practice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TestGettingAndStoringCookies {


	@Test
	void getUsers() {

		Response res = given()
				.when()
				.get("https://www.google.com/");

		// Get all cookies: 
		// Cookies are in key-value pairs, hence we use HasMap as getCookies method returns a Map
		Map<String, String> getAllCookies = res.getCookies();
		System.out.println(getAllCookies);

		// get single cookie
		String cookie1 = res.getCookie("AEC");
		System.out.println(cookie1);

		// Print all cookie's keys
		System.out.println(getAllCookies.keySet());

		// Print all cookies with help of their keys
		for(String key:getAllCookies.keySet()) {
			String cookie = res.getCookie(key);
			System.out.println(key + " ===    "+ cookie);
		}
	}

	// we can also use log method to log all cookies:
	@Test
	void logCookies() {

		given()
		.when()
		.get("https://www.google.com/")
		.then()
		.statusCode(200)
		.log().cookies();
	}
}
