package cs.colostate.cs414.g.domain;

public class Payment {

	private String type;
	private float amount;
	
	public Payment(float amount, String type) {
		this.setType(type);
		this.setAmount(amount);
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
