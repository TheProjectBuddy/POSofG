package cs.cs414.g.domain;

public class Customer implements java.io.Serializable
{
	private static final long serialVersionUID = 5614201153279683663L;
	private String name;
	private String customerID;

	public Customer(String customerID) {
		this.customerID = customerID;
	}
	
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
}