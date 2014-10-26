package cs.colostate.cs414.g.ui;

import java.util.ArrayList;

import cs.colostate.cs414.g.domain.Menu;
import cs.colostate.cs414.g.domain.MenuItem;
import cs.colostate.cs414.g.domain.PizzaStore;
import cs.colostate.cs414.g.domain.StoreManager;

public class KioskFacade {
	private PizzaStore theStore;
	
	public KioskFacade() {
		theStore = new PizzaStore();
		theStore.initialization();
	}
	
	public void save() {
		theStore.save();
	}
	
	public void setRestaurant(PizzaStore theStore) {
		this.theStore = theStore;
	}
	
	public PizzaStore getStore() {
		return theStore;
	}
	
	public MenuItem getOrderItem(String name) {
		return theStore.getCurrentMenu().getItemOfName(name);
	}
	
	public void addMenu(Menu menu) {
		theStore.addMenu(menu);
	}
	
	public void addMenuItem(String name, double price) {
		//theStore.addMenuItem(name, price);
                Menu tempmenu=theStore.getCurrentMenu();
		MenuItem tempMenuItem=new MenuItem(name,price);
		tempmenu.addMenuItem(tempMenuItem);
	}
	
        public void addSpecialItem(String name, double price) {
		Menu tempmenu=theStore.getCurrentMenu();
		MenuItem tempMenuItem=new MenuItem(name,price);
		tempmenu.setSpecial(tempMenuItem);
		
	}

	public String getMenuName() {
		return theStore.getCurrentMenuName();
	}
	
	public void addNewMenu(String menuName, String managerName) {
		theStore.addMenu(new Menu(menuName, new StoreManager(managerName, theStore)));
	}
	
	public ArrayList<String> getCurrentMenuItemNames() {
		return theStore.getCurrentMenuItemNames();
	}
	
	public ArrayList<Double> getCurrentMenuItemPrices() {
		return theStore.getCurrentMenuItemPrices();
	}
}
