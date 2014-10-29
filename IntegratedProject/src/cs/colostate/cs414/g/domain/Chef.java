package cs.colostate.cs414.g.domain;

import cs.colostate.cs414.g.util.OrderStatus;
import cs.colostate.cs414.g.util.Stage;
import cs.colostate.cs414.g.util.TimeRange;
import cs.colostate.cs414.g.util.TimeSystem;
import cs.colostate.cs414.g.util.Update;
import cs.colostate.cs414.g.util.WaitingQueue;


public class Chef extends Stage implements Update, OrderItemEmp, java.io.Serializable
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
			TimeRange timeRange = currentOrderItem.getStageTimes().get(getAssociatedStage());
			double elapsedTime = TimeSystem.getCurrentTime() - timeRange.getStart(); 

			elapsedTime /= 60.0;

			if (elapsedTime > currentOrderItem.getFood().getPrepTime()) {
				currentOrderItem.endStage(getAssociatedStage());
				currentOrderItem.setWorker(null);

				nextStage.addOrderItem(currentOrderItem);
				currentOrderItem = null;
			}
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
