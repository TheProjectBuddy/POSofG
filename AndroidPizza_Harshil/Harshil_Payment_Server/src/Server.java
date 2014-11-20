

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class Server {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 8000);

		
		
		CouponReducerController coupon= new CouponReducerController();
		RedeemCalculationController redeem = new RedeemCalculationController();
		RedeemUpdateController redeemUpdateController = new RedeemUpdateController();
		
		server.createContext("/coupon", coupon);
		server.createContext("/redeem", redeem);
		server.createContext("/redeemUpdate", redeemUpdateController);
		server.start();
	}

}
