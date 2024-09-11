package chainingTest;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {
	
	
	@Test
	public void getUser(ITestContext context){
		
		// getting the data from the itestcontext and this data was stored in CreatUser test
		// we are casting here so that context object will be converted to string
//		String id = (String) context.getAttribute("user_id"); 
		
		// with this we will be able to run with testngWithSeparateTests.xml
		String id = (String) context.getSuite().getAttribute("user_id");
		
		given()
			.pathParam("id", id)
		.when()
			.get("http://localhost:3000/students/{id}")
		.then()
			.statusCode(200)
			.log().all();
	}

}
