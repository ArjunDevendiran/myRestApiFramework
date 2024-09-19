package api.endpoint;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;

/**
  This class is created to hold CRUD methods for User APIs - Create, Read, Update and Delete
  
  and the urls are passed from routes.properties file
 */

public class UserEndPoint_PropertiesFile {
	
	
	// This method will get the data from properties file
	static ResourceBundle getUrl() {
		ResourceBundle url = ResourceBundle.getBundle("routes"); 
		
		return url;
	}
	
	/**
	 * Creates user
	 * @param User payload
	 * @return Response response
	 */
	public static Response creatUser(User payload) {
		
		String postUrl = getUrl().getString("create_user_url");
		
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								
								.body(payload)
							.when()
								.post(postUrl);
		
		return response;
		
	}
	
	/**
	 * Gets user
	 * @param String userName
	 * @return Response response
	 */
	public static Response getUser(String userName) {
		
		String getUrl = getUrl().getString("get_user_url");
		
		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.pathParam("username", userName)
							.when()
								.get(getUrl);
		
		return response;
		
	}
	
	/**
	 * Updates user
	 * @param String userName
	 * @param User payload
	 * @return Response response
	 */
	public static Response updateUser(String userName, User payload) {
		
		String updateUrl = getUrl().getString("update_user_url");
		
		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.body(payload)
								.pathParam("username", userName)
							.when()
								.put(updateUrl);
		
		return response;
		
	}
	
	/**
	 * Deletes user
	 * @param String userName
	 * @return Response response
	 */
	public static Response deleteUser(String userName) {
		
		String deleteUrl = getUrl().getString("delete_user_url");
		
		Response response = given()
								.pathParam("username", userName)
							.when()
								.delete(deleteUrl);
		
		return response;
		
	}

}
