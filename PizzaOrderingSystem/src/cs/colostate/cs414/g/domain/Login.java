package cs.colostate.cs414.g.domain;

public class Login {
	
	PizzaStore theStore;

	public static boolean authenticate(String username, String password) {
		//To be changed to manager cashier chef
	     if (username.equals("varsha") && password.equals("secret")) {
	            return true;
	        }
	        return false;
	    }
	}
