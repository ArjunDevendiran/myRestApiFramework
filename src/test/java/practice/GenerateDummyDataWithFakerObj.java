package practice;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class GenerateDummyDataWithFakerObj {
	
	
	
	@Test(priority = 1)
	void generateRandomData() {
		
		Faker faker = new Faker();
			
		String fullName = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String userName = faker.name().username();
		String password = faker.internet().password();
		String email = faker.internet().safeEmailAddress();
		String phone = faker.phoneNumber().cellPhone();
		 System.out.println(fullName);
		 System.out.println(firstName);
		 System.out.println(lastName);
		 System.out.println(userName);
		 System.out.println(password);
		 System.out.println(email);
		 System.out.println(phone);
	}
	

}
