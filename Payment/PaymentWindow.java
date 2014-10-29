package cs.colostate.cs414.g.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cs.colostate.cs414.g.domain.Order;
import cs.colostate.cs414.g.domain.Payment;
public class PaymentWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton buttonByCash;
	private JButton buttonByCard;
	private JButton buttonApplyCoupon;
	private JButton buttonBack;
    private JLabel priceLabel;
    private JLabel labelPrice;
    
    private JLabel couponLabel;
    private JLabel labelDiscount;
    private JLabel discountLabel;
    private JTextField couponField;
    private Payment payment;
    
	private double discountedPrice;
	
    public PaymentWindow(OrderEntryWindow orderWindow, Order cOrder)
    {
    	final OrderEntryWindow orderWindow1=orderWindow;
    	final Order currentOrder=cOrder;
    	final double price= currentOrder.getPrice();
    //	final double discountedPrice;
    	payment=new Payment(price,"InStore");
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
				
		labelPrice = new JLabel("Total:");
		labelPrice.setHorizontalAlignment(SwingConstants.CENTER);
		labelPrice.setSize(100, 100);
		labelPrice.setBounds(200,10, 75, 75);
		contentPane.add(labelPrice);
		
	    
		
	    
	    priceLabel = new JLabel("$"+Double.toString(price));
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setSize(100,100);
		priceLabel.setBounds(275, 10, 75, 75);
		contentPane.add(priceLabel);

	    
	    couponLabel = new JLabel("Coupon Code:");
		couponLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		couponLabel.setSize(50, 100);
		couponLabel.setBounds(200,100, 100, 30);
		contentPane.add(couponLabel);
		
		couponField = new JTextField();
		couponField.setText("Enter Code");
		couponField.setSize(50, 75);
		couponField.setBounds(300, 100, 75, 30);
		contentPane.add(couponField);
		couponField.setColumns(8);
		
		labelDiscount = new JLabel("Discounted Total:");
		labelDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		labelDiscount.setSize(200, 100);
		labelDiscount.setBounds(200,140, 150, 75);
		labelDiscount.setVisible(false);
        contentPane.add(labelDiscount);
		discountLabel = new JLabel("$0.00");
		discountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		discountLabel.setSize(100,100);
		discountLabel.setBounds(350, 140, 75, 75);
		discountLabel.setVisible(false);
		contentPane.add(discountLabel);
		discountedPrice=price;
		buttonApplyCoupon=new JButton("Apply");
		buttonApplyCoupon.setBounds(380,100, 75, 30);
		buttonApplyCoupon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			double tempdiscountedPrice=0.0;	
			String couponCode=getCoupon();
			if(payment.processCoupon(couponCode))
			{
			tempdiscountedPrice = payment.getDiscountedPrice();
			discountLabel.setText("$"+Double.toString(tempdiscountedPrice));		
		    labelDiscount.setVisible(true);
		    discountLabel.setVisible(true);
			discountedPrice=tempdiscountedPrice;
					}
                       else
			{
				JOptionPane.showMessageDialog(contentPane,"Invalid Coupon","Alert",JOptionPane.ERROR_MESSAGE);
			}
			
			}
			
		});
		
		contentPane.add(buttonApplyCoupon);
		
		
		buttonByCash = new JButton("By Cash");
		buttonByCash.setBounds(30,200,100,50);
		buttonByCash.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
		   PaymentWindow.this.setVisible(false);
		   CashPaymentWindow cashPaymentWindow=new CashPaymentWindow(PaymentWindow.this,currentOrder,discountedPrice);
		   cashPaymentWindow.setVisible(true);
			}
		});
		contentPane.add(buttonByCash);
		
		buttonByCard=new JButton("By Card");
        buttonByCard.setBounds(420,200,100,50);
        buttonByCard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
		   PaymentWindow.this.setVisible(false);
		   CardPaymentWindow cardPaymentWindow=new CardPaymentWindow(PaymentWindow.this,currentOrder,discountedPrice);
		   cardPaymentWindow.setVisible(true);
			}
		});
        
        contentPane.add(buttonByCard);
        
       buttonBack=new JButton("Back");
       buttonBack.setBounds(250, 250, 100, 50);
       buttonBack.addActionListener(new ActionListener(){
    	   public void actionPerformed(ActionEvent e)
    	   {
    		PaymentWindow.this.setVisible(false);   
    		orderWindow1.setVisible(true);   
    	   }
       });
       contentPane.add(buttonBack);
    }
    public String getCoupon()
    {
    	return couponField.getText();
    }
}