package cs.colostate.cs414.g.domain;

import java.util.ArrayList;

public class Order {
	public int orderID;
	public OrderStatus orderStatus;
	public ArrayList<OrderItem> orderList = new ArrayList<OrderItem>();
	public float total = 0;
	public float amountDue;
	public int numberOfItems;
	
	public Order(){
		
	}
	
	public int getNumItems() {
		return numberOfItems ;
	}
	
	public void pay(float amount, String type) {
		Payment payment = new Payment(amount, type);
		this.amountDue -= payment.getAmount();
	}
	
	public ArrayList<OrderItem> getOrderList() {
		return this.orderList;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public double getAmountDue() {
		return this.amountDue;
	}
	
	public boolean isPaid() {
		if(amountDue >= 0)
			return true ;
		return false ;
	}

	public boolean addItem(MenuItem item) {
		boolean found = false;
		for(OrderItem orderItem : orderList) {
			if (orderItem.equals(item)) {
				found = true;
				orderItem.incrementItemQuantity();
				this.amountDue += item.price;
				break;
			}
		}
			
		if(!found) {
			this.orderList.add(new OrderItem(item));
			this.amountDue += item.price;
			this.total += item.price ;
			numberOfItems++ ;
			return true ;
		}
		else
			return false ;
	}
	
	public boolean removeItem(MenuItem item) {
		for(OrderItem orderItem : this.orderList) {
			if(orderItem.equals(item)) {
				orderList.remove(orderItem);
				numberOfItems-- ;
				return true ;
			}
		}
		return false ;
	}

}
