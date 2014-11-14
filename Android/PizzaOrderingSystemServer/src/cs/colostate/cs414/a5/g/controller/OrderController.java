package cs.colostate.cs414.a5.g.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import cs.colostate.cs414.g.domain.Menu;
import cs.colostate.cs414.g.domain.MenuItem;
import cs.colostate.cs414.g.domain.Topping;

public class OrderController implements HttpHandler {

	private Menu menuList;
	
	public OrderController(){
		FileInputStream menuFileStream = null;
		try {
			File file = new File("menu"); 
			menuFileStream = new FileInputStream(file);
		}
		catch (FileNotFoundException e) {
			System.err.println("Unable to open menu file. Exiting...");
			System.exit(1);
		}
		
		try {
			menuList = new Menu(menuFileStream);
		}
		catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace(System.err);
			System.exit(1);
		}
		
		  
	}
	public void handle(HttpExchange exchange) throws IOException {
		URI uri = exchange.getRequestURI();
		
		String query = uri.getQuery();
		if (query != null)
		{
			parseQuery(query);
		}
		
		String response = getXml();
		exchange.sendResponseHeaders(200, response.length());

		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
	private void parseQuery(String query) {
		String[] subs = query.split("&");
		
	}


	//Turns the ArrayList<Pizza> into an XML representation
	private String getXml() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<menu>");

		for(MenuItem menu: menuList.getFoodItems()){
				buffer.append("<menuitem>");
				buffer.append("<"+menu.getType()+">");
				buffer.append("<price>");
					buffer.append(menu.getPrice());
				buffer.append("</price>");
				buffer.append("</"+menu.getType()+">");
				buffer.append("</menuitem>");
			}
			buffer.append("<toppings>");
			for(Topping topping:menuList.getToppings()){
				buffer.append("<topping>");
				buffer.append(topping.getType());
				buffer.append("</topping>");
			}
			buffer.append("</toppings>");
		
		buffer.append("</menu>");
		System.out.println(buffer.toString());
		return buffer.toString();
	}
	
	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 8000);
		OrderController MC = new OrderController();
		server.createContext("/menu", MC);
		
		server.start();
	}
}
