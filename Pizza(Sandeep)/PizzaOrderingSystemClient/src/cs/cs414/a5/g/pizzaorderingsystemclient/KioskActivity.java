package cs.cs414.a5.g.pizzaorderingsystemclient;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class KioskActivity extends Activity implements OnClickListener,
RadioGroup.OnCheckedChangeListener {

private static final int MY_BUTTON1 = 9000;
private static final int MY_BUTTON2 = 9001;
private static final int MY_BUTTON3 = 9002;

public String uriString = new String();

final RadioButton[] rb = new RadioButton[4];
RadioGroup rg;
final ArrayList<String> toppingsSelected = new ArrayList<String>();
final ArrayList<String> othersSelected = new ArrayList<String>();
final ArrayList<CheckBox> selectedcheckBox = new ArrayList<CheckBox>();
public static String orderString = null;
public static int orderId = 1;
@Override
protected void onCreate(Bundle savedInstanceState) {
//uriString = "customer=guest&orderId="+orderId;
Intent intent=getIntent();
uriString="customer="+intent.getStringExtra("GName");
//Toast.makeText(getApplicationContext(), uriString, Toast.LENGTH_LONG).show();
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_kiosk);
AsyncTask result = new GetMenu().execute();
try {
	String string = (String) result.get();
	Log.w("String is", string);

	// //////XML PARSER

	DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
	DocumentBuilder b = f.newDocumentBuilder();
	Document doc = b.parse(new InputSource(new StringReader(string)));

	LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout2);

	rg = new RadioGroup(this);
	rg.setOrientation(RadioGroup.VERTICAL);

	String temp = new String();

	NodeList nodes = doc.getElementsByTagName("menuitem");
	for (int i = 0; i < nodes.getLength(); i++) {
		rb[i] = new RadioButton(this);
		Element element = (Element) nodes.item(i);
		NodeList name = element.getElementsByTagName("type");
		Element line = (Element) name.item(0);
		temp += getCharacterDataFromElement(line) + "-";
		NodeList price = element.getElementsByTagName("price");
		line = (Element) price.item(0);
		temp += getCharacterDataFromElement(line);
		rb[i].setText(temp);
		rb[i].setId(i + 1);
		rg.addView(rb[i]);
		rg.setOnCheckedChangeListener(this);
		temp = "";
	}
	ll.addView(rg);

	
	NodeList toppingsList = doc.getElementsByTagName("topping");

	Log.w("Toppings length", toppingsList.getLength() + "");
	for (int i = 0; i < toppingsList.getLength(); i++) {
		final CheckBox cb = new CheckBox(this);
		final Element tpg = (Element) toppingsList.item(i);
		cb.setText(getCharacterDataFromElement(tpg));
		cb.setId(i);
		cb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// is chkIos checked?
				if (((CheckBox) v).isChecked()) {
					toppingsSelected
							.add(getCharacterDataFromElement(tpg));
					selectedcheckBox.add(cb);
				} 
			}
		});

		ll.addView(cb);
	}

	NodeList nodesOther = doc.getElementsByTagName("others");
	for (int i = 0; i < nodesOther.getLength(); i++) {
		final CheckBox chkbx = new CheckBox(this);
		Element others = (Element) nodesOther.item(i);
		NodeList otherName = others.getElementsByTagName("other");
		final Element otherLine = (Element) otherName.item(0);
		chkbx.setText(getCharacterDataFromElement(otherLine));
		chkbx.setId(i);
		chkbx.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// is chkIos checked?
				if (((CheckBox) v).isChecked()) {
					othersSelected
							.add(getCharacterDataFromElement(otherLine));
					selectedcheckBox.add(chkbx);
				} 

			}
		});
		ll.addView(chkbx);
	}

	Button btn1 = new Button(this);
	btn1.setText("Add to Order");
	btn1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
			LayoutParams.WRAP_CONTENT));
	btn1.setId(MY_BUTTON1);
	btn1.setOnClickListener(this);
	ll.addView(btn1);

	Button btn2 = new Button(this);
	btn2.setText("View/Modify Order");
	btn2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
			LayoutParams.WRAP_CONTENT));
	btn2.setId(MY_BUTTON2);
	btn2.setOnClickListener(this);
	ll.addView(btn2);

	Button btn3 = new Button(this);
	btn3.setText("Place Order");
	btn3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
			LayoutParams.WRAP_CONTENT));
	btn3.setId(MY_BUTTON3);
	btn3.setOnClickListener(this);
	ll.addView(btn3);

} catch (InterruptedException e) {
	e.printStackTrace();
} catch (ExecutionException e) {
	e.printStackTrace();
} catch (ParserConfigurationException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (UnsupportedEncodingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SAXException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

}

public static String getCharacterDataFromElement(Element e) {
Node child = e.getFirstChild();
if (child instanceof CharacterData) {
	CharacterData cd = (CharacterData) child;
	return cd.getData();
}
return null;
}

@Override
public void onClick(View v) {
Toast toast;
switch (v.getId()) {
case MY_BUTTON1:

	int selectedId = rg.getCheckedRadioButtonId();

	// Which Pizza is selected?
	
	RadioButton rbtn = new RadioButton(this);
	rbtn = (RadioButton) findViewById(selectedId);
	// form uri of order
	if(rbtn == null){
		//No pizza is selected, check if other items are selected?
		for (String str : othersSelected) {
			uriString += "&other=" + str;
		}
	}
	else{
	uriString += "&type=" + (String) rbtn.getText();
	// Which toppings are selected?
	for (String str : toppingsSelected) {
		uriString += "-" + str;
	}
	//which other items
	for (String str : othersSelected) {
		uriString += "&other=" + str;
	}
	}
	AsyncTask result2 = new SendOrder().execute(uriString);
	try {
		orderString = (String) result2.get();
		Log.w("String after add to order is", orderString);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		rg.clearCheck();
		for(CheckBox c: selectedcheckBox)
			c.setChecked(false);
			
		othersSelected.clear();
		toppingsSelected.clear();
		
	toast = Toast.makeText(this, "Added to Order!!", Toast.LENGTH_LONG);
	toast.setGravity(Gravity.TOP, 25, 400);
	toast.show();
	uriString = "";
	break;
case MY_BUTTON2:
//	//View Order - Should display a new window with all the orders places and should be able to Modify it.
	Intent intent = new Intent(this, OrderActivity.class);
	Log.w("Inside Kiosk ",orderString);
	intent.putExtra("OrderString", orderString);
	startActivityForResult(intent, 1);

	break;
case MY_BUTTON3:
	orderId ++;
} 
}

@Override
public void onCheckedChanged(RadioGroup group, int checkedId) {
}
}
