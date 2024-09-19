package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import api.endpoint.UserEndPoint_PropertiesFile;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest_PropertiesFile {
	
	Faker fk;
	User userPayload;
	ObjectMapper objMpr;
	String jsonData;
	Logger logger;
		
	@BeforeClass
	public void setUp() {
		
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
		
		//log4j logger
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void createUser() throws JsonProcessingException {
		 
		logger.info("=================  Creating user =================");
		
		// Serialization - converting json obj to string
		jsonData = objMpr.writerWithDefaultPrettyPrinter().writeValueAsString(userPayload);
		System.out.println(jsonData);
		
		
		Response response = UserEndPoint_PropertiesFile.creatUser(this.userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority = 2)
	public void getUser() {
		
		logger.info("=================  Getting user info =================");
		
		Response response = UserEndPoint_PropertiesFile.getUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority = 3)
	public void updateUser() {
		
		logger.info("=================  Updating user info =================");
		
		// updating following details
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().safeEmailAddress());
		
		Response response = UserEndPoint_PropertiesFile.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
//	@Test(priority = 4)
	public void deleteUser() {
		
		logger.info("=================  Deleting user =================");
		
		Response response = UserEndPoint_PropertiesFile.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}

}
