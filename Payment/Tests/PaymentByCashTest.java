package cs.colostate.cs414.g.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class PaymentByCashTest {

	PaymentByCash payment=new PaymentByCash(10.0,"InStore");
	
	@Test
	public void testGetReturn()
	{
		assertEquals("4.0",Double.toString(payment.getReturnAmount(14)));
	}
}
