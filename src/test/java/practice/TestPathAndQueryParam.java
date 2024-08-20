package practice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class TestPathAndQueryParam {

		
		@Test
		void getUsers() {
			
			/**
			 * URL - https://reqres.in/api/users?page=2
			 * Domain - https://reqres.in/
			 * path - api/users
			 * query param 1 - page=2
			 * query param 2 - id=7
			 */
			
			given()
				.pathParam("path1", "api")  
				.pathParam("path2", "users") 
				.queryParam("page", 2)
				.queryParam("id", 5)
			
			.when()
				.get("https://reqres.in/{path1}/{path2}")
			
			.then()
				.statusCode(200)   
				.log().all(); 
		}
}
