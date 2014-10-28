package cs.colostate.cs414.g.domain;

import java.util.ArrayList;
import java.util.TreeMap;

import cs.colostate.cs414.g.util.OrderStatus;
import cs.colostate.cs414.g.util.TimeUtil;

public class Order implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int orderID;
	public ArrayList<OrderItem> orderList = new ArrayList<OrderItem>();
	public float total = 0;
	public float amountDue;
	public int numberOfItems;
	Customer customer;
	private double timeCreated;
	private double estimatedTime;
	public static int orderCounter = 1;
	
	public Order(Customer customer){
		this.customer = customer;
		this.orderID = orderCounter++;
	}
	
	public int getNumItems() {
		return numberOfItems ;
	}
	
	public void pay(double d, String type) {
		Payment payment = new Payment(d, type);
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
	
	public boolean removeItem(OrderItem item) {
		if (orderList.contains(item)) {
		if (item.cancel()) {
			orderList.remove(item);
			return true;
		}
	}
	
	return false;
	}
	
	public synchronized boolean cancelItem(OrderItem item) {
		if (orderList.contains(item)) {
			if (item.cancel()) {
				orderList.remove(item);
				return true;
			}
		}
		
		return false;
	}
	
	public synchronized OrderItem addItem(MenuItem item) {
		OrderItem newItem = new OrderItem(this, item);
		orderList.add(newItem);
		return newItem;
	}
	
	public synchronized void cancel() {
		for (OrderItem oi : orderList) {
			oi.cancel();
		}
		orderList.clear();
	}
	
	public synchronized double getPrice() {
		double total = 0.0;
		for (OrderItem oi : orderList) {
			total += oi.getItem().getPrice();
		}
		
		return total;
	}
	public double getNeededTime(){
		double sum=0;
		for (OrderItem item: orderList){
			sum += item.getItem().getPrepTime();
			sum += item.getItem().getCookTime();
		}
		return sum;
	}
	
	public synchronized boolean isOrderCompleted() {
		return getCurrentStage() == OrderStatus.COMPLETED;
	}
	public synchronized OrderStatus getCurrentStage() {
		if (orderList.size() == 0) {
			return OrderStatus.MODIFY;
		}
		
		int lowestStage = 10000;
		for (OrderItem item : orderList) {
			if (item.getCurrentStage().ordinal() < lowestStage) {
				lowestStage = item.getCurrentStage().ordinal();
			}
		}
		
		return OrderStatus.values()[lowestStage];
}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(double timeCreated) {
		this.timeCreated = timeCreated;
	}

	public double getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(double estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public void setOrderList(ArrayList<OrderItem> orderList) {
		this.orderList = orderList;
	}
	public synchronized double calculateTotalTime() {
		double max = 0.0;
		max = getTimeCreated();
		OrderStatus stage = this.getCurrentStage();
		for (OrderItem oi : orderList) {
			TreeMap< OrderStatus, TimeUtil > durations = oi.getStageTimes();
			if (durations.size() > 0) {
				OrderStatus lastStage = durations.lastKey();
				double endTime = durations.get(lastStage).getEnd();
				if (endTime > max) {
					max = endTime;
				}
			}
		}
		
		assert(max > getTimeCreated());
		return (max - getTimeCreated());
	}
	public synchronized double getTimeSpentPreparing(){
		double orderPrepTime=0;
		for (OrderItem curItem: orderList){
			TimeUtil prepWait=curItem.getStageTimes().get(OrderStatus.PREPARATION_WAITING);
			TimeUtil prep=curItem.getStageTimes().get(OrderStatus.PREPARATION);
			if (prep != null) orderPrepTime += prep.getDuration();
			if (prepWait != null) orderPrepTime += prepWait.getDuration();
		}
		return orderPrepTime;
	}
	public synchronized double getTimeSpentCooking(){
		double orderCookTime=0;
		for (OrderItem curItem: orderList){
			TimeUtil cookWait=curItem.getStageTimes().get(OrderStatus.COOKING_WAITING);
			TimeUtil cook=curItem.getStageTimes().get(OrderStatus.COOKING);
			if (cook != null) orderCookTime += cook.getDuration();
			if (cookWait != null) orderCookTime += cookWait.getDuration();
		}
		return orderCookTime;
	}
}
