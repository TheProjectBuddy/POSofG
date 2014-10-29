package cs.colostate.cs414.g.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class PaymentByCardTest {
	
	PaymentByCard payment= new PaymentByCard(10.0,"InStore");
	
	@Test
	public void testAuthenticateCard1()
	{
		Boolean temp=payment.authenticateCard("1234567890123456", "123", "12/18");
		assertEquals(true,temp);
	}
	@Test
	public void testAuthenticateCard2()
	{
		Boolean temp1=payment.authenticateCard("123456789012345", "123", "12/18");
        assertEquals(false,temp1);
		
	}
	@Test
	public void testAuthenticateCard3()
	{
		Boolean temp1=payment.authenticateCard("123456789A123456", "123", "12/18");
        assertEquals(false,temp1);
		
	}

	@Test
	public void testAuthenticateCard4()
	{
		Boolean temp1=payment.authenticateCard("1234567892123456", "123", "12118");
        assertEquals(false,temp1);
		
	}

}
