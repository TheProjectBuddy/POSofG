package cs.colostate.cs414.g.domain;

import cs.colostate.cs414.g.util.OrderStatus;

public interface OrderItemEmp
	{
		public boolean cancel(OrderItem item);
		
		public OrderStatus getAssociatedStage();
	}
