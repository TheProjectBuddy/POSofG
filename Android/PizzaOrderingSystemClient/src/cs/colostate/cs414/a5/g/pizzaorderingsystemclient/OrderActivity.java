package cs.colostate.cs414.a5.g.pizzaorderingsystemclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class OrderActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		
		Intent intent = getIntent();
		String resultXML = intent.getStringExtra("OrderString");
		Log.w("From OrderActivity",resultXML);
	}
}
