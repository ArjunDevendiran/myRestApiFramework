package practice;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TestGettingStoringAndValidatingHeaders {

		@Test
		void validateHeaders() {
			
					given()
						.when()
						.get("https://www.google.com/")
						.then()
							.statusCode(200)
							.header("content-encoding", "gzip")
							.header("server", "gws");   }
		@Test
		void getHeaders() {
			
					Response res = given()
						.when()
						.get("https://www.google.com/");
					
					// getting single header value
					String header = res.getHeader("content-encoding");
					System.out.println("single header  = "+ header);
					
					// getting all headers
					Headers allHeaders = res.getHeaders();  // Headers object is used to store multiple headers and no maps
					
					for(Header h : allHeaders) {
						System.out.println(h.getName() + " =  " + h.getValue());  }
		}
		// we can also use log method to log all headers:
		@Test
		void logHeaders() {
					given()
						.when()
						.get("https://www.google.com/")
						.then()
							.log().headers();
		}
}
