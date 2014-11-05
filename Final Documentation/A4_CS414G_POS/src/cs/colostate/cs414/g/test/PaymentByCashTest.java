package cs.colostate.cs414.g.test;

import static org.junit.Assert.*;

import org.junit.Test;

import cs.colostate.cs414.g.domain.PaymentByCash;

public class PaymentByCashTest {

	PaymentByCash payment=new PaymentByCash(10.0,"InStore");
	
	@Test
	public void testGetReturn()
	{
		assertEquals("4.0",Double.toString(payment.getReturnAmount(14)));
	}
}
