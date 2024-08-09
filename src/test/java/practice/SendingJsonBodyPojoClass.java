package practice;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class SendingJsonBodyPojoClass {
	
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
		
		HashMap address1 = new HashMap();
		address1.put("city", "India");
		HashMap addresses[] = {address1};
		
		Pojo_PostRequest data = new Pojo_PostRequest();
		data.setFirstName("Alpha");
		data.setLastName("Beta");
		data.setGender("male");
		data.setAddress(addresses);
		
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
