import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import jdk.jfr.events.FileWriteEvent;



public class StoreManagerUI extends JFrame implements ActionListener
{
	Container container = getContentPane();
	
	JLabel errorMessage = new JLabel();
	JButton tryAgain = new JButton();
	JLabel menuName[] = new JLabel[40];
	JLabel menuPrice[] = new JLabel[40];
	JLabel menuSpecial[] = new JLabel[40];
	JLabel menuItemID[] = new JLabel[40];
	JButton modifyItem[] = new JButton[40];
	String updated[] = new String[40];
	public StoreManagerUI(int UpdatedUI) 
	{
		// TODO Auto-generated constructor stub
		
		StoreManagerAuthenticate authenticate = new StoreManagerAuthenticate(UpdatedUI); 
		
		//SwingUtilities.makeCompactGrid(getContentPane(), 100, 5, 3, 3, 3, 3);
		if(authenticate.loginAuthenticated)
		{
			try 
			{
				
				File file = new File("src/menu/item.txt");
				FileReader fileReader = new FileReader(file);
				
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				String lineRead = bufferedReader.readLine();
				int i = 0;
				while(lineRead != null)
				{
					String elements[] = lineRead.split("-");
					menuItemID[i] = new JLabel(elements[0]);
					menuName[i] = new JLabel(elements[1]);
					menuPrice[i] = new JLabel(elements[2]);
					
					if(elements.length>3)
					{
						
						menuSpecial[i] = new JLabel("(It Is Special)");
					}
					else
					{
						menuSpecial[i] = new JLabel("             ");
					} 
					modifyItem[i] = new JButton("Modify?");
					
					container.add(menuItemID[i]);
					container.add(menuSpecial[i]);
					container.add(menuName[i]);
					container.add(menuPrice[i]);
					container.add(modifyItem[i]);
					modifyItem[i].addActionListener(this);
					i++;
					lineRead = bufferedReader.readLine();
				}
				container.setLayout(new GridLayout(i, 5));
				bufferedReader.close();
				
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		else
		{
			errorMessage.setText("Login Failed");
			container.add(errorMessage);
			tryAgain.setText("Want To Try Again??");
			tryAgain.addActionListener(this);
			container.add(tryAgain);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String s[]) 
	{
		StoreManagerUI storeManagerUI = new StoreManagerUI(0);
		storeManagerUI.setVisible(true);
		storeManagerUI.setSize(1500, 700);
		storeManagerUI.setTitle("Store Manager Display");
	}
	
	
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		if(arg0.getSource() == tryAgain)
		{
			this.setVisible(false);
			this.dispose();
			StoreManagerUI storeManagerUI = new StoreManagerUI(0);
			storeManagerUI.setVisible(true);
			storeManagerUI.setSize(1500, 700);	
		}	
		
		else
		{
			
			 
			this.setVisible(false);
				for(int i=0; i<40; i++)
				{
					
					
					
					if(arg0.getSource() == modifyItem[i])
					{
						
						
						
						ModifyMenuItem modifyMenuItem = new ModifyMenuItem(menuItemID[i].getText(), menuName[i].getText(), menuPrice[i].getText(), menuSpecial[i].getText(), i);
						
						modifyMenuItem.setVisible(true);
						modifyMenuItem.setSize(500,500);
						
					}
					
					
				}
				
				
			} 
			
			
			
				
		
	}
}
