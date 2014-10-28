package cs.colostate.cs414.g.domain;


public class MenuItem implements java.io.Serializable{

	private static final long serialVersionUID = -859432841580478689L;
	private String type;
	private double price;
	private double prepTime;
	private double cookTime;
	private int ovenSpace;
	
	
	public MenuItem(String type, double price, double prepTime, double cookTime, int ovenSpace) {
		this.type = type;
		this.price = price;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.ovenSpace = ovenSpace;
	}
	

	public String getType() {
		return type;
	}
	
	public double getPrice() {
		return this.price;
	}

	public double getPrepTime() {
		return this.prepTime;
	}
	
	public double getCookTime() {
		return this.cookTime;
	}
	
	public int getOvenSpace() {
		return this.ovenSpace;
	}
	
	public MenuItem copy() {
		return new MenuItem(type, price, prepTime, cookTime, ovenSpace);
	}
	
	public String toString() {
		return this.getType();
	}
}
