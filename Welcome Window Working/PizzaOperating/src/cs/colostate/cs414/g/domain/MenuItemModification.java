package cs.colostate.cs414.g.domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MenuItemModification
{
	public void addNewItem(JTextField nameItem, JTextField itemPrice, JTextField toppingPrice, JRadioButton pizza, JRadioButton toppings, JRadioButton others, JCheckBox box, JTextField prepTime, JTextField cookTime, JTextField overSpace) 
	{
		File file = new File("menu.txt");
		try 
		{
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String LineRead = bufferedReader.readLine();
			int count = 0;
			String[] updated = new String[100];
			while(LineRead != null)
			{
				
				
				updated[count] = LineRead;
				count++;
				LineRead = bufferedReader.readLine();
			}
			bufferedReader.close();
			
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for(int i=0; i<100; i++)
			{
				
				if(updated[i] != null) bufferedWriter.write(updated[i]+"\n");
				
			}
			String lineToWrite;
			String starting = null ;
			if(toppings.isSelected()) starting = "1";
			else if(others.isSelected()) starting = "2";
			String name = nameItem.getText();
			String price = itemPrice.getText();
			
			String end = "|0";
			if(box.isSelected()) end = "|1";
			
			if(starting.equals("2")) lineToWrite = starting+"|"+name+"|"+price+"|"+prepTime.getText()+"|"+cookTime.getText()+"|"+overSpace.getText()+end;
			else lineToWrite = starting+"|"+name;
			count++;
			System.out.println(lineToWrite+"|"+count);
			bufferedWriter.write(lineToWrite+"|"+count+"\n");
			bufferedWriter.close();
		}
		catch(Exception e)
		{
			
		}
	}

	public void modifyItem(JTextField nameItem, JTextField itemPrice,
			JTextField toppingPrice, JRadioButton pizza, JRadioButton topping,
			JRadioButton others, JCheckBox box, JTextField prepTime,
			JTextField cookTime, JTextField ovenSpace, int itemID) {
		// TODO Auto-generated method stub
		File file = new File("menu.txt");
		try 
		{
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String LineRead = bufferedReader.readLine();
			int count = 0;
			String[] updated = new String[100];
			while(LineRead != null)
			{
				
				
				updated[count] = LineRead;
				count++;
				LineRead = bufferedReader.readLine();
			}
			bufferedReader.close();
			
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for(int i=0; i<100; i++)
			{
				
				if(updated[i] != null) 
					{
						if(i == (itemID-1))
						{
							String lineToWrite;
							String starting = null ;
							if(topping.isSelected()) starting = "1";
							else if(others.isSelected()) starting = "2";
							String name = nameItem.getText();
							String price = itemPrice.getText();
							
							String end = "|0";
							if(box.isSelected()) end = "|1";
							
							if(starting.equals("2")) lineToWrite = starting+"|"+name+"|"+price+"|"+prepTime.getText()+"|"+cookTime.getText()+"|"+ovenSpace.getText()+end;
							else lineToWrite = starting+"|"+name;
							
							System.out.println(lineToWrite+"|"+itemID);
							bufferedWriter.write(lineToWrite+"|"+itemID+"\n");
							
						}
						else
						{
							bufferedWriter.write(updated[i]+"\n");
						}
						
					}
				
				
			}
			bufferedWriter.close();
			
		}
		catch(Exception e)
		{
			
		}
	}
}
