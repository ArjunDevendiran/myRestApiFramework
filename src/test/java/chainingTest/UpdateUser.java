package chainingTest;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	
	
	@Test
	public void updateUser(ITestContext context){
		
		//getting the data from the itestcontext and this data was stored in CreatUser test
		// we are casting here so that context object will be converted to string
//		String id = (String) context.getAttribute("user_id"); 
		
		// with this we will be able to run with testngWithSeparateTests.xml
		String id = (String) context.getSuite().getAttribute("user_id");
		
		// create payload
		Faker fk = new Faker();
		JSONObject data = new JSONObject();
		data.put("firstName", fk.funnyName());
		data.put("lastName", fk.name().nameWithMiddle());
				
		given()
			.contentType("application/json")
			.body(data.toString())
			.pathParam("id", id)
		.when()
			.put("http://localhost:3000/students/{id}")
		.then()
			.statusCode(200)
			.log().all();
	}

}
