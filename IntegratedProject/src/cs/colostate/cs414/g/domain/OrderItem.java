package cs.colostate.cs414.g.domain;

import java.util.TreeMap;

import cs.colostate.cs414.g.util.OrderStatus;
import cs.colostate.cs414.g.util.TimeRange;
import cs.colostate.cs414.g.util.TimeSystem;

public class OrderItem implements java.io.Serializable{
	
	private static final long serialVersionUID = -7261946680285305982L;
	private MenuItem food;
	private Order order;
	private OrderItemEmp worker;
	private TreeMap< OrderStatus, TimeRange > stageTimes = new TreeMap< OrderStatus, TimeRange >();
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
		assert(stageTimes.get(stage) == null);
		currentStage = stage;
		TimeRange tr = new TimeRange();
		tr.setStart(TimeSystem.getCurrentTime());
		stageTimes.put(stage, tr);
	}
	
	public void endStage(OrderStatus stage) {
		TimeRange tr = stageTimes.get(stage);
		assert(tr != null);
		tr.setEnd(TimeSystem.getCurrentTime());
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

	public TreeMap<OrderStatus, TimeRange> getStageTimes() {
		return stageTimes;
	}
	
	public OrderStatus getCurrentStage() {
		return currentStage;
	}
	
	public String toString() {
		return food.toString();
	}
}
