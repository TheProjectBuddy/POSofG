package cs.colostate.cs414.g.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.Test;

import cs.colostate.cs414.g.domain.Menu;
import cs.colostate.cs414.g.domain.MenuItem;
import cs.colostate.cs414.g.domain.MenuItemModification;

public class MenuItemModificationTest {

	@Test
	public void testAddNewItem() {
		JTextField nameItem = new JTextField("Burger"); 
		JTextField itemPrice = new JTextField("5.25");
		JTextField toppingPrice = new JTextField("1.5");
		JRadioButton pizza = new JRadioButton("Pizza");
		JRadioButton toppings = new JRadioButton("toppings"); 
		JRadioButton others = new JRadioButton("others");
		others.setSelected(true);
		JCheckBox box = new JCheckBox("Special");
		box.setSelected(true);
		JTextField prepTime = new JTextField("5.0");
		JTextField cookTime = new JTextField("5.0"); 
		JTextField overSpace = new JTextField("5");
		
		MenuItemModification itemModification = new MenuItemModification();
		itemModification.addNewItem(nameItem, itemPrice, toppingPrice, pizza, toppings, others, box, prepTime, cookTime, overSpace);
		
		try
		{
			File file = new File("menu.txt");
			InputStream inputStream;
			inputStream = new FileInputStream(file);
			Menu menu = new Menu(inputStream);
			ArrayList<MenuItem> items = menu.getFoodItems();
			
			assertTrue(items.get(items.size() - 1).toString().equals("Burger"));
			//assertTrue((items.get(items.size() - 1).itemID) == 15);
			assertTrue((items.get(items.size() - 1).price) == 5.25);
			assertTrue((items.get(items.size() - 1).special) == 1);
			assertTrue((items.get(items.size() - 1).getPrepTime()) == 5.0);
			assertTrue((items.get(items.size() - 1).getCookTime()) == 5.0 );
			assertTrue((items.get(items.size() - 1).getOvenSpace()) == 5);
		}
		catch(Exception e)
		{
			fail("Test is failed");
		}
	}
	
	@Test
	public void testModifyItem()
	{
		JTextField nameItem = new JTextField("Burger"); 
		JTextField itemPrice = new JTextField("5.5");
		JTextField toppingPrice = new JTextField("1.5");
		JRadioButton pizza = new JRadioButton("Pizza");
		JRadioButton toppings = new JRadioButton("toppings"); 
		JRadioButton others = new JRadioButton("others");
		others.setSelected(true);
		JCheckBox box = new JCheckBox("Special");
		box.setSelected(false);
		JTextField prepTime = new JTextField("5.0");
		JTextField cookTime = new JTextField("5.0"); 
		JTextField overSpace = new JTextField("5");
		
		
		MenuItemModification itemModification = new MenuItemModification();
		
		
		try
		{
			File file = new File("menu.txt");
			InputStream inputStream;
			inputStream = new FileInputStream(file);
			Menu menu = new Menu(inputStream);
			ArrayList<MenuItem> items = menu.getFoodItems();
			
			itemModification.modifyItem(nameItem, itemPrice, toppingPrice, pizza, toppings, others, box, prepTime, cookTime, overSpace, items.get(items.size() - 1).itemID);
			//assertTrue(items.get(items.size() - 2).toString().equals("Burger"));
			//assertTrue((items.get(items.size() - 1).itemID) == 15);
			assertTrue((items.get(items.size() - 2).price) == 5.5);
			assertTrue((items.get(items.size() - 2).special) == 0);
			assertTrue((items.get(items.size() - 2).getPrepTime()) == 5.0);
			assertTrue((items.get(items.size() - 2).getCookTime()) == 5.0 );
			//assertTrue((items.get(items.size() - 2).getOvenSpace()) == 5);
		}
		catch(Exception e)
		{
			fail("Test is failed");
		}
		
	}
	
	@Test
	public void testDeleteItem()
	{
		MenuItemModification itemModification = new MenuItemModification();
		
		
		try
		{
			File file = new File("menu.txt");
			InputStream inputStream;
			inputStream = new FileInputStream(file);
			Menu menu = new Menu(inputStream);
			ArrayList<MenuItem> items = menu.getFoodItems();
			System.out.println(items.get(items.size() - 1).itemID);
			
			itemModification.deleteItem(items.get(items.size() - 1).itemID);
			
			File fileU = new File("menu.txt");
			InputStream inputStreamU;
			inputStreamU = new FileInputStream(fileU);
			Menu menuU = new Menu(inputStreamU);
			ArrayList<MenuItem> itemsU = menuU.getFoodItems();
			assertTrue((itemsU.get(itemsU.size() - 1).itemID) == (items.get(items.size() - 1).itemID - 1) );
		}
		catch(Exception e)
		{
			fail("Test is failed");
		}
	}
	
	@Test
	public void testModifyPizza()
	{
		JTextField nameItem = new JTextField("SMALL Pizza"); 
		JTextField itemPrice = new JTextField("25");
		JTextField toppingPrice= new JTextField("1"); 
		JCheckBox box = new JCheckBox();
		box.setSelected(true);
		int itemID = 1;
		String prepTime = "15.2";
		String cookTime = "2.5";
		String ovenSpace = "3";
		
MenuItemModification itemModification = new MenuItemModification();
		
		
		try
		{
			File file = new File("menu.txt");
			InputStream inputStream;
			inputStream = new FileInputStream(file);
			Menu menu = new Menu(inputStream);
			ArrayList<MenuItem> items = menu.getFoodItems();
			
			itemModification.modifyPizza(nameItem, itemPrice, toppingPrice, box, itemID, prepTime, cookTime, ovenSpace);
			assertTrue(items.get(0).toString().equals("SMALL Pizza"));
			assertTrue((items.get(0).itemID) == 1);
			assertTrue((items.get(0).price) == 25);
			//assertTrue((items.get(0).special) == 1);
			assertTrue((items.get(0).getPrepTime()) == 15.2);
			assertTrue((items.get(0).getCookTime()) == 2.5 );
			assertTrue((items.get(0).getOvenSpace()) == 3);
		}
		catch(Exception e)
		{
			fail("Test is failed");
		}
	}
	

}
