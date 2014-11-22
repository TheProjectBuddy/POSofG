package cs.colostate.cs414.g.domain;

import java.util.ArrayList;
import java.util.Iterator;

import cs.colostate.cs414.g.util.OrderStatus;
import cs.colostate.cs414.g.util.WaitingQueue;

public class Oven implements OrderItemEmp, java.io.Serializable
{

	private static final long serialVersionUID = 5609302306524659364L;
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
			freeSpace+= item.getFood().getOvenSpace();
			return true;
		}
		
		return false;
	}

	public OrderStatus getAssociatedStage() {
		return stage;
	}

	public void addOrderItem(OrderItem item) {
		cookingList.add(item);
		freeSpace-= item.getFood().getOvenSpace();
		item.startStage(stage);
		item.setWorker(this);
	}

	public void update() {
		Iterator<OrderItem> it =cookingList.iterator();
		OrderItem currentItem;		

		while(it.hasNext()){
			currentItem=it.next();
			
			
				currentItem.endStage(stage);
				freeSpace += currentItem.getFood().getOvenSpace();
				it.remove(); 
				currentItem.setWorker(null);
				currentItem.startStage(OrderStatus.COMPLETED);
				currentItem.endStage(OrderStatus.COMPLETED);
				
		}
		
		synchronized(waitingQueue) {
			it = waitingQueue.iterator();
			while(it.hasNext() && freeSpace !=0){
				currentItem=it.next();
				if (currentItem.getFood().getOvenSpace()<= freeSpace){
					this.addOrderItem(currentItem);
					it.remove();
				}
			}
		}
	}
}
