package cs.colostate.cs414.g.domain;

import java.io.Serializable;

public class MenuItem implements Serializable{
	
	public int itemID;
	public double price;
	public String description;
	public String name;
	private String type;
	private double prepTime;
	private double cookTime;
	private int ovenSpace;
	public int special=0;

	public MenuItem(String type, double price, double prepTime, double cookTime, int ovenSpace, int itemID, int special) {
		this.type = type;
		this.price = price;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.ovenSpace = ovenSpace;
		this.itemID = itemID;
		this.special = special;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrepTime() {
		return prepTime;
	}
	public void setPrepTime(double prepTime) {
		this.prepTime = prepTime;
	}
	public double getCookTime() {
		return cookTime;
	}
	public void setCookTime(double cookTime) {
		this.cookTime = cookTime;
	}
	public int getOvenSpace() {
		return ovenSpace;
	}
	public void setOvenSpace(int ovenSpace) {
		this.ovenSpace = ovenSpace;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public MenuItem copy() {
		return new MenuItem(type, price, prepTime, cookTime, ovenSpace, itemID, special);
	}
	
	public String toString() {
		return this.getType();
	}
	public boolean cancel() {
		// TODO Auto-generated method stub
		return false;
	}
}
