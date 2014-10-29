package cs.colostate.cs414.g.domain;

public class PaymentByCash extends Payment {
	
	private double cashGiven;

	public PaymentByCash(double d, String type) {
		super(d, type);
		// TODO Auto-generated constructor stub
	}
	
	public double getReturnAmount(double finalPrice,double cashGiven)
	{
		this.cashGiven=cashGiven;
          		
		return cashGiven-finalPrice;
	}

}
