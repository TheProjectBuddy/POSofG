package cs.colostate.cs414.g.domain;

import java.util.ArrayList;
import java.util.TreeMap;

import cs.colostate.cs414.g.util.OrderStatus;
import cs.colostate.cs414.g.util.TimeRange;

public class Order implements java.io.Serializable{
	
	private static final long serialVersionUID = 5199787946298783352L;
	private ArrayList< OrderItem > orderItems = new ArrayList< OrderItem >();
	Customer customer;
	private double timeCreated;
	private double estimatedTime;
	private int orderId;
	public double total;
	
	public static int orderCounter = 1;
	
	public Order(Customer customer) {
		this.customer = customer;
		this.orderId = orderCounter++;
	}
	
	public synchronized OrderItem addFood(MenuItem food) {
		OrderItem newItem = new OrderItem(this, food);
		orderItems.add(newItem);
		return newItem;
	}

	public synchronized boolean cancelItem(OrderItem item) {
		if (orderItems.contains(item)) {
			if (item.cancel()) {
				orderItems.remove(item);
				return true;
			}
		}
		
		return false;
	}
	
	public synchronized void cancel() {
		for (OrderItem oi : orderItems) {
			oi.cancel();
		}
		orderItems.clear();
	}
	
	public synchronized double getPrice() {
		total = 0.0;
		for (OrderItem oi : orderItems) {
			total += oi.getFood().getPrice();
		}
		setTotal(total);
		return total;
	}
	
	public double getNeededTime(){
		double sum=0;
		for (OrderItem item: orderItems){
			sum += item.getFood().getPrepTime();
			sum += item.getFood().getCookTime();
		}
		return sum;
	}
	
	
	/**
	 * Calculate how long it took (or is taking, if the order is still in 
	 * progress) for this order to complete 
	 * @return the time it took for this order to complete (or is taking).
	 */
	public synchronized double calculateTotalTime() {
		double max = 0.0;
		max = getTimeCreated();
		for (OrderItem oi : orderItems) {
			TreeMap< OrderStatus, TimeRange > durations = oi.getStageTimes();
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
	
	/**
	 * Totals time spent preparing for this order
	 * @return time spent preparing for this order
	 */
	public synchronized double getTimeSpentPreparing(){
		double orderPrepTime=0;
		for (OrderItem curItem: orderItems){
			TimeRange prepWait=curItem.getStageTimes().get(OrderStatus.PREPARATION_WAITING);
			TimeRange prep=curItem.getStageTimes().get(OrderStatus.PREPARATION);
			if (prep != null) orderPrepTime += prep.getDuration();
			if (prepWait != null) orderPrepTime += prepWait.getDuration();
		}
		return orderPrepTime;
	}
	
	/**
	 * Totals time spent cooking for this order
	 * @return time spent cooking for this order
	 */
	public synchronized double getTimeSpentCooking(){
		double orderCookTime=0;
		for (OrderItem curItem: orderItems){
			TimeRange cookWait=curItem.getStageTimes().get(OrderStatus.COOKING_WAITING);
			TimeRange cook=curItem.getStageTimes().get(OrderStatus.COOKING);
			if (cook != null) orderCookTime += cook.getDuration();
			if (cookWait != null) orderCookTime += cookWait.getDuration();
		}
		return orderCookTime;
	}
	
	/**
	 * Is the order complete (ie, delivered)?
	 * 
	 * @return true if complete else false.
	 */
	public synchronized boolean isOrderCompleted() {
		return getCurrentStage() == OrderStatus.COMPLETED;
	}
	
	/**
	 * Get the current stage of the order. This will be the current stage of the slowest item in the order.
	 * @return the current stage.
	 */
	public synchronized OrderStatus getCurrentStage() {
		if (orderItems.size() == 0) {
			return OrderStatus.MODIFY;
		}
		
		int lowestStage = 10000;
		for (OrderItem item : orderItems) {
			if (item.getCurrentStage().ordinal() < lowestStage) {
				lowestStage = item.getCurrentStage().ordinal();
			}
		}
		
		return OrderStatus.values()[lowestStage];
	}

	/**
	 * The time that the order was created.
	 * @return the timeCreated
	 */
	public synchronized double getTimeCreated() {
		return timeCreated;
	}

	/**
	 * Set the time when the order was initially started/created.
	 * @param timeCreated the timeCreated to set
	 */
	public synchronized void setTimeCreated(double timeCreated) {
		this.timeCreated = timeCreated;
	}

	/**
	 * Get the time when the order was initially started/created.
	 * @return the estimatedTime
	 */
	public synchronized double getEstimatedTime() {
		return estimatedTime;
	}

	/**
	 * Set the estimated time it will take until this order is delivered.
	 * @param estimatedTime the estimatedTime to set
	 */
	public synchronized void setEstimatedTime(double estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	/**
	 * Get a shallow copy of the list of items in the order.
	 * @return the orderItems
	 */
	public synchronized ArrayList<OrderItem> getOrderItems() {
		return new ArrayList< OrderItem >(orderItems);
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * Get the order identification number.
	 * @return the order ID.
	 */
	public int getOrderId() {
		return orderId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
