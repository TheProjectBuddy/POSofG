package cs.colostate.cs414.g.domain;

public class MenuItem {
	
	public int itemID;
	public float price;
	public String description;
	public String name;

	public MenuItem(String name, float price) {
		this.name = name;
		this.price = price;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public float getPrice() {
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
