package cs.colostate.cs414.a5.g.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs.colostate.cs414.g.domain.Customer;
import cs.colostate.cs414.g.domain.MenuItem;
import cs.colostate.cs414.g.domain.Order;
import cs.colostate.cs414.g.domain.OrderItem;

public class OrderController implements HttpHandler{

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
		Order order = null;
		ArrayList<OrderItem> orderItems = null;
		for (String parameter : subs)
		{	
			//key is on the left and value is on the right, so we split this
			String[] values = parameter.split("=");
			for(String str: values)
			System.out.println(str+" ");
			if (values[0].equals("customer"))
			{
				order = new Order(new Customer(values[1]));
			}
			else if (values[0].equals("orderId"))
			{
				order.setOrderId(Integer.parseInt(values[1]));
			}
			else if (values[0].equals("type")){
				orderItems = new ArrayList<OrderItem>();
					orderItems.add(new OrderItem(order, new MenuItem(values[1], Double.parseDouble(values[2]),0)));
			}
	}
		//System.out.println(orderItems.toString());
	}

	//Turns the ArrayList<Pizza> into an XML representation
	private String getXml() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<order>");

		buffer.append("</order>");
		System.out.println(buffer.toString());
		return buffer.toString();
	}
}
