package practice;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class SendingJsonBodyUsingHashMap {
	
	String id;
	
	@Test(priority = 2)
	void getData() {
		
		when()
			.get("http://localhost:3000/students/" + id)
		
			.then()
				.statusCode(200)
				.log().all();
	}
	
	@Test(priority = 1)
	void postNewData() {
		
		HashMap data = new HashMap();
		
		HashMap address1 = new HashMap();
		address1.put("city", "India");
		HashMap addresses[] = {address1};
		
		
		data.put("firstName", "Alpha");
		data.put("lastName", "Beta");
		data.put("gender", "male");
		data.put("address", addresses);
		
		 Response responseBody = given()
			.contentType("application/json")  // defining what content type is requested
			.body(data)
		
		.when()
			.post("http://localhost:3000/students")
			
			.then()
				.statusCode(201)
				.log().all()
				.extract().response();
		 
		 id = responseBody.jsonPath().getString("id");
		 
		 System.out.println("---------------" + id);
	}
	
	@Test(priority = 3)
	void deleteData() {
		
		when()
			.delete("http://localhost:3000/students/" + id)
		
			.then()
				.statusCode(200);
	}

}
