package cs.colostate.cs414.g.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import cs.colostate.cs414.g.domain.MenuItem;
import cs.colostate.cs414.g.domain.OrderItem;

public class OrderTest {
	
	@Before
	public void initialSetUp(){
		ArrayList< OrderItem > orderItems = new ArrayList< OrderItem >();
		MenuItem item = new MenuItem("Pizza", 3.0, 2.0, 8.0, 1);
	}

	@Test
	public void testAddFoodItem() {
		
	}
	
	@Test
	public void testCancelItem(){
		
	}
	
	@Test
	public void testCancel(){
		
	}
	
	@Test
	public void testGetPrice(){
		
	}
}
