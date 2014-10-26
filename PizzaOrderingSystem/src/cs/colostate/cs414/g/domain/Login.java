package cs.colostate.cs414.g.domain;

import java.util.HashMap;
import java.util.Map;

public class Login {
	
	private static Map<String,String> allLogins = new HashMap<String,String>();

	public static void addLogins (Map<String, String> allLogins){
		Login.allLogins.putAll(allLogins);
	}
	public static boolean authenticate(String username, String password) {
		if(allLogins.containsKey(username)){
			if(allLogins.containsValue(password)){
				return true;
			}
		}
		return false;
	}
}