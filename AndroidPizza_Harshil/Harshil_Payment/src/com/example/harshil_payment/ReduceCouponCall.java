package com.example.harshil_payment;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class ReduceCouponCall extends AsyncTask<String,String,String> 
{

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		String url="http://10.0.2.2:8000/coupon"+"?"+arg0[0];
		HttpGet httpget= new HttpGet(url);
		HttpClient httpclient= new DefaultHttpClient();
		
		try 
		{
			httpclient.execute(httpget);
			
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		} 
		return null;
	}

}
