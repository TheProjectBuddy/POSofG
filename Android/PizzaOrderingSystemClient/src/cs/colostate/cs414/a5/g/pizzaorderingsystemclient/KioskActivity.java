package cs.colostate.cs414.a5.g.pizzaorderingsystemclient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

	private static final int MY_BUTTON = 99;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kiosk);
		AsyncTask result = new GetMenu().execute();
		try {
			String string = (String)result.get();
			Log.w("String is", string);
			
			////////XML PARSER
			/**
			 *  menu>
<menuitem>
<SMALLPizza>
<price>27.5</price>
</SMALLPizza>
</menuitem>
<menuitem>
<MEDIUMPizza>
<price>16.0</price>
</MEDIUMPizza>
</menuitem>
<menuitem>
<LARGEPizza>
<price>23.5</price>
</LARGEPizza>
</menuitem>
<menuitem>
<Salad>
<price>5.0</price>
</Salad>
</menuitem>
<toppings>
<topping>Tomato</topping>
<topping>Tomato</topping>
<topping>Jalapeno</topping>
<topping>Onions</topping>
<topping>Peppers</topping>
<topping>Mushrooms</topping>
</toppings>
</menu>
			 */
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();
			Document doc = b.parse(new ByteArrayInputStream(string.getBytes("UTF-8")));
			NodeList books = doc.getElementsByTagName("toppings");
			for (int i = 0; i < books.getLength(); i++) {
			    Element book = (Element) books.item(i);
			    Node title = book.getElementsByTagName("topping").item(0);
			    Log.w("XML Parsed working!! ",title.getTextContent());
			}
			
			
			LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout2);
			
			final RadioButton[] rb = new RadioButton[3];
			RadioGroup rg = new RadioGroup(this);
			rg.setOrientation(RadioGroup.VERTICAL);
			
			for (int i = 0; i < 3; i++) {
				rb[i] = new RadioButton(this);
				rb[i].setText("Dynamic Radio Button " + i);
				rb[i].setId(i);
				rg.addView(rb[i]); 
			}
			ll.addView(rg);
			
			for (int i = 0; i < 5; i++) {
				CheckBox cb = new CheckBox(this);
				cb.setText("Dynamic Checkbox " + i);
				cb.setId(i + 10);
				ll.addView(cb);
			}
			Button btn = new Button(this);
			btn.setText("Button added dynamically!");
			btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			btn.setId(MY_BUTTON);
			btn.setOnClickListener(this);
	        ll.addView(btn);
	        
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
