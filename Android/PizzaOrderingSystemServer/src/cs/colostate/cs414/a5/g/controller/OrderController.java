package cs.colostate.cs414.a5.g.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs.colostate.cs414.a5.g.util.MenuUtil;
import cs.colostate.cs414.g.domain.Customer;
import cs.colostate.cs414.g.domain.Menu;
import cs.colostate.cs414.g.domain.MenuItem;
import cs.colostate.cs414.g.domain.Order;
import cs.colostate.cs414.g.domain.OrderItem;
import cs.colostate.cs414.g.domain.Pizza;
import cs.colostate.cs414.g.domain.Topping;

public class OrderController implements HttpHandler {
	Order order = null;
	Menu menu = MenuUtil.getMenuItems();
	ArrayList<OrderItem> orderItems = null;
	MenuItem menuItem = null;
	Pizza pizza = null;

	public void handle(HttpExchange exchange) throws IOException {
		URI uri = exchange.getRequestURI();

		String query = uri.getQuery();
		if (query != null) {
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

		for (String parameter : subs) {
			// key is on the left and value is on the right, so we split this
			String[] values = parameter.split("=");
			if (values[0].equals("customer")) {
				order = new Order();
				order.setCustomer(new Customer(values[1]));
			} else if (values[0].equals("orderId")) {
				order.setOrderId(Integer.parseInt(values[1]));
			} else if (values[0].equals("type")) {
				// Pizza
				String[] str = values[1].split("-");
				
				orderItems = new ArrayList<OrderItem>();
				pizza = new Pizza(Pizza.Size.valueOf(str[0]),
						MenuUtil.getPricePizza(str[0]),
						MenuUtil.getPricePerTopping(str[0]), 0);
				for (int i = 2; i < str.length; i++) {
					Topping topping = new Topping(str[i]);
					pizza.addTopping(topping);
				}
				orderItems.add(new OrderItem(order, pizza));
				order.addFood(pizza);
			} else if (values[0].equals("other")) {
				menuItem = new MenuItem(values[1],
						MenuUtil.getPrice(values[1]), 0);
				order.addFood(menuItem);
			} else if (values[0].equals("remove")) {
				ArrayList<OrderItem> temp = new ArrayList<OrderItem>();
				temp.clear();

				if (values[1].contains("-")) {
					// it is a pizza

					String[] removeP = values[1].split("-");
					//removeP[0] = SMALL rest toppings
					temp.addAll(order.getOrderItems());

					Pizza removePizza = new Pizza(
							Pizza.Size.valueOf(removeP[0]),
							MenuUtil.getPricePizza(removeP[0]),
							MenuUtil.getPricePerTopping(removeP[0]), 0);
					for (int index = 1; index < removeP.length; index++)
						removePizza.addTopping(new Topping(removeP[index]));
					
					for(int i=0;i<temp.size();i++){
						if(temp.get(i).getFood() instanceof Pizza){
							boolean result = compareItem(temp.get(i).getFood(),removePizza);
							if(result == true){
								temp.remove(i);
							}
						}
					}
					order.setOrderItems(temp);
					
				} else {
					//other item
					temp.addAll(order.getOrderItems());
					for(int i=0;i<temp.size();i++){
						if(temp.get(i).getFood().getType().contains(values[1])){
							temp.remove(i);
						}
					}
					order.setOrderItems(temp);
				}
			}

		}
	}

	private boolean compareItem(MenuItem menuItem, Pizza removePizza) {
		Pizza pizza = (Pizza) menuItem;
		String pizzaTop = new String();
		String removeTopp = new String();
		
		for(int i=0;i<pizza.getToppings().size();i++){
			pizzaTop+=pizza.getToppings().get(i).getType();
		}
		
		for(int i=0;i<removePizza.getToppings().size();i++){
			removeTopp+=removePizza.getToppings().get(i).getType();
		}
		
		if(pizza.getType() == removePizza.getType()){
			if(pizzaTop.equalsIgnoreCase(removeTopp)){
				return  true;
			}
		}
		
		return false;
	}

	// Turns the ArrayList<Pizza> into an XML representation
	private String getXml() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<order>");
		for (OrderItem oi : order.getOrderItems()) {
			if (oi.getFood() instanceof Pizza) {
				Pizza pizza = (Pizza) oi.getFood();

				buffer.append("<orderitem>");
				buffer.append("<pizza>");
				buffer.append(oi.getFood().getType());
				buffer.append("</pizza>");
				buffer.append("<toppings>");
				for (Topping tpg : pizza.getToppings()) {
					buffer.append("<topping>");
					buffer.append(tpg.getType());
					buffer.append("</topping>");
				}
				buffer.append("</toppings>");
				buffer.append("</orderitem>");
			} else {
				buffer.append("<otheritem>");
				buffer.append(oi.getFood().getType());
				buffer.append("</otheritem>");
			}
		}
		buffer.append("<total>");
		buffer.append(order.getPrice());
		buffer.append("</total>");

		buffer.append("</order>");
		System.out.println(buffer.toString());
		return buffer.toString();
	}
}
