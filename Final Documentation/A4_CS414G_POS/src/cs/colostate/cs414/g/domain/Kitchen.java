package cs.colostate.cs414.g.domain;

import java.util.ArrayList;

import cs.colostate.cs414.g.util.OrderStatus;
import cs.colostate.cs414.g.util.Stage;
import cs.colostate.cs414.g.util.Update;
import cs.colostate.cs414.g.util.WaitingQueue;

public class Kitchen extends Stage implements Update, java.io.Serializable
{
	private static final long serialVersionUID = 3044674156377666396L;
	private WaitingQueue preparationWaitingQueue = new WaitingQueue(OrderStatus.PREPARATION_WAITING);
	private WaitingQueue cookingWaitingQueue = new WaitingQueue(OrderStatus.COOKING_WAITING);
	private ArrayList< Chef > chefs = new ArrayList< Chef >();
	private ArrayList< Oven > ovens = new ArrayList< Oven >();
	
	public synchronized void addOrderItem(OrderItem item) {
		if (item.getFood().getPrepTime() > 0) {
			preparationWaitingQueue.addOrderItem(item);
		}
		else if (item.getFood().getCookTime() > 0) {
			cookingWaitingQueue.addOrderItem(item);
		}
		else {
			this.nextStage.addOrderItem(item);
		}
	}
	public synchronized void addChef(Chef chef) {
		chefs.add(chef);
		chef.setNextStage(cookingWaitingQueue);
		chef.setWaitingQueue(preparationWaitingQueue);
	}
	
	public synchronized void addOven(Oven oven) {
		assert(this.nextStage != null);
		ovens.add(oven);
		oven.setWaitingQueue(cookingWaitingQueue);
		oven.setNextStage(this.nextStage);
	}
	public synchronized void update() {
	}
}