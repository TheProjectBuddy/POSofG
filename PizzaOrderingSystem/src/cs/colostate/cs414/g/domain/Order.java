package cs.colostate.cs414.g.domain;

import java.util.ArrayList;

public class Order {
	int orderID;
	OrderStatus orderStatus;
	Menu menu;
	ArrayList<MenuItem> orderList = new ArrayList<MenuItem>();
	
	public Order(int orderID){
		this.orderID = orderID;
		this.orderStatus=OrderStatus.WAITING;
	}

	public void addItemToOrder(int itemID) {
		if 
	}

	public float computeTotal(int orderID) {
		
		return 0;
	}

}
