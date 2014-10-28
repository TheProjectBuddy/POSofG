package cs.colostate.cs414.g.domain;

import java.util.ArrayList;
import java.util.Iterator;

import cs.colostate.cs414.g.util.OrderStatus;
import cs.colostate.cs414.g.util.Stage;
import cs.colostate.cs414.g.util.Update;
import cs.colostate.cs414.g.util.WaitingQueue;

public class Oven extends Stage implements Update,OrderItemEmp, java.io.Serializable
{
	private WaitingQueue waitingQueue = null;
	private ArrayList<OrderItem> cookingList = new ArrayList<OrderItem>();
	private final int capacity;
	private int freeSpace;
	private final OrderStatus stage;

	public Oven(int size) {
		capacity=size;
		freeSpace=size;
		stage=OrderStatus.COOKING;
	}

	public void setWaitingQueue(WaitingQueue waitingQueue) {
		this.waitingQueue = waitingQueue;
	}

	public ArrayList<OrderItem> getCookingList(){
		return cookingList;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public boolean cancel(OrderItem item) {
		if(cookingList.remove(item)){
			freeSpace+= item.getItem().getOvenSpace();
			return true;
		}
		
		return false;
	}

	public OrderStatus getAssociatedStage() {
		return stage;
	}
	
	public void addOrderItem(OrderItem item) {
		cookingList.add(item);
		freeSpace-= item.getItem().getOvenSpace();
		item.startStage(stage);
		item.setWorker(this);
	}
	
	public void update() {
		Iterator<OrderItem> it =cookingList.iterator();
		OrderItem currentItem;		

		while(it.hasNext()){
			currentItem=it.next();
			double cookingTime= currentItem.getItem().getCookTime() * 60.0;
				freeSpace += currentItem.getItem().getOvenSpace();
				//remove from cook queue
				it.remove(); 
				currentItem.setWorker(null);
				nextStage.addOrderItem(currentItem);
			
		synchronized(waitingQueue) {
			it = waitingQueue.iterator();
			while(it.hasNext() && freeSpace !=0){
				currentItem=it.next();
				//if it fits, it cooks
				if (currentItem.getItem().getOvenSpace()<= freeSpace){
					this.addOrderItem(currentItem);
					it.remove();
				}
			}
		}
	}
}
}
