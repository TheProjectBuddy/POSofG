package cs.colostate.cs414.a5.g.controller;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class Server {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		System.out.println("My Pizza Store server running.....");
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 8000);

		MenuController menu = new MenuController();
		
		OrderController order= new OrderController();
		
		SignupController signup= new SignupController();
		
		SigninController signin=new SigninController();
		
		server.createContext("/order",order);
		server.createContext("/menu",menu);
		server.createContext("/signup",signup);
		server.createContext("/signin",signin);
		
		server.start();
	}

}
