package cs.cs414.a5.g.pizzaorderingsystemserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class OrderController implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		URI uri= exchange.getRequestURI();
		System.out.println(uri.getQuery());
		//parseRequest(uri.toString());
		
		//String s=uri.getQuery();
		//System.out.println(s);
		String response="";
		
	exchange.sendResponseHeaders(200, response.length());
	OutputStream stream =exchange.getResponseBody();
	stream.write(response.getBytes());
	stream.close();
	}

	private void parseRequest(String request)
	{
		
		String[] split= request.split("?");
		System.out.println(split[1]);
		
	}
	
}
