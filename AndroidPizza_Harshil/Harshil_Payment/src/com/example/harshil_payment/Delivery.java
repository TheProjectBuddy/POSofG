package com.example.harshil_payment;

import java.text.DecimalFormat;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Delivery extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delivery);
		
		final double d = 12.3;
		TextView textView2 = (TextView) findViewById(R.id.textView3);
		textView2.setText("Your Amount: "+Double.toString(d));
		
		Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent3 = new Intent(Delivery.this, ThankYou.class);
				startActivity(intent3);
			}
		});
        
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent4 = new Intent(Delivery.this, PaymentCard.class);
				startActivity(intent4);
			}
		});
        
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText editText = (EditText) findViewById(R.id.editText2);
			    String couponNumber = editText.getText().toString();
				AsyncTask result=new ReduceCouponCall().execute(couponNumber);
				try 
				{
					String discount = (String) result.get();
					double discountAmount = Double.parseDouble(discount);
					double newPrice = d - discountAmount;
					TextView textView = (TextView) findViewById(R.id.textView3);
					DecimalFormat df = new DecimalFormat("#.##");   
					//df.format(newPrice);
					textView.setText("Your New Amount: "+df.format(newPrice));
					
					Toast.makeText(getApplicationContext(),"Your Discount Is Applied Successfully",Toast.LENGTH_LONG).show();
				} 
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.delivery, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
