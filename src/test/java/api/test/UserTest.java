package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import api.endpoint.UserEndPoint;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker fk;
	User userPayload;
	ObjectMapper objMpr;
	String jsonData;
		
	@BeforeClass
	public void setData() {
		
		fk = new Faker();
		
		userPayload = new User();
		
		userPayload.setId(fk.idNumber().hashCode());
		userPayload.setUsername(fk.name().username());
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().safeEmailAddress());
		userPayload.setPassword(fk.internet().password(5, 10));
		userPayload.setPhone(fk.phoneNumber().cellPhone());
		
		// for Serialization - converting json obj to string
		objMpr = new ObjectMapper();
	}
	
	@Test(priority = 1)
	public void createUser() throws JsonProcessingException {
		 
		// Serialization - converting json obj to string
		jsonData = objMpr.writerWithDefaultPrettyPrinter().writeValueAsString(userPayload);
		System.out.println(jsonData);
		
		
		Response response = UserEndPoint.creatUser(this.userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority = 2)
	public void getUser() {
		Response response = UserEndPoint.getUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority = 3)
	public void updateUser() {
		
		// updating following details
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().safeEmailAddress());
		
		Response response = UserEndPoint.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority = 4)
	public void deleteUser() {
		
		Response response = UserEndPoint.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}

}
