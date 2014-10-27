package cs.colostate.cs414.g.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Login {
	
	private static String userID, password;
	static boolean flag = false;
	
	public static boolean authenticate(String passwordProvided) {
		if(passwordProvided.equals(password)) return true;
		else return false;
		
		
		
	}


	public static boolean checkExistingUsername(String userName) {
		// TODO Auto-generated method stub
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
					break;
					
				}
				newLine = bufferedReader.readLine();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("employees.txt does not exist");
			e.printStackTrace();
		}
		 catch (IOException e) {
			// TODO: handle exception
			 System.out.println("error while reading file");
				e.printStackTrace();
		}
		 return flag;
	}
	
}