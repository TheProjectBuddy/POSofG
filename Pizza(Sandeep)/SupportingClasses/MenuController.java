package cs.cs414.a5.g.pizzaorderingsystemserver;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import java.io.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MenuController implements HttpHandler {

	ArrayList<MenuItem> items;
	
	public MenuController()
	{
		items= new ArrayList<MenuItem>();
	
	MenuItem cheesePizza= new MenuItem("Pizza"," a cheese pizza");
	items.add(cheesePizza);
	}
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		//System.out.println("Request");
		
		URI uri= exchange.getRequestURI();
		System.out.println(uri);
		
		String response=getXML();
		
	exchange.sendResponseHeaders(200, response.length());
	OutputStream stream =exchange.getResponseBody();
	stream.write(response.getBytes());
	stream.close();
	}
	
	private String getXML()
	{
		StringBuffer buff=new StringBuffer();
		buff.append("<Menu>\n");
		
		for(MenuItem item: items)
		{
			buff.append("<item>\n");
			buff.append("<type>\n");
			buff.append(item.getType()+"\n");
			buff.append("</type>\n");
			buff.append("<description>\n");
			buff.append(item.getDescrption()+"\n");
			buff.append("</description>\n");
			buff.append("</item>\n");
			
		}
		buff.append("</Menu>\n");
		return buff.toString();
	}

}
