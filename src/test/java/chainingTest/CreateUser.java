package chainingTest;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {
	
	/** 
	 * We will use ITestContext testNG listener for saving the id and using it in other chained clases
	 * 
	 * 
	 */
	
	
	@Test
	public void createUser(ITestContext context){
		
		//create dummy data
		Faker fk = new Faker();
		
		// create payload
		JSONObject data = new JSONObject();
		data.put("firstName", fk.name().firstName());
		data.put("lastName", fk.name().lastName());
		
		//json array payload
		JSONObject address1 = new JSONObject();
		address1.put("city", fk.address().cityName());
		address1.put("state", fk.address().state());
		JSONObject address[] = {address1};
		data.put("address",address);
		
		String id = given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
			.jsonPath().getString("id");
		
		System.out.println(id);
		
		// Storing the id in itestconext at @test level and this wil be used within @test level that means any classes defined within
		// the <test>.. </test> in the test.xml will use the context data but other <test>.. </test> wont have access
		// with this we will be able to run with testng.xml
		// with this we will not be able to run with testngWithSeparateTests.xml because all tests are separated and the data is only 
		// accessible in the test where this data is generated and stored that is CreateUser class 
//		context.setAttribute("user_id",id);
		
		//in this way we are storing the data in suite level so with this the data will be accessible by all the tests within a suite
		// with this we will be able to run with testngWithSeparateTests.xml
		context.getSuite().setAttribute("user_id",id);
		
		// Note - we will also be able to run textg.xml with data stored in suite as suite datas are accessible by tests
	}

}
