package cs.colostate.cs414.g.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cs.colostate.cs414.g.domain.Customer;
import cs.colostate.cs414.g.domain.MenuItem;
import cs.colostate.cs414.g.domain.Order;
import cs.colostate.cs414.g.domain.OrderItem;

public class OrderTest {
	Customer cust;
	MenuItem item;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		cust = new Customer("555-555-5555", "Customer 1");
		item = new MenuItem("Test Item", 9.99, 10, 1, 1);
	}

	@Test
	public void testAddItem() {
		Order order = new Order(cust);
		assertTrue(order.getOrderItems().size() == 0);
		OrderItem oitem = order.addFood(item);
		assertTrue(order.getOrderItems().size() == 1);
		assertTrue(order.getOrderItems().get(0).equals(oitem));
	}
	
	@Test
	public void testRemoveItem() {
		Order order = new Order(cust);
		OrderItem oitem = order.addFood(item);
		assertTrue(order.getOrderItems().size() == 1);
		order.cancelItem(oitem);
		assertTrue(order.getOrderItems().size() == 0);
	}
	
	@Test
	public void testGetPrice(){
		Order order = new Order(cust);
		ArrayList< OrderItem > orderItems = new ArrayList< OrderItem >();
		OrderItem oi1 = order.addFood(item);
		orderItems.add(oi1);		
		assertTrue(order.getPrice()==9.99);
	}
	
	@Test
	public void testCancelOrder(){
		Order order = new Order(cust);
		ArrayList< OrderItem > orderItems = new ArrayList< OrderItem >();
		OrderItem oi1 = order.addFood(item);
		orderItems.add(oi1);	
		order.cancel();
		assertTrue(order.getOrderItems().isEmpty());
	}

}
