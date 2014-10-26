package cs.colostate.cs414.g.domain;

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
        private ArrayList<String> menuItemNames = new ArrayList<String>();
	public Menu(String menuName, Calendar effectiveDate, Calendar ineffectiveDate, StoreManager creatingManager) {
		this.menuName = menuName;
		this.effectiveDate = effectiveDate;
		this.ineffectiveDate = ineffectiveDate;	
		this.creatingManager = creatingManager;
	}
	
	public Menu(String menuName, StoreManager creatingManager) {
		this.menuName = menuName;
		this.creatingManager = creatingManager;
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
	
	public ArrayList<MenuItem> getMenuItems() {
		return this.allMenuItem;
	}
	
        public ArrayList<String> getMenuItemNames(){
	
		return this.menuItemNames;
	}

	public MenuItem getSpecial() {
		return this.special;
	}
	
	public int getNumberOfItems() {
		return allMenuItem.size();
	}
	public MenuItem getItemOfName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	public MenuItem getItem(int itemID) {
		// TODO Auto-generated method stub
		return null;
	}
}
