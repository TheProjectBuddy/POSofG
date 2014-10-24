package cs.colostate.cs414.g.domain;

public class Login {
	
	PizzaStore theStore;

	public static boolean authenticate(String username, String password) {
	     if (username.equals("bob") && password.equals("secret")) {
	            return true;
	        }
	        return false;
	    }
	}
