package cs.colostate.cs414.g.domain;

public class Payment {

	private String type;
	private double amount;
	
	public Payment(double d, String type) {
		this.setType(type);
		this.setAmount(d);
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
