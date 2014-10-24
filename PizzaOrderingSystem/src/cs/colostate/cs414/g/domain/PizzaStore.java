package cs.colostate.cs414.g.domain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PizzaStore {
	public String storeName ;
	public String storeLocation ;
	public String storePhoneNum ;
	private ArrayList<Menu> menuList;
	private ArrayList<StoreManager> managerList;
	private ArrayList<Cashier> cashierList;
	private ArrayList<Chef> chefList;

	public PizzaStore() {
		menuList = new ArrayList<Menu>();
		managerList = new ArrayList<StoreManager>();
	}

	public void initialization() {
		loadManagers();
		loadMenu();
	}

	private void loadManagers() {
		try {
			FileInputStream inFile = new FileInputStream("managers.txt");// TO BE CREATED
			BufferedReader reader = new BufferedReader(new InputStreamReader(inFile));
			StoreManager toAdd = null;
			String str;
			while((str = reader.readLine()) != null && str != "") {
				toAdd = new StoreManager(str, this);
				managerList.add(toAdd);
			}
			System.out.println(managerList.toString());
			reader.close();
		} catch(Exception f) {
			System.out.println("Error opening manager file.");
		}
	}
	
	private void loadMenu() {
		String line;
		int lineNumber = 0;
		try {
			FileInputStream inFile = new FileInputStream("menu");//TO BE CREATED
			BufferedReader content = new BufferedReader(new InputStreamReader(inFile));
			Menu loadMenu = null;
			while((line = content.readLine()) != null){
				if(lineNumber == 0) {
					String elements[] = line.split("-");
					loadMenu = new Menu(elements[0], new StoreManager(elements[1], this));
					menuList.add(loadMenu);
					lineNumber ++;
				}else {
					if(!line.equals("NEXT")){
						String elements[] = line.split("-");
						MenuItem item = new MenuItem(elements[0], Float.parseFloat(elements[1]));
						loadMenu.addMenuItem(item);
					} else {
						lineNumber = 0;
					}
				}
			}
			content.close();
		} catch (Exception e) {
			System.out.println("Error opening menu"+e.getMessage());
		}
	}
	public void save() {
		// TODO Auto-generated method stub
		
	}

	public Menu getCurrentMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	public void addMenuItem(String name, double price) {
		// TODO Auto-generated method stub
		
	}

	public String getCurrentMenuName() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getCurrentMenuItemNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Double> getCurrentMenuItemPrices() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMenuList(ArrayList<Menu> menuList2) {
		// TODO Auto-generated method stub
		
	}
	
}
