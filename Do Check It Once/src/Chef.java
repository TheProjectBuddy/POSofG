
public class Chef
{

	String name;
	String userName;
	String password;
	
	public Chef(String name, String userName, String password) 
	{
		// TODO Auto-generated constructor stub
		this.name = name;
		this.userName = userName;
		this.password = password;
	}

	private String getName() 
	{
		return name;
	}

	private void setName(String name)
	{
		this.name = name;
	}

	private String getUserName()
	{
		return userName;
	}

	private void setUserName(String userName)
	{
		this.userName = userName;
	}

	private String getPassword()
	{
		return password;
	}

	private void setPassword(String password) 
	{
		this.password = password;
	}
	
}
