package cs.colostate.cs414.g.domain;

import java.util.TreeMap;

import cs.colostate.cs414.g.util.OrderStatus;
import cs.colostate.cs414.g.util.TimeUtil;

public class OrderItem implements java.io.Serializable{

	MenuItem item;
	public Order order;
	private OrderItemEmp employee;
	private OrderStatus currentStage = OrderStatus.MODIFY;
	private TreeMap< OrderStatus, TimeUtil > stageTimes = new TreeMap< OrderStatus, TimeUtil >();
	
	public OrderItem(Order order, MenuItem item) {
		this.item = item;
		this.order = order;
		this.currentStage = OrderStatus.PREPARATION_WAITING;
	}
	
	public boolean equals(MenuItem menuItem) {
		return this.item == menuItem;
	}
	
	public MenuItem getItem(){
		return item ;
	}
	public boolean cancel() {
		return employee == null || employee.cancel(this);
	}

	public OrderItemEmp getWorker() {
		return employee;
	}

	public void setWorker(OrderItemEmp employee) {
		this.employee = employee;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public OrderStatus getCurrentStage() {
		return currentStage;
	}

	public String toString() {
		return item.toString();
	}

	public TreeMap<OrderStatus, TimeUtil> getStageTimes() {
		return stageTimes;
	}
	public void startStage(OrderStatus stage) {
		// set the start time of the stage to the current time
		assert(stageTimes.get(stage) == null);
		currentStage = stage;
		TimeUtil tr = new TimeUtil();
		tr.setStart(TimeUtil.getCurrentTime());
		stageTimes.put(stage, tr);
	}
	public void endStage(OrderStatus stage) {
		TimeUtil tr = stageTimes.get(stage);
		assert(tr != null);
		tr.setEnd(TimeUtil.getCurrentTime());
	}
}
