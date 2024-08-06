package practice;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

 

public class Sample {
	
	int id;
	
	@Test (priority = 1)
	void getUsers() {
		
		given()   //if we dont have any prerequisites to send in given() we can remove this method and just keep when and then
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)   // validate response code
			.body("page", equalTo(2)) // validate response body
			.log().all(); //print response in console
	}
	
	@Test (priority = 2)
	void createUser(){
		
		HashMap<String, String> data = new HashMap<String, String>();  // storing the request body to be passed in the request
		data.put("name", "ABC");
		data.put("job", "DEF");
		
		id = given()
			.contentType("application/json")  // defining what content type is request
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id"); //storing the ID value
	}
	
	@Test (priority = 3, dependsOnMethods= {"createUser"})
	void editUser(){
		
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "GHI");
		data.put("job", "JKL");
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("https://reqres.in/api/users/" + id)
			
		.then()
			.statusCode(200)
			.body("name", equalTo("GHI"))
			.log().all();
	}
	
	@Test (priority = 4, dependsOnMethods= {"createUser"})
	void deleteUser(){
		
		when()
			.delete("https://reqres.in/api/users/" + id)
			
		.then()
			.statusCode(204);
	}

}
