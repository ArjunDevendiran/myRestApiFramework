package practice;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;  
import com.fasterxml.jackson.databind.ObjectMapper; //import for ObjectMapper

public class DeSerialisationJsonToPojo {
	
	
	
	@Test(priority = 1)
	void deSrialisationPojoToJson() throws JsonProcessingException {
		
		String jsonData = "{\r\n"
				+ "  \"firstName\" : \"Alpha\",\r\n"
				+ "  \"lastName\" : \"Beta\",\r\n"
				+ "  \"gender\" : \"male\",\r\n"
				+ "  \"address\" : [ {\r\n"
				+ "    \"city\" : \"India\"\r\n"
				+ "  } ]\r\n"
				+ "}";
				
		// This class helps in seriralisation and deserialisation 
		ObjectMapper objMpr = new ObjectMapper(); 
		// here we convert the json string to java obj with help of pojo class 
		Pojo_Student data = objMpr.readValue(jsonData, Pojo_Student.class);
		 
		 System.out.println(data.getFirstName());
		 System.out.println(data.getGender());
		 System.out.println(data.getLastName());
		 System.out.println(data.getAddress());
	}
	

}
