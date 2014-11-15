package cs.colostate.cs414.a5.g.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import cs.colostate.cs414.a5.g.util.MenuUtil;
import cs.colostate.cs414.g.domain.Menu;
import cs.colostate.cs414.g.domain.MenuItem;
import cs.colostate.cs414.g.domain.Topping;

public class MenuController implements HttpHandler {

	private Menu menuList = MenuUtil.getMenuItems();
	
	public MenuController(){}
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
					buffer.append("<type>");
					buffer.append(menu.getType());
					buffer.append("</type>");
				
					buffer.append("<price>");
					buffer.append(menu.getPrice());
					buffer.append("</price>");
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
}