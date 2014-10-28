package cs.colostate.cs414.g.domain;

import java.util.Map;

import cs.colostate.cs414.g.util.OrderStatus;
import cs.colostate.cs414.g.util.Stage;
import cs.colostate.cs414.g.util.TimeUtil;
import cs.colostate.cs414.g.util.Update;
import cs.colostate.cs414.g.util.WaitingQueue;


public class Chef extends Stage implements Update,OrderItemEmp, java.io.Serializable{

	public String name;
	PizzaStore store;
	public Map<String,String> loginUidPwd;
	private double time;
	private WaitingQueue waitingQueue = null;
	private OrderItem currentOrderItem = null;

	public Update initialize() {
		time = TimeUtil.getCurrentTime();
		return this;
	}
	public void setWaitingQueue(WaitingQueue waitingQueue) {
		this.waitingQueue = waitingQueue;
	}
	
	public OrderItem getCurrentItem(){
		return this.currentOrderItem;
	}
	public Map<String, String> getLoginUidPwd() {
		return loginUidPwd;
	}

	public void setLoginUidPwd(Map<String, String> loginUidPwd) {
		this.loginUidPwd = loginUidPwd;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PizzaStore getStore() {
		return store;
	}
	public void setStore(PizzaStore store) {
		this.store = store;
	}
	public OrderStatus getAssociatedStage() {
		return OrderStatus.PREPARATION;
	}

	public void update() {
		if (currentOrderItem != null) {
			TimeUtil timeRange = currentOrderItem.getStageTimes().get(getAssociatedStage());
			System.out.println(timeRange.getStart());
			double elapsedTime = TimeUtil.getCurrentTime() - timeRange.getStart();

			elapsedTime /= 60.0;
			if (elapsedTime > currentOrderItem.getItem().getPrepTime()) {
				currentOrderItem.endStage(getAssociatedStage());
				currentOrderItem.setWorker(null);
				nextStage.addOrderItem(currentOrderItem);
				currentOrderItem = null;
			}
		
		if (currentOrderItem == null && waitingQueue.peekNextItem() != null) {
			addOrderItem(waitingQueue.removeNextItem());
		}
	}
	}
	public void addOrderItem(OrderItem item) {
		if (currentOrderItem == null) {
			item.startStage(getAssociatedStage());
			item.setWorker(this);
			currentOrderItem = item;
		}
	}

	@Override
	public boolean cancel(OrderItem item) {
		if (item == currentOrderItem) {
			currentOrderItem = null;
			return true;
		}
		
		return false;
	}
}
