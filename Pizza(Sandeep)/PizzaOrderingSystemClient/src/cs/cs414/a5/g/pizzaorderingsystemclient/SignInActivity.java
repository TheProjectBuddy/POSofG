package cs.cs414.a5.g.pizzaorderingsystemclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		
		Button signin=(Button)findViewById(R.id.signinButton);
		signin.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0)
			{
				String response = null;
				EditText nameText = (EditText) findViewById(R.id.editText1);
				String username = nameText.getText().toString();
				
				EditText passText = (EditText) findViewById(R.id.editText2);
				String password = passText.getText().toString();
				
				String message= username +"&"+ password;
				AsyncTask result=new SigninCall().execute(message);
				
				try {
					response=(String)result.get();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				Toast.makeText(getApplicationContext(), response ,Toast.LENGTH_LONG).show();
				
			}
		});
		
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
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