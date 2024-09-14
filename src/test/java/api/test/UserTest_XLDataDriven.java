package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.endpoint.UserEndPoint;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTest_XLDataDriven {
	
	/**
	  Here we will use DataProvider approach to get the data from excel sheet, hence Data Driven testing
	 */
	
	User userPayload;
	ObjectMapper objMpr;
	
	// dataProvider name will be defined in the test annotation, this name will be defined on the util method which has @DataProvider annotation
	// dataProviderClass path will e defined if the class is not in the same package of test class
	// all the params in method will be added in sequence as the titles are defined in the excel table
	// These data then will be passed to the POJO class to convert the string data to an object
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void createMultipleUsers(String userID, String userName, String firstName, String lastName, String email, String pwd, String phone) throws JsonProcessingException {
		
		userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		// for Serialization - converting json obj to string
		objMpr = new ObjectMapper();
				
		String jsonData = objMpr.writerWithDefaultPrettyPrinter().writeValueAsString(userPayload);
		System.out.println(jsonData);
		
		// This test will create as many users the data/object is passed. Excel has 3 users, so 3 datas will be created
		Response response = UserEndPoint.creatUser(this.userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void deleteMultipleUsers(String userName) {
		
		System.out.println("user name   ==  " + userName);
		
		// This test will delete as many users names are passed. Excel has 3 users, so 3 datas will be deleted
		Response response = UserEndPoint.deleteUser(userName);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}

}
