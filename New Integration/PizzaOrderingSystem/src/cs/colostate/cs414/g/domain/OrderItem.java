package cs.colostate.cs414.g.domain;

import java.util.TreeMap;

import cs.colostate.cs414.g.util.OrderStatus;

public class OrderItem implements java.io.Serializable{
	
	private static final long serialVersionUID = -7261946680285305982L;
	private MenuItem food;
	private Order order;
	private OrderItemEmp worker;
	private OrderStatus currentStage = OrderStatus.MODIFY;
	
	public OrderItem(Order order, MenuItem food) {
		this.food = food;
		this.order = order;
		this.currentStage = OrderStatus.PREPARATION_WAITING;
	}

	public boolean cancel() {
		return worker == null || worker.cancel(this);
	}
	
	public void startStage(OrderStatus stage) {
		currentStage = stage;
	}
	
	public void endStage(OrderStatus stage) {
	}

	public OrderItemEmp getWorker() {
		return worker;
	}

	public void setWorker(OrderItemEmp worker) {
		this.worker = worker;
	}

	public MenuItem getFood() {
		return food;
	}

	public Order getOrder() {
		return order;
	}
	
	public OrderStatus getCurrentStage() {
		return currentStage;
	}
	
	public String toString() {
		return food.toString();
	}
}
