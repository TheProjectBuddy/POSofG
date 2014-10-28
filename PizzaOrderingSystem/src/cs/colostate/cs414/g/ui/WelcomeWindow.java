package cs.colostate.cs414.g.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cs.colostate.cs414.g.domain.Login;
import cs.colostate.cs414.g.domain.Menu;
import cs.colostate.cs414.g.domain.PhoneOrder;
import cs.colostate.cs414.g.util.Stage;

public class WelcomeWindow extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelWelcomeToThe;
	private JPanel panel;
	private JButton buttonEmployee;
	private JButton buttonManager;
	private JButton buttonExit;
	private JLabel labelPleasePickAn;

	PhoneOperatorWindow phoneOperatorWindow = null;
	ManagerWindow mw = new ManagerWindow();


	/**
	 * Create the frame.
	 */
	public WelcomeWindow(PhoneOrder phoneOrder, Menu menu, Stage startStage) {
		phoneOperatorWindow = new PhoneOperatorWindow(this, phoneOrder, menu, startStage);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		labelWelcomeToThe = new JLabel("Welcome to the Pizza Ordering System!");
		labelWelcomeToThe.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		labelWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(labelWelcomeToThe, BorderLayout.NORTH);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		buttonEmployee = new JButton("Employee");
		buttonEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				phoneOperatorWindow.present();
				WelcomeWindow.this.setVisible(false);
			}
		});
		panel.add(buttonEmployee);
		//if(Login.isManager == 1){
		buttonManager = new JButton("Manager");
		buttonManager.addActionListener( new ActionListener () {
			public void actionPerformed(ActionEvent arg0) {

				mw.setVisible(true);
			}
		});
		panel.add(buttonManager);
		//}
		buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				WindowEvent windowClosing = new WindowEvent(WelcomeWindow.this, WindowEvent.WINDOW_CLOSING);
				WelcomeWindow.this.dispatchEvent(windowClosing);			
			}
		});
		panel.add(buttonExit);
		
		labelPleasePickAn = new JLabel("Please choose an option:");
		labelPleasePickAn.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelPleasePickAn.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(labelPleasePickAn, BorderLayout.CENTER);
	}

}
