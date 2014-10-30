package cs.colostate.cs414.g.util;

import cs.colostate.cs414.g.domain.OrderItem;

public abstract class Stage implements java.io.Serializable
{
	protected Stage nextStage;

	public void setNextStage(Stage nextStage) {
		this.nextStage = nextStage;
	}
	
	public abstract void addOrderItem(OrderItem item);
}