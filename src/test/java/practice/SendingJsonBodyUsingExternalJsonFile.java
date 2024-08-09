package practice;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class SendingJsonBodyUsingExternalJsonFile {
	
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
	void postNewData() throws FileNotFoundException {
		
		File file = new File("D:/eclipse22-workspace/eclipse-workspace-personal-RestAssured-project/RestAssuredProject/src/test/resources/testData.json"); // access the file
		FileReader fr = new FileReader(file); // read the file
		JSONTokener jt = new JSONTokener(fr); // extracts source string from the file and converts the data in a token 
											  // and this is used by jsonObject to parse the source string to json
		JSONObject data = new JSONObject(jt); // access the json data
		
		
		 Response responseBody = given()
			.contentType("application/json")  // defining what content type is requested
			.body(data.toString()) //JSON Object needs to be converted to string while passing
		
		.when()
			.post("http://localhost:3000/students")
			
			.then()
				.statusCode(201)
				.log().all()
				.extract().response();
		 
		 id = responseBody.jsonPath().getString("id");
		 
		 System.out.println("---------------" + id);
	}
	
//	@Test(priority = 3)
	void deleteData() {
		
		when()
			.delete("http://localhost:3000/students/" + id)
		
			.then()
				.statusCode(200);
	}

}
