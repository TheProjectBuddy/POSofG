import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class ModifyMenuItem extends JFrame implements ActionListener
{
	String lineToWrite;
	String updated[] = new String[40];
	JButton update;
	String ID;
	JTextField newName = new JTextField();
	JTextField newPrice = new JTextField();
	JCheckBox box = new JCheckBox("Is it Special?");
	int i;
	public ModifyMenuItem(String menuItemID, String  menuName, String  menuPrice , String  menuSpecial, int index) 
	{
		// TODO Auto-generated constructor stub
		i = index;
		ID = menuItemID;
		lineToWrite = menuItemID+"-"+menuName+"-"+menuPrice;
		if(menuSpecial.equals("(It Is Special)")) lineToWrite = lineToWrite+"-"+"Special";
		Container container = getContentPane();
		container.setLayout(new GridLayout(3, 3));
		setTitle("Modify It");
		JLabel itemNameDesc = new JLabel("Item Name: ");
		JLabel itemPriceDesc = new JLabel("Item Price: ");
		
		JLabel actualName = new JLabel(menuName);
		JLabel actualPrice = new JLabel(menuPrice);
		
		if(menuSpecial.equals("(It Is Special)")) box.setSelected(true);
		update = new JButton("Update Changes");
		JButton cancel = new JButton("Revoke Changes");
		container.add(itemNameDesc);
		container.add(actualName);
		container.add(newName);
		container.add(itemPriceDesc);
		container.add(actualPrice);
		container.add(newPrice);
		container.add(box);
		container.add(update);
		update.addActionListener(this);
		container.add(cancel);
		
		File file = new File("src/menu/item.txt");
		try 
		{
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String LineRead = bufferedReader.readLine();
			int count = 0;
			while(LineRead != null)
			{
				
				updated[count] = LineRead;
				count++;
				LineRead = bufferedReader.readLine();
			}
			bufferedReader.close();
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
		if(arg0.getSource() == update)
		{
			try 
			{
				File file = new File("src/menu/item.txt");
				FileWriter fileWriter = new FileWriter(file);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				lineToWrite = ID+"-"+newName.getText()+"-"+newPrice.getText();
				if(box.isSelected())
				{
					lineToWrite = lineToWrite+"-"+"Special";
				}
				for(int in=0; in<40; in++)
				{
					
					
					
					if(i == in)
					{
						
						
						
						
						updated[in] = lineToWrite;
						System.out.println(lineToWrite);
						
						
					}
					if(updated[in] != null) bufferedWriter.write(updated[in]+"\n");
					
				}
				bufferedWriter.close();
			}
			catch(Exception e)
			{
				
			}
			this.setVisible(false);
			StoreManagerUI storeManagerUI = new StoreManagerUI(1);
			storeManagerUI.setVisible(true);
			storeManagerUI.setSize(1500, 700);	
			
				
		}
		
	}
}
