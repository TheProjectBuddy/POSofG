package cs.cs414.a5.g.pizzaorderingsystemserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class SigninController implements HttpHandler{

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		URI uri= exchange.getRequestURI();
		
		
		String response="";
		authenticate(uri.getQuery());;
		
		exchange.sendResponseHeaders(200, response.length());
		OutputStream stream =exchange.getResponseBody();
		stream.write(response.getBytes());
		stream.close();
		
	}

	private void authenticate(String query) 
	{
		
		boolean flag=false;
		String[] elements;
		String[] splits=query.split("&");
	String username=splits[0];
	String password=splits[1];
		System.out.println(username);
		System.out.println(password);
		File file=new File("CustomerLogins");
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String newLine = bufferedReader.readLine();
		//	if(newLine==null)
		//    newLine=bufferedReader.readLine();
			
			while(newLine != null)
			
			{
			
				elements = newLine.split("\\|");
				
				for(int i=0;i<elements.length;i++)
				System.out.println(elements[i]);
				
				System.out.println(elements.length);
				
				//System.out.println(elements[3]);
				//System.out.println(elements[elements.length]);
				
				if(elements[3].equals(username))
				{
					
					if(elements[4].equals(password))
					{
					    flag = true;	
					}
					else
					{
						flag=false;
					}
					
					
				}
				
				
				newLine = bufferedReader.readLine();
				
			}
			bufferedReader.close();
			
				
		} catch (Exception e) {
			
			System.out.println("CustomerLogins does not exist");
			e.printStackTrace();
		}
		
			
		if(flag==true)
		System.out.println( "authenticated");
		else
		System.out.println( "authenticated failed");
		
	}
}

	


