package cs.cs414.a5.g.pizzaorderingsystemserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs.cs414.a5.g.util.DataUtil;

public class SignupController implements HttpHandler{

	String name;
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		URI uri= exchange.getRequestURI();
		
		
		Boolean success=parseRequest(uri.getQuery());
		//System.out.println(uri.getQuery());
		if(success==true)
		{
		String response="Signup Successful!";
        DataUtil.setLoggedin(success);
		exchange.sendResponseHeaders(200, response.length());
		OutputStream stream =exchange.getResponseBody();
		stream.write(response.getBytes());
		stream.close();
		}
		else
		{
			System.out.println("Signup unsucessful!");
		}
		
	}

	private Boolean parseRequest(String query) {
		
		String[] splits=query.split("&");
		//for(int i=0;i<splits.length;i++)
		//System.out.println(splits[i]);
		//System.out.println(query);
		File file = new File("CustomerLogins");
		try 
		{
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String LineRead = bufferedReader.readLine();
			System.out.println(LineRead);
			int count = 0;
			String[] updated = new String[100];
			while(LineRead != null)
			{
			    updated[count] = LineRead;
				count++;
				LineRead = bufferedReader.readLine();
			}
			bufferedReader.close();
			
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for(int i=0; i<100; i++)
			{
				
				if(updated[i] != null) bufferedWriter.write(updated[i]+"\n");
				
			}
			for(int i=0;i<splits.length;i++)
			{
				String[] temp=splits[i].split("=");
			bufferedWriter.write(temp[1]+"|");
			}
			bufferedWriter.write(count+"\n");
			bufferedWriter.close();
			
			return true;
		}
		catch(Exception e)
		{
		
		    e.printStackTrace();
			return false;
		}
		
		
	
	}

}
