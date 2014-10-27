package cs.colostate.cs414.g.util;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import cs.colostate.cs414.g.domain.Login;
import cs.colostate.cs414.g.domain.Menu;
import cs.colostate.cs414.g.domain.PhoneOrder;
//import cs.colostate.cs414.g.ui.LoginDialog;
import cs.colostate.cs414.g.ui.WelcomeWindow;

public class MainUtil {

	public static void run(final PhoneOrder phoneOrder, final Menu menu, final Stage startStage) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String userName, password;
					boolean userWhileLoop = true;
					boolean passWhileLoop = true;
					while(userWhileLoop)
					{
						userName = JOptionPane.showInputDialog(null, "Hello, Kindly Enter Your Username", "Login Details - Username", 0);
						if(Login.checkExistingUsername(userName)) 
						{
							userWhileLoop = false;
							while(passWhileLoop)
							{
								password = JOptionPane.showInputDialog(null, "Hello, Kindly Enter Your Password", "Login Details - Password", 0);
								if(Login.authenticate(password))
								{
									passWhileLoop = false;
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Wrong Password.. Try Again");
									passWhileLoop = true;
								}
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Wrong Username.. Try Again");
							userWhileLoop = true;
						}
					}
					WelcomeWindow frame = new WelcomeWindow(phoneOrder, menu, startStage);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
