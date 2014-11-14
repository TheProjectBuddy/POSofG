package cs.colostate.cs414.a5.g.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import cs.colostate.cs414.g.domain.Menu;

public class MenuUtil {

	public static Menu menuList = null;
	
	public static Menu getMenuItems(){

		FileInputStream menuFileStream = null;
		try {
			File file = new File("menu"); 
			menuFileStream = new FileInputStream(file);
		}
		catch (FileNotFoundException e) {
			System.err.println("Unable to open menu file. Exiting...");
			System.exit(1);
		}
		
		try {
			menuList = new Menu(menuFileStream);
		}
		catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace(System.err);
			System.exit(1);
		}
		return menuList;
	}
}
