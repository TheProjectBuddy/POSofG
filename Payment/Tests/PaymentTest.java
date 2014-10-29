package cs.colostate.cs414.g.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class PaymentTest {

	
	Payment payment=new Payment(10.0,"InStore");
	
	@Test
	public void getAmountTest()
	{
		assertEquals("10.0",Double.toString(payment.getAmount()));
	}
	
	@Test
	public void getTypeTest()
	{
		assertEquals("InStore",payment.getType());
	}
	@Test
	public void processCouponTest1()
	{
		assertEquals(true,payment.processCoupon("ABCD1234"));
	}
	@Test
	public void processCouponTest2()
	{
		assertEquals(false,payment.processCoupon("ABC12345"));
	}
    @Test
    public void discountedPriceTest()
    {
    	if(payment.processCoupon("ABCD1234"))
    	assertEquals("5.0",Double.toString(payment.getDiscountedPrice()));
    }
}

