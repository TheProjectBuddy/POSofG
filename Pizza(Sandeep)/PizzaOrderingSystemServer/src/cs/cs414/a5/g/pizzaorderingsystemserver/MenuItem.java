package cs.cs414.a5.g.pizzaorderingsystemserver;

public class MenuItem {
	
	private String type;
	private String descrption;
	public MenuItem(String type, String des) {
	
		this.type=type;
		this.descrption=des;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescrption() {
		return descrption;
	}
	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}
	

}
