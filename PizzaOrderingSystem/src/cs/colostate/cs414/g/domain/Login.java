package cs.colostate.cs414.g.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Login {
	
	private static String userID, password;
	static boolean flag = false;
	public static int isManager = 0;
	public static boolean authenticate(String passwordProvided) {
		if(passwordProvided.equals(password)) return true;
		return false;	
	}


	public static boolean checkExistingUsername(String userName) {
		 File file = new File("employees.txt");
		 try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String newLine = bufferedReader.readLine();
			
			while(newLine != null)
			{
				String elements[] = newLine.split("-");
				if(elements[2].equals(userName))
				{
					password = elements[3];
					flag = true;
					if(elements[1].equalsIgnoreCase("Store Manager")) isManager = 1;
					break;
					
				}
				newLine = bufferedReader.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("employees.txt does not exist");
			e.printStackTrace();
		}
		 catch (IOException e) {
			 System.out.println("error while reading file");
				e.printStackTrace();
		}
		 return flag;
	}
	
}