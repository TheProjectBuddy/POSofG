package cs.colostate.cs414.g.domain;

public class MenuItem {
	
	public int itemID;
	public double price;
	public String description;
	public String name;

	public MenuItem(String name, double price) {
		this.name = name;
		this.price = price;
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

}
