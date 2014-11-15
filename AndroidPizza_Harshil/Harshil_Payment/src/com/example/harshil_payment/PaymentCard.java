package com.example.harshil_payment;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.net.ParseException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PaymentCard extends Activity {
	
	public Boolean validateExpiry(String expiry) throws ParseException, java.text.ParseException
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
		simpleDateFormat.setLenient(false);
		Date date= simpleDateFormat.parse(expiry);
		boolean expired = !date.before(new Date());
		return expired;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment_card);
		
		Button buttonPayment = (Button) findViewById(R.id.button2);
		buttonPayment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Boolean flag=true;
			    EditText editText = (EditText) findViewById(R.id.editText1);
			    String cardName = editText.getText().toString();
			    
			    EditText editText2 = (EditText) findViewById(R.id.editText2);
			    String cardNo = editText2.getText().toString();
			    
			    EditText editText3 = (EditText) findViewById(R.id.editText3);
			    String cvv = editText3.getText().toString();
			    
			    EditText editText4 = (EditText) findViewById(R.id.editText4);
			    String expireDate = editText4.getText().toString();
			    
			    if(cardNo.charAt(0)=='0')	flag=false;

			    try {
					if(!(validateExpiry(expireDate)))	flag=false;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(!(cvv.matches("[0-9]+")))	flag=false;
		        if(!(expireDate.charAt(2)=='/'))flag=false;
				if(cardNo.isEmpty()||cvv.isEmpty()||expireDate.isEmpty() || cardNo.length()!=16)flag=false;
				
				if(flag)
				{
					Toast.makeText(getApplicationContext(),"String",Toast.LENGTH_LONG).show();
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.payment_card, menu);
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
