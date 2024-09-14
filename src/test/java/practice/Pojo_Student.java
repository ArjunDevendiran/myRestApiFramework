package practice;

import java.util.HashMap;

public class Pojo_Student {
	
	// Make sure the json keys match exactly as defined in the swagger or any other specificaton doc, it is case sensitive
	
	String firstName;
	String lastName;
	String gender;
	HashMap address[];
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public HashMap[] getAddress() {
		return address;
	}
	public void setAddress(HashMap[] address) {
		this.address = address;
	}
	
	

}
