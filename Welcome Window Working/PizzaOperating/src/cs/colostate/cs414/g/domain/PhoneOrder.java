package cs.colostate.cs414.g.domain;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import cs.colostate.cs414.g.util.OrderStatus;

public class PhoneOrder {

	
	private Map< String, Customer > customers = null;
	private Map< String, ArrayList< Order > > orders = null;
	
	//constructor
	public PhoneOrder(Map< String, Customer > customers, Map< String, ArrayList< Order > > orders) {
		this.customers = customers;
		this.orders = orders;
	}
	public Customer getCustomerForPhoneNumber(String phoneNumber) {
		return customers.get(phoneNumber);
	}
	
	public void addCustomer(Customer newCustomer) {
		customers.put(newCustomer.getPhoneNumber(), newCustomer);
		orders.put(newCustomer.getPhoneNumber(), new ArrayList< Order >());
	}
	
	public ArrayList< Order > getOrdersShallowCopyForCustomer(Customer customer) {
		synchronized(orders) {
			ArrayList< Order > ordersRef = orders.get(customer.getPhoneNumber()); 
			if (ordersRef == null) {
				return null;
			}
			
			synchronized(ordersRef) {
				return new ArrayList< Order >(ordersRef);
			}
		}
	}
	
	/**
	 * Gets the current Order for the customer searched by phone number
	 * if there is no order, creates a new one
	 * @param customer
	 * @param createNewOrder
	 * @return
	 */
	public Order getCurrentOrderForCustomer(Customer customer, boolean createNewOrder) {
		ArrayList< Order > customersOrders = orders.get(customer.getPhoneNumber());
		synchronized(customersOrders) {
			if (customersOrders != null && customersOrders.size() > 0) {
				for (Order o : customersOrders) {
					if (o.isOrderCompleted() == false) {
						return o;
					}
				}
			}
		}
		
		if (createNewOrder) {
			Order newOrder = new Order(customer);
			customersOrders.add(newOrder);
			return newOrder;
		}
		else {
			if (customersOrders.size() > 0) {
				return customersOrders.get(customersOrders.size() - 1);
			}
			else {
				return null;
			}
		}
	}
	
	public Order getLastOrder(String phoneNumber) {
		Customer customer = getCustomerForPhoneNumber(phoneNumber);

		if(customer == null)
			return null;

		Order order = getCurrentOrderForCustomer(customer, false);
		return order;
	}
	public boolean removeOrderItem(OrderItem item) {
		return item.getOrder().cancelItem(item);
	}
	
	public void estimateTime(Order newOrder){
		double total=0;
		synchronized(orders) {
			Set< Map.Entry< String, ArrayList<Order > > > entrySet = orders.entrySet();
			for (Map.Entry< String, ArrayList< Order > > kv : entrySet) {
				ArrayList< Order > custOrders = kv.getValue();
				synchronized(custOrders) {
					for (Order curOrder : custOrders) {
						synchronized(curOrder){
							if (curOrder.getCurrentStage() != OrderStatus.COMPLETED){
								double orderTotal =0;
								OrderStatus stage;
								
								for (OrderItem curItem : curOrder.getOrderList()){
									stage = curItem.getCurrentStage();
									switch (stage){
										case PREPARATION_WAITING:
											orderTotal += curItem.getItem().getPrepTime();
											break;
										case COOKING_WAITING:
											orderTotal += curItem.getItem().getCookTime();
											break;
										default: break;
									}
									total += orderTotal;
								}
							}
						}
					}
				}
			}
		}
		total += newOrder.getNeededTime();
		newOrder.setEstimatedTime(total);
	}
	

}
