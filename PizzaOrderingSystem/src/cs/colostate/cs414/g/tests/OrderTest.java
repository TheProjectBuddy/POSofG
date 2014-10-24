package cs.colostate.cs414.g.tests;

	import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cs.colostate.cs414.g.domain.*;

	public class OrderTest {

		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
		}

		@Before
		public void setUp() throws Exception {
		}

		@Test
		public void testAddItem() {
			Order order = new Order();
			MenuItem item = new MenuItem("Test Item", 9.99);
			Assert.assertTrue(order.getOrderList().size() == 0);
			order.addItem(item);
			assertTrue(order.getOrderList().size() == 1);
			assertTrue(order.getOrderList().get(0).equals(item));
		}
		
		@Test
		public void testRemoveItem() {
			Order order = new Order();
			MenuItem item = new MenuItem("Test Item", 9.99);
			order.addItem(item);
			assertTrue(order.getOrderList().size() == 1);
			order.removeItem(item);
			assertTrue(order.getOrderList().size() == 0);
		}
		
		@Test
		public void testGetOrderDue() {
			Order order = new Order();
			MenuItem item = new MenuItem("Test Item", 9.99);
			order.addItem(item);
			assertTrue(order.getTotal() == 9.99);
			order.addItem(item);
			assertTrue(order.getAmountDue() == 9.99*2);
		}
		
		@Test
		public void testPay() {
			Order order = new Order();
			MenuItem item = new MenuItem("Test Item", 9.99);
			order.addItem(item);
			assertTrue(order.getTotal() == 9.99);
			order.pay(9.99, "Credit");
			assertTrue(order.getAmountDue() == 0);
		}

	}
