package cs.colostate.cs414.g.domain;

import java.util.ArrayList;

public class Pizza extends MenuItem implements java.io.Serializable
{
	
	private Size size;
	private double toppingPrice;
	private ArrayList< Topping > toppings = new ArrayList< Topping >();
	int ID;
	public enum Size
	{
		SMALL,
		MEDIUM,
		LARGE
	}
	
	public Pizza(Size size, double price, double toppingPrice, double prepTime, double cookTime, int ovenSpace, int ID) {
		super("Pizza", price, prepTime, cookTime, ovenSpace, ID);
		this.ID = ID;
		this.size = size;
		this.toppingPrice = toppingPrice;
	}
	
	public Size getSize() {
		return this.size;
	}

	public double getPrice() {
		double totalToppingPrice = 0.0;
		for (Topping t : toppings) {
			totalToppingPrice += toppingPrice * (t.getCoverage() == Topping.Coverage.WHOLE ? 1.0 : 0.5);
		}
		
		return super.getPrice() + totalToppingPrice;
	}

	public void addTopping(Topping topping) {
		for (Topping t : toppings) {
			if (t.getType().equals(topping.getType())) {
				toppings.remove(t);
				break;
			}
		}
		
		toppings.add(topping);
	}

	public void setToppings(ArrayList< Topping > newToppings) {
		toppings = newToppings;
	}

	public void removeTopping(Topping topping) {
		toppings.remove(topping);
	}
	
	public double getToppingPrice() {
		return toppingPrice;
	}

	public ArrayList< Topping > getToppings() {
		return toppings;
	}

	@Override
	public String getType() {
		return size + " " + super.getType();
	}
	
	public MenuItem copy() {
		Pizza newPizza = new Pizza(size, getPrice(), toppingPrice, getPrepTime(), getCookTime(), getOvenSpace(), ID);
		for(Topping t : toppings) {
			newPizza.addTopping(new Topping(t));
		}
		return newPizza;
	}

	@Override
	public String toString(){
		return this.getType();
	}

}
