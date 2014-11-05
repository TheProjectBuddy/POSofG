package cs.colostate.cs414.g.domain;



public class PaymentByCard extends Payment{

	public String cardno;
	public PaymentByCard(double d, String type) {
		super(d, type);
		// TODO Auto-generated constructor stub
	}
	
	public Boolean authenticateCard(String cardNo,String CVV,String Expiry)
	{
		Boolean flag=true;
	    cardno=cardNo;
		String cvv=CVV;
		String expiry=Expiry;
		
		if((cardno.length())!=16)
			flag=false;
		if(!(cardno.matches("[0-9]+")))
			flag=false;
		if(!(cvv.matches("[0-9]+")))	
			flag=false;
        if(!(expiry.charAt(2)=='/'))
        	flag=false;
		if(cardno.isEmpty()||cvv.isEmpty()||expiry.isEmpty())
			flag=false;
		return flag;
	}
	
	public String getCardNO()
	{
		return cardno.substring(cardno.length()-4);
	}

}
