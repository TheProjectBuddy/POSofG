package cs.colostate.cs414.g.domain;

import java.util.ArrayList;

public class PizzaStore {
	public String storeName ;
	public String storeLocation ;
	public String storePhoneNum ;
	private ArrayList<Menu> menuList;
	private ArrayList<StoreManager> managerList;
	private ArrayList<Cashier> cashierList;
	private ArrayList<Chef> chefList;

	public PizzaStore() {
		menuList = new ArrayList<Menu>();
		managerList = new ArrayList<StoreManager>();
	}
	
	//initialize all variables..
	//Decide upon the Menu.
}
