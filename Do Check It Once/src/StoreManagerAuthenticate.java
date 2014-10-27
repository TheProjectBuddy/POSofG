import javax.swing.JOptionPane;


public class StoreManagerAuthenticate
{
	String userName;
	String password = null;
	boolean loginAuthenticated = false;

	public StoreManagerAuthenticate(int Update) 
	{
		
		if(Update == 1)
		{
			loginAuthenticated = true;
		}
		else
		{
		userName = JOptionPane.showInputDialog("Enter Your StoreManagerID", "SecureID");
		
		if(!userName.isEmpty())
		{
			if("Harshil".matches(JOptionPane.showInputDialog("Hi,"+userName+" Enter Your StoreManagerPassword", "SecurePassword")))
			{
				loginAuthenticated = true;
			}
		}
		}
	}
	
}
