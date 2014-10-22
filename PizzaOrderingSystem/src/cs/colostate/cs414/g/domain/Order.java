package cs.colostate.cs414.g.domain;

import java.util.ArrayList;

public class Order {
	int orderID;
	OrderStatus orderStatus;
	ArrayList<MenuItem> orderList = new ArrayList<MenuItem>();
	float total = 0;
	
	public Order(int orderID){
		this.orderID = orderID;
		this.orderStatus=OrderStatus.WAITING;
	}

	public void addItemToOrder(MenuItem item) {
		orderList.add(item);
	}

	public float computeTotal(int orderID) {
		for(MenuItem item:orderList){
			total+=item.getPrice();
		}
		return total;
	}

}
