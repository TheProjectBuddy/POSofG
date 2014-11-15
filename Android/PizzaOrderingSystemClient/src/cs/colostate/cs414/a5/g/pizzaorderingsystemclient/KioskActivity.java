package cs.colostate.cs414.a5.g.pizzaorderingsystemclient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class KioskActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kiosk);
		AsyncTask result = new GetMenu().execute();
		try {
			String string = (String)result.get();
			Log.w("String is", string);
			
			////////XML PARSER
			
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();
		    Document doc = b.parse(new InputSource(new StringReader(string)));
			
			LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout2);
			
			final RadioButton[] rb = new RadioButton[4];
			RadioGroup rg = new RadioGroup(this);
			rg.setOrientation(RadioGroup.VERTICAL);
			
			String temp = new String();
		
			NodeList nodes = doc.getElementsByTagName("menuitem");
			for (int i = 0; i < nodes.getLength(); i++) {
				rb[i] = new RadioButton(this);
				Element element = (Element) nodes.item(i);
				NodeList name = element.getElementsByTagName("type");
				Element line =(Element) name.item(0);
				temp += getCharacterDataFromElement(line)+"-";
				NodeList price = element.getElementsByTagName("price");
		        line = (Element) price.item(0);
		        temp += getCharacterDataFromElement(line);  
				rb[i].setText(temp);
				rb[i].setId(i);
				rg.addView(rb[i]); 
				temp = "";
			}
			ll.addView(rg);
			
			NodeList toppingsList = doc.getElementsByTagName("topping");
			Log.w("Toppings length",toppingsList.getLength()+"");
			for (int i = 0; i < toppingsList.getLength(); i++) {
				CheckBox cb = new CheckBox(this);
				Element tpg = (Element) toppingsList.item(i);
				cb.setText(getCharacterDataFromElement(tpg));
				cb.setId(i);
				ll.addView(cb);
			}
			Button btn1 = new Button(this);
			btn1.setText("Add to Order");
			btn1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			btn1.setId(1);
			btn1.setOnClickListener(this);
	        ll.addView(btn1);
	        
	        Button btn2 = new Button(this);
			btn2.setText("Place Order");
			btn2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			btn2.setId(2);
			btn2.setOnClickListener(this);
	        ll.addView(btn2);
	        
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
		// TODO Auto-generated method stub

	}
}
