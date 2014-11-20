package cs.cs414.a5.g.pizzaorderingsystem;


import android.os.Bundle;
import android.preference.PreferenceFragment;

public class userPrefFragment extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.user_prefs);
	}

}
