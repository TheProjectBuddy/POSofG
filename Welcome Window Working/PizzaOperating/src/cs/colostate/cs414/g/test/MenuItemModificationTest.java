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
			ArrayList<MenuItem> items = menu.getAllMenuItem();
			
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
			ArrayList<MenuItem> items = menu.getAllMenuItem();
			
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

}
