package cs.colostate.cs414.g.util;

import java.awt.EventQueue;

import cs.colostate.cs414.g.domain.Menu;
import cs.colostate.cs414.g.domain.PhoneOrder;
import cs.colostate.cs414.g.ui.WelcomeWindow;

public class MainUtil {

	public static void run(final PhoneOrder phoneOrder, final Menu menu, final Stage startStage) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeWindow frame = new WelcomeWindow(phoneOrder, menu, startStage);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
