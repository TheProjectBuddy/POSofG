package cs.cs414.a5.g.pizzaorderingsystemclient;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class SignupCall extends AsyncTask<String,String,String>{

	protected String doInBackground(String... message) {
		
		
		//System.out.println(message[0]);
		String url="http://10.0.2.2:8000/signup"+"?"+message[0];
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
