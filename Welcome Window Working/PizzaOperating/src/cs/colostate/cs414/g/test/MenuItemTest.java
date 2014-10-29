package cs.colostate.cs414.g.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import org.junit.Test;

import cs.colostate.cs414.g.domain.Menu;
import cs.colostate.cs414.g.domain.MenuItem;

public class MenuItemTest {

	@Test
	public void test() 
	{
		
		try
		{
			File file = new File("menuTest.txt");
			InputStream inputStream;
			inputStream = new FileInputStream(file);
			Menu menu = new Menu(inputStream);
			ArrayList<MenuItem> items = menu.getAllMenuItem();
			
			assertTrue(items.get(0).toString().equals("SMALL Pizza"));
			assertTrue((items.get(0).itemID) == 1);
			assertTrue((items.get(0).price) == 1.5);
			assertTrue((items.get(0).special) == 0);
			assertTrue((items.get(0).getPrepTime()) == 8.0);
			assertTrue((items.get(0).getCookTime()) == 13.0 );
			assertTrue((items.get(0).getOvenSpace()) == 1);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
