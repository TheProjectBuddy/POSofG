package cs.cs414.a5.g.pizzaorderingsystemclient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity2 extends Activity {
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        
        final double d = 12.3;
        
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String customerID = "1";
				double point = 0;
				//customerID
				final AsyncTask redeemResult = new RedeemPointCall().execute(customerID);
				try 
				{
					
					 String discountRedeem = (String) redeemResult.get();
					 point = Double.parseDouble(discountRedeem) + d*2;
					 
					 //Toast.makeText(getApplicationContext(),discountRedeem,Toast.LENGTH_LONG).show();
				} 
				catch (Exception e1) 
				{
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Intent intent1 = new Intent(MainActivity2.this, ThankYou.class);
				intent1.putExtra("customerID", customerID);
				intent1.putExtra("customerPoints", Double.toString(point));
				startActivity(intent1);
			}
		});
        
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 
				Intent intent2 = new Intent(MainActivity2.this, Delivery.class);
				startActivity(intent2);
			}
		});
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
