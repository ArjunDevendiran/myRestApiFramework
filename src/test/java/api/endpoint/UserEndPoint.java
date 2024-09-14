package api.endpoint;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import api.payload.User;

/**
  This class is created to hold CRUD methods for User APIs - Create, Read, Update and Delete
 */

public class UserEndPoint {
	
	/**
	 * Creates user
	 * @param User payload
	 * @return Response response
	 */
	public static Response creatUser(User payload) {
		
		Response response = given()
//								.contentType("application/json")  // we can also write like in line 25 and 26
//								.header("accept","application/json")
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								
								.body(payload)
							.when()
								.post(UserRoute.create_user_url);
		
		return response;
		
	}
	
	/**
	 * Gets user
	 * @param String userName
	 * @return Response response
	 */
	public static Response getUser(String userName) {
		
		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.pathParam("username", userName)
							.when()
								.get(UserRoute.get_user_url);
		
		return response;
		
	}
	
	/**
	 * Updates user
	 * @param String userName
	 * @param User payload
	 * @return Response response
	 */
	public static Response updateUser(String userName, User payload) {
		
		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.body(payload)
								.pathParam("username", userName)
							.when()
								.put(UserRoute.update_user_url);
		
		return response;
		
	}
	
	/**
	 * Deletes user
	 * @param String userName
	 * @return Response response
	 */
	public static Response deleteUser(String userName) {
		
		Response response = given()
								.pathParam("username", userName)
							.when()
								.delete(UserRoute.update_user_url);
		
		return response;
		
	}

}
