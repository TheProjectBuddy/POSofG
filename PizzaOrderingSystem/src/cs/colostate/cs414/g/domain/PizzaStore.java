package cs.colostate.cs414.g.domain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PizzaStore {
	public String storeName;
	public String storeLocation;
	public String storePhoneNum;
	private Menu menu;
	private ArrayList<StoreManager> managerList;
	private ArrayList<Cashier> cashierList;
	private ArrayList<Chef> chefList;

	public PizzaStore() {
		//menuList = new ArrayList<Menu>();
		managerList = new ArrayList<StoreManager>();
		cashierList = new ArrayList<Cashier>();
		chefList = new ArrayList<Chef>();
	}

	public void initialization() {
		loadEmployees();
		loadMenu();
	}

	private void loadEmployees() {
		try {
			FileInputStream inFile = new FileInputStream("employees.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inFile));
			StoreManager toAddM = null;
			Cashier toAddCash = null;
			Chef toAddC = null;
			Map<String, String> loginPwd = new HashMap<String, String>();
			String str;
			while ((str = reader.readLine()) != null && str != "") {
				String elements[] = str.split("-");
				if (elements[1].equalsIgnoreCase("Store Manager")) {
					toAddM = new StoreManager(str, this);
					toAddM.setName(elements[0]);
					loginPwd.put(elements[2], elements[3]);
					toAddM.setLoginUidPwd(loginPwd);
					managerList.add(toAddM);
				}
				else if (elements[1].equalsIgnoreCase("Cashier")) {
					toAddCash = new Cashier(str, this);
					toAddCash.setName(elements[0]);
					loginPwd.put(elements[2], elements[3]);
					toAddCash.setLoginUidPwd(loginPwd);
					cashierList.add(toAddCash);
				}
				if (elements[1].equalsIgnoreCase("Chef")) {
					toAddC = new Chef(str, this);
					toAddC.setName(elements[0]);
					loginPwd.put(elements[2], elements[3]);
					toAddC.setLoginUidPwd(loginPwd);
					chefList.add(toAddC);
				}

			}
			reader.close();
		} catch (Exception f) {
			System.out.println("Error opening manager file.");
		}
	}

	private void loadMenu() {
		String line;
		int lineNumber = 0;
		try {
			FileInputStream inFile = new FileInputStream("menu");
			BufferedReader content = new BufferedReader(new InputStreamReader(
					inFile));
			Menu loadMenu = null;
			while ((line = content.readLine()) != null) {
				if (lineNumber == 0) {
					String elements[] = line.split("-");
					loadMenu = new Menu(elements[0], new StoreManager(
							elements[1], this));
				menu= loadMenu;
					lineNumber++;
				} else {
					if (!line.equals("NEXT")) {
						String elements[] = line.split("-");
						MenuItem item = new MenuItem(elements[0],
								Float.parseFloat(elements[1]));
						loadMenu.addMenuItem(item);
					} else {
						lineNumber = 0;
					}
				}
			}
			content.close();
		} catch (Exception e) {
			System.out.println("Error opening menu" + e.getMessage());
		}
	}

	public void save() {
		// TODO Auto-generated method stub

	}

	public Menu getCurrentMenu() {
	
		return menu; }

	public void addMenu(Menu menu) {
		this.menu=menu;

	}

	//public void addMenuItem(String name, double price) {
	

	//}

	//public String getCurrentMenuName() {
		// TODO Auto-generated method stub
	//	return null;
	//}

	public ArrayList<String> getCurrentMenuItemNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Double> getCurrentMenuItemPrices() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setMenu(Menu menu2) {
		// TODO Auto-generated method stub
		
	}
}