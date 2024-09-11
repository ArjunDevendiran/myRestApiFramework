package practice;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;  
import com.fasterxml.jackson.databind.ObjectMapper; //import for ObjectMapper

public class SerialisationPojoToJson {
	
	
	
	@Test(priority = 1)
	void serialisationPojoToJson() throws JsonProcessingException {
		
		HashMap address1 = new HashMap();
		address1.put("city", "India");
		HashMap addresses[] = {address1};
		
		Pojo_PostRequest data = new Pojo_PostRequest();
		data.setFirstName("Alpha");
		data.setLastName("Beta");
		data.setGender("male");
		data.setAddress(addresses);
		
		// This class helps in seriralisation and deserialisation 
		ObjectMapper objMpr = new ObjectMapper(); 
		// here we convert the java obj into json but in string format 
		String jsonData = objMpr.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		 
		 System.out.println(jsonData);
	}
	

}
