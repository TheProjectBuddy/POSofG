package cs.colostate.cs414.g.domain;

import cs.colostate.cs414.g.util.OrderStatus;
import cs.colostate.cs414.g.util.WaitingQueue;


public class Chef implements OrderItemEmp, java.io.Serializable
{
	private static final long serialVersionUID = 1249465871308592055L;
	private WaitingQueue waitingQueue = null;
	private OrderItem currentOrderItem = null;
	
	public void setWaitingQueue(WaitingQueue waitingQueue) {
		this.waitingQueue = waitingQueue;
	}
	public OrderItem getCurrentItem(){
		return this.currentOrderItem;
	}

	public boolean cancel(OrderItem item) {
		if (item == currentOrderItem) {
			currentOrderItem = null;
			return true;
		}
		
		return false;
	}

	public OrderStatus getAssociatedStage() {
		return OrderStatus.PREPARATION;
	}

	public void update() {

		if (currentOrderItem != null) {
				currentOrderItem.endStage(getAssociatedStage());
				currentOrderItem.setWorker(null);
				currentOrderItem = null;
			
		}
		
		if (currentOrderItem == null && waitingQueue.peekNextItem() != null) {
			addOrderItem(waitingQueue.removeNextItem());
		}
	}

	public void addOrderItem(OrderItem item) {

		if (currentOrderItem == null) {
			item.startStage(getAssociatedStage());
			item.setWorker(this);
			currentOrderItem = item;
		}
	}
}
