package frontPage;

/*
 *@author = Caue Meireles Duarte 
 */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//View of the front page of the system with the main options
public class FrontPageView extends JFrame {
	
	private FrontPageController controller;
	private JTextField custLoginTF;
	private JLabel errorMsg;

	public String getCustLoginTF() {
		return custLoginTF.getText();
	}

	public JLabel getErrorMsg() {
		return errorMsg;
	}

	//Class constructor
	public FrontPageView(FrontPageController controller) {
		this.controller = controller;
		this.addWindowListener(controller);
		settings();
		createElements();
	}

	//create buttons, layout manager, labels and textbox and adds them to the frame
	private void createElements() {
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,0,10,0);
		JLabel custLbl = new JLabel("Enter Customer Number For Info:");
		this.add(custLbl, c);
		JTextField custNumTF = new JTextField(20);
		c.gridy = 1;
		this.custLoginTF = custNumTF;
		this.add(custNumTF, c);
		JLabel errorLbl = new JLabel("Invalid Customer Number!");
		errorLbl.setForeground(this.getBackground());				//setting text to the same colour as the background so its "invisible" without moving other components
		errorLbl.setHorizontalAlignment(SwingConstants.CENTER);
		this.errorMsg = errorLbl;
		c.gridy = 2;
		c.weighty = 0.7;
		this.add(errorLbl, c);
		JButton loginBt = new JButton("Check Customer");
		loginBt.setActionCommand("custInfo");
		loginBt.addActionListener(controller);
		c.gridy = 3;
		this.add(loginBt, c);
		JButton newCustBt = new JButton("Register Customer");
		newCustBt.setActionCommand("newCust");
		newCustBt.addActionListener(controller);
		c.gridy = 4;
		this.add(newCustBt, c);
		JButton checkMediaBt = new JButton("Check Media");
		checkMediaBt.setActionCommand("checkMedia");
		checkMediaBt.addActionListener(controller);
		c.gridy = 5;
		this.add(checkMediaBt, c);
		JButton addMediaBt = new JButton("Add New Media");
		addMediaBt.setActionCommand("addMedia");
		addMediaBt.addActionListener(controller);
		c.gridy = 6;
		this.add(addMediaBt, c);
		
		this.validate();
		this.repaint();
	
	}

	//create main settings for the frame
	private void settings() {
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(250,350);
		this.validate();
		this.repaint();
		
	}

}
