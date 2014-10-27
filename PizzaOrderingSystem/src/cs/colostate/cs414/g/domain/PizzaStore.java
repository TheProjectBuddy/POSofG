package cs.colostate.cs414.g.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cs.colostate.cs414.g.util.MainUtil;

public class PizzaStore {
	static Map< String, ArrayList< Order > > orders = Collections.synchronizedMap(new HashMap< String, ArrayList< Order > >());
	static Map< String, Customer > customers = Collections.synchronizedMap(new HashMap< String, Customer >());
	
	public static ArrayList< Order > getOrdersList() {
		ArrayList< Order > ordersList = new ArrayList< Order >();
		synchronized(orders) {
			Set< Map.Entry< String, ArrayList<Order > > > entrySet = orders.entrySet();
			for (Map.Entry< String, ArrayList<Order > > kv : entrySet) {
				ArrayList<Order> custOrders = kv.getValue();
				synchronized(custOrders) {
					for (Order order : custOrders) {
						ordersList.add(order);
					}
				}
			}
		}
		
		return ordersList;
	}
	
	public static void main(String[] args) {
		
		FileInputStream menuFileStream = null;
		try {
			File file = new File("menu.txt"); 
			menuFileStream = new FileInputStream(file);
		}
		catch (FileNotFoundException e) {
			System.err.println("Unable to open menu file. Exiting...");
			System.exit(1);
		}
		
		Menu menu = null;
		try {
			menu = new Menu(menuFileStream);
		}
		catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace(System.err);
			System.exit(1);
		}
		
		final Kitchen kitchen = new Kitchen();
		
		MainUtil.run(new PhoneOrder(customers, orders), menu, kitchen);
	}
}