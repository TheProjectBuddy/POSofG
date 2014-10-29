package cs.colostate.cs414.g.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

import cs.colostate.cs414.g.domain.Menu;



public class MenuTest {
	
	public void setUp() throws Exception {
		 
		
	}
	
	@Test
	public void testFileExist() {
		try
		{
			File file = new File("menuTest.txt");
			InputStream inputStream = new FileInputStream(file);;
			Menu menu = new Menu(inputStream);
			
			assertTrue(menu.getAllMenuItem().size() == 4);
			assertTrue(menu.getToppings().size() == 1);
			
			assertTrue(menu.getNumberOfItems() == 4);
		}
		catch(Exception e)
		{
			System.out.println("Error");
		}
		//fail("Not yet implemented");
		
	}
	
	@Test
	public void testParsingError()
	{
		try
		{
			File file = new File("parseErrorMenuTest.txt");
			InputStream inputStream = new FileInputStream(file);;
			Menu menu = new Menu(inputStream);
			
			
		}
		catch(Exception e)
		{
			assertTrue(e.getMessage().equals("Unable to parse menu file -- error found."));
		}
		
	}

}
