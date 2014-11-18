package cs.colostate.cs414.a5.g.pizzaorderingsystemclient;

import android.os.AsyncTask;
import android.util.Log;

public class SendOrder extends AsyncTask<String,String,String>{

	@Override
	protected String doInBackground(String... params) {
		String result = null;
		String url = "http://10.0.2.2:8000/order?"+params[0];
		Log.w("URI", url);
		return result;
	}

}
