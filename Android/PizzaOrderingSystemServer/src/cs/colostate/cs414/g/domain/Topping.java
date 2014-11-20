package cs.colostate.cs414.g.domain;

public class Topping {
	
	private String type;
	
	public Topping(String type) {
		this.type = type;
	}
	
	public Topping(Topping other) {
		this.type = new String(other.type);
	}
	
	public String getType() {
		return type;
	}

	public String toString() {
		return this.getType();
	}

}
