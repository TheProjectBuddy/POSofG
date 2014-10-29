package cs.colostate.cs414.g.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import cs.colostate.cs414.g.domain.*;

public class SummaryWindow extends JFrame {
	
	private JPanel contentPane;
	
	private JLabel totalPaid;
	private JLabel customerName;
	private JLabel cardNum;
	private JLabel labeltotalPaid;
	private JLabel labelcustomerName;
	private JLabel labelcardNum;
	private JLabel thanks;
	
private JButton buttonExit;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SummaryWindow(PaymentByCard payByCard,Order cOrder,double price)
	{
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		totalPaid=new JLabel("Total:");
		totalPaid.setSize(100,50);
		totalPaid.setBounds(200,40,100,50);
        contentPane.add(totalPaid); 
		customerName=new JLabel("Customer Name:");
		customerName.setSize(200, 50);
		customerName.setBounds(150, 90, 200, 50);
		contentPane.add(customerName);
		cardNum=new JLabel("Card NO:");
		cardNum.setSize(100,50);
		cardNum.setBounds(150,140,100,50);
		contentPane.add(cardNum);
		
		labeltotalPaid=new JLabel("text");
		labeltotalPaid.setSize(100,50);
		labeltotalPaid.setBounds(350,40,100,50);
		labeltotalPaid.setText(Double.toString(price));
		contentPane.add(labeltotalPaid);
		
		labelcustomerName=new JLabel("text");
		labelcustomerName.setSize(200,50);
		labelcustomerName.setBounds(350,90,200,50);
		labelcustomerName.setText(cOrder.getCustomer().getName());
		contentPane.add(labelcustomerName);
		
		labelcardNum=new JLabel("txt");
		labelcardNum.setSize(150,50);
		labelcardNum.setBounds(350,140,200,50);
		labelcardNum.setText("************"+payByCard.getCardNO());
		contentPane.add(labelcardNum);
		
		thanks=new JLabel("Thank you");
		thanks.setFont(new Font("Lucida Grande", Font.ITALIC, 26));
		thanks.setHorizontalAlignment(SwingConstants.CENTER);
		thanks.setSize(300,200);
		contentPane.add(thanks, BorderLayout.SOUTH);
		
	
	}
	
	public SummaryWindow(PaymentByCash payByCash,Order cOrder,double price)
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		totalPaid=new JLabel("Total:");
		totalPaid.setSize(100,50);
		totalPaid.setBounds(200,40,100,50);
        contentPane.add(totalPaid); 
		customerName=new JLabel("Customer Name:");
		customerName.setSize(200, 50);
		customerName.setBounds(150, 90, 200, 50);
		contentPane.add(customerName);
		
		
		labeltotalPaid=new JLabel("text");
		labeltotalPaid.setSize(100,50);
		labeltotalPaid.setBounds(350,40,100,50);
		labeltotalPaid.setText(Double.toString(price));
		contentPane.add(labeltotalPaid);
		
		labelcustomerName=new JLabel("text");
		labelcustomerName.setSize(200,50);
		labelcustomerName.setBounds(350,90,200,50);
		labelcustomerName.setText(cOrder.getCustomer().getName());
		contentPane.add(labelcustomerName);


 buttonExit =new JButton("EXIT");
	    buttonExit.setSize(100,50);
		buttonExit.setBounds(250,400,100,50);
		buttonExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		contentPane.add(buttonExit);		
	
		
		thanks=new JLabel("Thank you");
		thanks.setFont(new Font("Lucida Grande", Font.ITALIC, 26));
		thanks.setHorizontalAlignment(SwingConstants.CENTER);
		thanks.setBounds(250, 200, 250, 250);
		contentPane.add(thanks);
		
	}

}
