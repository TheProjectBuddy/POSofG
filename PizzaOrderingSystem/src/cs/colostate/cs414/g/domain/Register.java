package cs.colostate.cs414.g.domain;

public class Register {
	
	static int orderID;
	Order newOrder;
	float grandTotal;
	
	public Register(){
		
	}
	public void placeOrder(){
		newOrder = new Order(orderID);
	}
	public int generateOrderID(){
		return orderID++;
	}
	public void addItem(MenuItem item){
		newOrder.addItemToOrder(item);
	}
	public float getTotal(){
		grandTotal = newOrder.computeTotal(orderID);
		return grandTotal;
	}
}
