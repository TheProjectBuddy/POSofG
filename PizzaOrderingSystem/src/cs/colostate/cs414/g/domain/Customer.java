package cs.colostate.cs414.g.domain;

public class Customer {

	private String phoneNumber;
	private String name;
	
	public Customer(String phoneNumber, String name) {
		this.phoneNumber = phoneNumber;
		this.name = name;
	}
	
	public String toString() {
		return name + " - " + phoneNumber;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getName() {
		return name;
	}	

}
