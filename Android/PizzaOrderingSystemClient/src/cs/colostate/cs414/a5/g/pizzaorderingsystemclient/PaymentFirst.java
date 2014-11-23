package cs.colostate.cs414.a5.g.pizzaorderingsystemclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class PaymentFirst extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		double total = Double.parseDouble(intent.getStringExtra("TotalPrice"));
		/**
		 * To be removed later
		 */
		String temp = ""+total;
		Log.w("Total in PaymentFirst", temp);
		
	}
}
