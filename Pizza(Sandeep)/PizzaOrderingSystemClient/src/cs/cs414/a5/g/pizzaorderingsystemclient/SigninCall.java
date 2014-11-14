package cs.cs414.a5.g.pizzaorderingsystemclient;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class SigninCall extends AsyncTask<String,String,String> {

	@Override
	protected String doInBackground(String... message){
		
		String url="http://10.0.2.2:8000/signin"+"?"+message[0];
		HttpGet httpget= new HttpGet(url);
		HttpClient httpclient= new DefaultHttpClient();
		String response;
		try {
			httpclient.execute(httpget);
			response="success";
		} catch (Exception e) {
			response="failure";
			e.printStackTrace();
		} 
		
		
		return response;
		
		
	}

}
