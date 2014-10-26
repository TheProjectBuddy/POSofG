package cs.colostate.cs414.g.ui;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import cs.colostate.cs414.g.domain.*;
import cs.colostate.cs414.g.domain.Menu;
import cs.colostate.cs414.g.domain.MenuItem;

public class Kiosk {
	public static int kioskID = 0;
	private static GridBagConstraints gBC = new GridBagConstraints();
	private static JFrame frame = new JFrame("My Pizzeria Kiosk: "
			+ kioskID);
	private static Order o = new Order();
	private static ArrayList<StoreManager> managerList = new ArrayList<StoreManager>();
	private static Menu menu;
	private static JLabel currentMenu;
	private static JDesktopPane desktop;
	private static ArrayList<JButton> menuItemButtons = new ArrayList<JButton>();
	private static ArrayList<JButton> orderButtons = new ArrayList<JButton>();
	private static KioskFacade kFacade = new KioskFacade();
	public static PizzaStore theStore = kFacade.getStore();
	private static String[] args;

	private static void createAndShowGUI(final String[] args) {
		Color background = new Color(153,204,255);
		final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		Random generator = new Random();
		o.orderID = generator.nextInt();
		File txtFile = new File(Integer.toString(o.orderID));
		while (txtFile.exists()) { 
			o.orderID = generator.nextInt();
			txtFile = new File(Integer.toString(o.orderID));
		}

		desktop = new JDesktopPane();
		frame.setContentPane(desktop);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		desktop.setBackground(background);
		frame.setMinimumSize(size);
		desktop.setLayout(new GridBagLayout());
		gBC.fill = GridBagConstraints.HORIZONTAL;

		loadMenu();

		gBC.gridx = 0;
		gBC.gridy = -1;
		gBC.weightx = 0.5;
		gBC.weighty = 0.25;

		JLabel loginLabel = new JLabel("Manager Name");
		final JTextField loginTextField = new JTextField(15);
		JButton submitLogInButton = new JButton("Submit");
		submitLogInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean found = false;
				for (StoreManager manager : managerList) {
					if (manager.getName() == loginTextField.getText()) {
						found = true;
						showManagerWindow(manager);
					}
				}
				if (!found) {
					StoreManager manager = new StoreManager("Varsha Arun",
							theStore);
					showManagerWindow(manager);
				}
			}
		});
		desktop.add(loginLabel);
		desktop.add(loginTextField);
		desktop.add(submitLogInButton);
		currentMenu = new JLabel("Current Menu: " + kFacade.getMenuName());
		desktop.add(currentMenu);
		gBC.gridy += 1;
		
		drawMenuItemButtons();
		frame.pack();
		frame.setVisible(true);
	}

	public static void restartProgram(String[] args) {
		StringBuilder cmd = new StringBuilder();
		cmd.append(System.getProperty("java.home") + File.separator + "bin"
				+ File.separator + "java ");
		for (String jvmArg : ManagementFactory.getRuntimeMXBean()
				.getInputArguments()) {
			cmd.append(jvmArg + " ");
		}
		cmd.append("-cp ")
				.append(ManagementFactory.getRuntimeMXBean().getClassPath())
				.append(" ");
		cmd.append(Kiosk.class.getName()).append(" ");
		for (String arg : args) {
			cmd.append(arg).append(" ");
		}
		try {
			Runtime.getRuntime().exec(cmd.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	private static void loadMenu() {
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
					kFacade.addNewMenu(elements[0], elements[1]);
					loadMenu = new Menu(elements[0], new StoreManager(
							elements[1], theStore));
					menu = loadMenu;
					lineNumber++;
				} else {
					if (!line.equals("NEXT")) {
						String elements[] = line.split("-");
						MenuItem item = new MenuItem(elements[0],
								Double.parseDouble(elements[1]));
						loadMenu.addMenuItem(item);
					} else {
						lineNumber = 0;
					}
				}
			}
			content.close();
			theStore.addMenu(menu);
			//menu = menuList.get(0);

		} catch (Exception e) {
			System.out.println("Error opening menu"+e.getLocalizedMessage());
		}

	}

	private static void clearMenuItemButtons() {
		gBC.gridx = 0;
		gBC.gridy = 0;
		gBC.weightx = 0.5;
		gBC.weighty = 0.25;

		for (Component obj : menuItemButtons)
			desktop.remove(obj);
		for (Component obj : orderButtons)
			desktop.remove(obj);

	}

	private static void drawMenuItemButtons() {
		clearMenuItemButtons();

		if (kFacade.getMenuName() != null) {
			ArrayList<String> menuItemNames = kFacade.getCurrentMenuItemNames();
			ArrayList<Double> menuItemCosts = kFacade
					.getCurrentMenuItemPrices();
			for (int i = 0; i < menuItemNames.size(); i++) {
				final MenuItem item = kFacade
						.getOrderItem(menuItemNames.get(i));
				JButton b = new JButton(menuItemNames.get(i) + "\n $"
						+ menuItemCosts.get(i));
				b.setActionCommand(menuItemNames.get(i));
				b.setPreferredSize(new Dimension(150, 100));
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						o.addItemToOrder(item); 
					}
				});
				gBC.gridx = i % 5;
				if ((i % 5) == 0)
					gBC.gridy += 1;
				desktop.add(b, gBC);
				menuItemButtons.add(b);
			}
		}
		drawOrderButtons();
	}

	public static void drawOrderButtons() {
		final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		frame.getContentPane().add(new JSeparator());
		JButton viewOrder = new JButton("View Order");
		JButton placeOrder = new JButton("Place Order");
		viewOrder.setPreferredSize(new Dimension(100, 100));
		viewOrder.setBackground(Color.green);
		placeOrder.setPreferredSize(new Dimension(100, 100));
		placeOrder.setBackground(Color.blue);

		viewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JInternalFrame internalFrame = new JInternalFrame(
						"Order Summary", false, true, false, false);
				internalFrame.setBounds(0, 50, 500, size.height);
				internalFrame.setBackground(new Color(225, 225, 255));
				gBC.ipady = size.height - 100;
				gBC.weightx = 0;
				gBC.gridwidth = 4;
				gBC.gridx = 0;
				gBC.gridy = 0;

				JLabel receipt = getReceipt();
				internalFrame.add(receipt);
				internalFrame.show();
				frame.add(internalFrame, gBC);
				internalFrame.setVisible(true);
				try {
					internalFrame.setSelected(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});

		placeOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDesktopPane desktop2 = new JDesktopPane();
				desktop2.setLayout(new GridBagLayout());
				JLabel receipt = getReceipt();
				gBC.ipadx = size.width / 2;
				gBC.gridheight = 8;
				gBC.gridx = 0;
				gBC.gridy = 0;
				desktop2.add(receipt, gBC);

				JLabel txt = new JLabel(
						"<html><h1>Fill in all fields: </h1></html>");
				gBC.gridheight = 1;
				gBC.gridx = 1;
				desktop2.add(txt, gBC);

				final JTextField name = new JTextField("First and Last Name");
				gBC.gridy = 1;
				desktop2.add(name, gBC);

				final JTextField address = new JTextField("Address");
				gBC.gridy = 2;
				desktop2.add(address, gBC);

				final JTextField phone = new JTextField("Phone Number");
				gBC.gridy = 3;
				desktop2.add(phone, gBC);

				txt = new JLabel("<html><h3>Payment Information: </h3></html>");
				gBC.gridy = 4;
				desktop2.add(txt, gBC);

				final JTextField cardName = new JTextField("Name on Card");
				gBC.gridy = 5;
				desktop2.add(cardName, gBC);

				final JTextField cardNum = new JTextField("Credit Card Number",
						16);
				gBC.gridy = 6;
				desktop2.add(cardNum, gBC);

				final JTextField expDate = new JTextField("Expiration Date");
				gBC.gridy = 7;
				desktop2.add(expDate, gBC);

				final JButton place = new JButton("Place Order");
				place.setBackground(Color.green);
				place.setPreferredSize(new Dimension(150, 100));
				gBC.gridy = 8;

				place.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (name.getText().equals("First and Last Name")
								|| address.getText().equals("Address")
								|| phone.getText().equals("Phone Number")
								|| cardName.getText().equals("Name on Card")
								|| cardNum.getText().equals(
										"Credit Card Number")
								|| expDate.getText().equals("Expiration Date"))
							JOptionPane.showOptionDialog(frame,
									"You must fill in all fields.", "Error",
									JOptionPane.DEFAULT_OPTION,
									JOptionPane.ERROR_MESSAGE, null, null, e);
						else {
							if (phone.getText().length() != 10)
								JOptionPane
										.showOptionDialog(
												frame,
												"Invalid phone number. Use Format: 9991234567.",
												"Error",
												JOptionPane.DEFAULT_OPTION,
												JOptionPane.ERROR_MESSAGE,
												null, null, e);
							else {
								if (cardNum.getText().length() != 16)
									JOptionPane
											.showOptionDialog(
													frame,
													"Invalid Card Number. Use Format: 1234567890123456",
													"Error",
													JOptionPane.DEFAULT_OPTION,
													JOptionPane.ERROR_MESSAGE,
													null, null, e);
								else {

									JOptionPane.showOptionDialog(frame,
											"Your order has been placed.",
											"Order Placed",
											JOptionPane.DEFAULT_OPTION,
											JOptionPane.PLAIN_MESSAGE, null,
											null, e);

									String order = "";
									ArrayList<OrderItem> orderItems = o
											.getOrderList();
									for (int i = 0; i < orderItems.size(); i++) {
										order += orderItems.get(i).getItem().name
												+ "\t\t"
												+ orderItems.get(i)
														.getQuantity() + "\n";
									}
									FileWriter fstream;
									try {
										fstream = new FileWriter(Integer
												.toString(o.orderID) + ".POS");
										BufferedWriter out = new BufferedWriter(
												fstream);
										out.write(name.getText() + "\n"
												+ address.getText() + "\n"
												+ phone.getText() + "\n"
												+ order + "\n\n");
										out.close();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
									restartProgram(args);
								}
							}
						}
					}
				});

				desktop2.add(place, gBC);

				desktop2.setVisible(true);
				frame.setContentPane(desktop2);
				frame.setVisible(true);
			}
		});
		gBC.gridy += 1;
		gBC.gridx = 0;
		desktop.add(viewOrder, gBC);
		orderButtons.add(viewOrder);
		gBC.gridx = 1;
		desktop.add(placeOrder, gBC);
		orderButtons.add(placeOrder);
	}

	private static void showManagerWindow(final StoreManager manager) {
		final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

		JInternalFrame internalFrame = new JInternalFrame("Manage", false,
				true, false, false);

		InternalFrameListener i = new InternalFrameListener() {

			public void internalFrameClosing(InternalFrameEvent e) {
				currentMenu.setText("Current Menu: " + kFacade.getMenuName());
				kFacade.save();
				drawMenuItemButtons();
			}

			public void internalFrameActivated(InternalFrameEvent e) {
			}

			public void internalFrameClosed(InternalFrameEvent e) {
			}

			public void internalFrameDeactivated(InternalFrameEvent e) {
			}

			public void internalFrameDeiconified(InternalFrameEvent e) {
			}

			public void internalFrameIconified(InternalFrameEvent e) {
			}

			public void internalFrameOpened(InternalFrameEvent e) {
			}
		};
		internalFrame.addInternalFrameListener(i);
		internalFrame.setBounds(0, 50, 500, size.height - 100);
		internalFrame.setBackground(new Color(200, 200, 200));
		internalFrame.add(getCreateMenuGUI(manager));

		internalFrame.show();
		frame.add(internalFrame);
		internalFrame.setVisible(true);

		try {
			internalFrame.setSelected(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}

	}

	public static JPanel getCreateMenuGUI(final StoreManager manager) {
		final JPanel p = new JPanel();
		GridLayout layout = new GridLayout(3, 3);
		p.setLayout(layout);

		JLabel addMenuLabel = new JLabel("Add Menu");
		final JTextField addMenuTextField = new JTextField(15);
		JButton addMenuButton = new JButton("Create Menu");
		addMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean found = false;
				
					if (menu.menuName.equalsIgnoreCase(addMenuTextField
							.getText())) {
						found = true;
					}
				
				if (!found) {
					menu = new Menu(addMenuTextField.getText(), manager);
					kFacade.addMenu(menu);
					//menuList.add(menu);
					theStore.addMenu(menu);
				}
				addMenuTextField.setText("Menu has been changed");
			}
		});

		p.add(addMenuLabel);
		p.add(addMenuTextField);
		p.add(addMenuButton);
		for (Object obj : getCurrentMenuItemCountGUI())
			p.add((Component) obj);
		for (Object obj : addMenuItemInterface())
			p.add((Component) obj);
		return p;
	}

	public static ArrayList<Object> getCurrentMenuItemCountGUI() {
		ArrayList<Object> p = new ArrayList<Object>();
		JLabel title = new JLabel("Number of Menu Items");
		p.add(title);
		JLabel currentNumberMenuItemsLabel;
		if (kFacade.getCurrentMenuItemNames().size() > 0)
			currentNumberMenuItemsLabel = new JLabel(""
					+ kFacade.getCurrentMenuItemNames().size());
		else
			currentNumberMenuItemsLabel = new JLabel("0");
		p.add(currentNumberMenuItemsLabel);
		p.add(new JLabel());
		return p;
	}

	public static ArrayList<Object> addMenuItemInterface() {
		ArrayList<Object> p = new ArrayList<Object>();
		JLabel title = new JLabel("Add Item (item - price)");
		p.add(title);
		final JTextField inputForm = new JTextField(15);
		p.add(inputForm);
		JButton submitButton = new JButton("Add New Item");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string = inputForm.getText();
				String elements[] = string.split("-");
				try {
					elements[0] = elements[0].trim();
					elements[1] = elements[1].trim();
					if (elements[1].startsWith("$"))
						kFacade.addMenuItem(elements[0],
								Double.parseDouble(elements[1].substring(1)));
					else
						kFacade.addMenuItem(elements[0],
								Double.parseDouble(elements[1]));
					inputForm.setText(elements[0] + " Added!");
				} catch (Exception exc) {
					System.out.println(exc);
					inputForm.setText("Error adding item!");
				}
			}
		});
		p.add(submitButton);

		drawMenuItemButtons();
		return p;
	}

	public static JLabel getReceipt() {
		ArrayList<OrderItem> orderItems = o.getOrderList();
		String text = "<html><center><h1>ORDER SUMMARY</h1><br>";
		text += "<table>" + "<tr>" + "<td>Item Name</td>" + "<td>Quantity</td>"
				+ "<td>Unit Price</td>" + "<td>Total Price</td>" + "</tr>";
		for (int i = 0; i < orderItems.size(); i++) {
			double lineTotal = (orderItems.get(i).getQuantity())
					* (orderItems.get(i).getItem().price);
			text += "<tr><td>" + orderItems.get(i).getItem().name + "</td>"
					+ "<td>" + orderItems.get(i).getQuantity() + "</td>"
					+ "<td>$" + orderItems.get(i).getItem().price + "</td>"
					+ "<td>$" + lineTotal + "</td></tr><br>";
		}
		DecimalFormat formatter = new DecimalFormat("#0.00");
		String tot = formatter.format(o.total);
		String payments = formatter.format(o.total - o.amountDue);
		String amountDue = formatter.format(o.amountDue);
		text += "<tr><td></td><td></td><td></td><td>Total: $" + tot
				+ "</td></tr>"
				+ "<tr><td></td><td>Payments:</td><td></td><td>$" + payments
				+ "</td></tr>"
				+ "<tr><td></td><td>Amount Due:</td><td></td><td>$" + amountDue
				+ "</td><tr></table></center></html>";
		JLabel recipt = new JLabel(text, JLabel.CENTER);
		return recipt;
	}

	public static void main(final String[] args1) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				args = args1;
				final JFrame frame = new JFrame("My Pizzeria Employee Login");
		        final JButton btnLogin = new JButton("Click to login");

		        btnLogin.addActionListener(
		                new ActionListener(){
		                    public void actionPerformed(ActionEvent e) {
		                        LoginDialog loginDlg = new LoginDialog(frame);
		                        loginDlg.setVisible(true);
		                        // if logon successfully
		                        if(loginDlg.isSucceeded()){
		                            btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
		                            createAndShowGUI(args);
		                        }
		                    }
		                });

		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setSize(300, 100);
		        frame.setLayout(new FlowLayout());
		        frame.getContentPane().add(btnLogin);
		        frame.setVisible(true);
			}
		});
	}
}
