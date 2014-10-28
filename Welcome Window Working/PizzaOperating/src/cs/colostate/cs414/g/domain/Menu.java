package cs.colostate.cs414.g.domain;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

public class Menu {

	protected int menuID ;
	public String menuName ;
	protected Calendar effectiveDate ;
	protected Calendar ineffectiveDate ;
	private StoreManager creatingManager;
	private MenuItem special;
	private ArrayList<MenuItem> allMenuItem = new ArrayList<MenuItem>();
	private ArrayList< Topping > toppings = null;
	
	
        private ArrayList<String> menuItemNames = new ArrayList<String>();
        private ArrayList<Double> menuItemPrices = new ArrayList<Double>();
	public Menu(String menuName, Calendar effectiveDate, Calendar ineffectiveDate, StoreManager creatingManager) {
		this.menuName = menuName;
		this.effectiveDate = effectiveDate;
		this.ineffectiveDate = ineffectiveDate;	
		this.creatingManager = creatingManager;
	}
	
	public Menu(InputStream input) throws Exception {
		InputStreamReader reader = new InputStreamReader(input);
		BufferedReader br = new BufferedReader(reader);
		
		String line = null;
		ArrayList< MenuItem > items = new ArrayList< MenuItem >();
		ArrayList< Topping > toppings = new ArrayList< Topping >();
		while ((line = br.readLine()) != null) {
			// Skip whitespace lines and comments
			if (line.trim().length() == 0 || (line.length() > 0 && 
					line.charAt(0) == '#')) {
				continue;
			}
			
			String[] splits = line.split("\\|");
			for (int i = 0; i < splits.length; ++i) {
				splits[i] = splits[i].trim();
			}
			
			final int NUM_PIZZA_PARAMS = 9;
			final int NUM_TOPPINGS_PARAMS = 3;
			final int NUM_OTHER_PARAMS = 8;
			if (splits.length == NUM_PIZZA_PARAMS && splits[0].equals("0")) {
				// must be a pizza
				Pizza.Size size;
				if (splits[1].equals("small")) {
					size = Pizza.Size.SMALL;
				}
				else if (splits[1].equals("medium")) {
					size = Pizza.Size.MEDIUM;
				}
				else if (splits[1].equals("large")) {
					size = Pizza.Size.LARGE;
				}
				else {
					throw new Exception("Unable to parse pizza size.");
				}
				
				double price = Double.parseDouble(splits[2]);
				double pricePerToppings = Double.parseDouble(splits[3]);
				double prepTime = Double.parseDouble(splits[4]);
				double cookTime = Double.parseDouble(splits[5]);
				int ovenSpace = Integer.parseInt(splits[6]);
				items.add(new Pizza(size, price, pricePerToppings, 
						prepTime, cookTime, ovenSpace));
			}
			else if (splits.length == NUM_TOPPINGS_PARAMS && splits[0].equals("1")) {
				toppings.add(new Topping(splits[1]));
			}
			else if (splits.length == NUM_OTHER_PARAMS && splits[0].equals("2")) {
				String name = splits[1];
				double price = Double.parseDouble(splits[2]);
				double prepTime = Double.parseDouble(splits[3]);
				double cookTime = Double.parseDouble(splits[4]);
				int ovenSpace = Integer.parseInt(splits[5]);
				items.add(new MenuItem(name, price, prepTime, 
						cookTime, ovenSpace));
			}
			else {
				throw new Exception("Unable to parse menu file -- error found.");
			}
		}
		
		this.allMenuItem = items;
		this.toppings = toppings;
	}

	public Menu(ArrayList< MenuItem > foodItems, ArrayList< Topping > toppings) {
		this.allMenuItem = foodItems;
		this.toppings = toppings;
	}
	
	public String getName() {
		return menuName;
	}
	
	public StoreManager getCreatingManager(){
		return this.creatingManager;
	}
	
	public void setEffectiveDate(Calendar date) {
		this.effectiveDate = date;
	}
	
	public void setIneffectiveDate(Calendar date) {
		this.ineffectiveDate = date;
	}
	
	public void addMenuItem(MenuItem item) {

		if(!allMenuItem.contains(item))
		{
			allMenuItem.add(item);
            menuItemNames.add(item.name);
            menuItemPrices.add(item.price);
	}
         
	}
	
	public void removeMenuItem(MenuItem item) {
		allMenuItem.remove(item);
	}
	

	public void setSpecial(MenuItem item) {
		this.special = item;
	}
	
	public Calendar getEffectiveDate() {
		return this.effectiveDate;
	}
	
	public Calendar getIneffectiveDate() {
		return this.ineffectiveDate;
	}

	public MenuItem getSpecial() {
		return this.special;
	}
	
	public int getNumberOfItems() {
		return allMenuItem.size();
	}
	
	public ArrayList< MenuItem > getItems() {
		ArrayList< MenuItem > deepCopy = new ArrayList< MenuItem >();
		for (MenuItem t : allMenuItem) {
			deepCopy.add(t.copy());
		}
		return deepCopy;
	}
	public MenuItem instantiateFood(MenuItem food) {
		return food.copy();
	}
	public ArrayList< Topping > getToppings() {
		ArrayList<Topping> deepCopy = new ArrayList<Topping>();
		for (Topping t : toppings) {
			deepCopy.add(new Topping(t));
		}
		return deepCopy;
	}
}
