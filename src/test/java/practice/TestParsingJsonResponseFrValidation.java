package practice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestParsingJsonResponseFrValidation {
	
	String id;
	
//	@Test
	void getDataApproach1() {
		
		given()
			.contentType("application/json")
		
		.when()
			.get("http://localhost:3000/students/")
		
			.then()
				.statusCode(200)
				.body("students[0].gender", equalTo("Joe"));
	}
	
//	@Test
	void getDataApproach2() {
		
		Response res = given()
		
		.when()
			.get("https://reqres.in/api/users?page=2");
		
		Assert.assertEquals(res.getStatusCode(), 200);  // using TestNG Assertion library
		
		String email = res.jsonPath().get("data[0].email").toString(); // getting json data from res object
		Assert.assertEquals(email, "michael.lawson@reqres.in");
		
		Assert.assertEquals(res.jsonPath().get("data[3].first_name").toString(), "Byron");
	}
	
	
	// Printing all the firstName from the response body
	@Test
	void printAllFirstName() {
		
		Response res = given()
						.contentType(ContentType.JSON) // this needs to be added as prerequisite so that the json body is considered as json type
														// if not defined the json body is not read correctly and wil throw error
		.when()
			.get("https://reqres.in/api/users?page=2");
		
		Assert.assertEquals(res.getStatusCode(), 200);  
		
		// JSONObject class from or.json library is used to convert the response to a json object
		// asString method is used from restassured library to convert the response object to string, we dont use toString method of java
		JSONObject jo = new JSONObject(res.asString());
		
		// getting the size of data array in the json response body
		// data is the array in the response body which has an array of objects
		int size = jo.getJSONArray("data").length(); 
		
		for(int i = 0; i < size; i++) {
			
			// we go to data array in the job object and then go to the object inside it at ith index and get the value of desired key
			// we convert the value to string as it will be JSONObject format 
			String firstName = jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
			System.out.println(firstName);
		}
	}
	
	
}
