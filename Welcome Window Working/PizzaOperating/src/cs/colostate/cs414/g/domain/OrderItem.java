package cs.colostate.cs414.g.domain;

import cs.colostate.cs414.g.util.OrderStatus;

public class OrderItem {

	MenuItem item;
	private Order order;
	private OrderItemEmp employee;
	private OrderStatus currentStage = OrderStatus.MODIFY;
	
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
}
