package com.example.harshil_payment;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
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
			    if(cardName.isEmpty() || cardNo.isEmpty() || cvv.isEmpty() || expireDate.isEmpty()) flag=false;
			    else if(cardNo.charAt(0)=='0' || cardNo.length()!=16 || !cardNo.matches("-?\\d+(.\\d+)?"))	flag=false;
			    else if(cvv.length()!=3 || !cvv.matches("-?\\d+(.\\d+)?"))	flag=false;
			    
			    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
				simpleDateFormat.setLenient(false);
				Date date;
				try 
				{
					date = simpleDateFormat.parse(expireDate);
					boolean expired = date.before(new Date());
					if(expired)
					{
						flag=false;
					}
				} 
				catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			    if(flag)
			    {
				   Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
				   Intent intent1 = new Intent(PaymentCard.this, ThankYou.class);
				   startActivity(intent1);
			    }
			    else
			    {
			    	 Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_LONG).show();
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