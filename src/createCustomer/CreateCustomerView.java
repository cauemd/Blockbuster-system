package createCustomer;

/*
 *@author = Caue Meireles Duarte 
 */

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//view used to insert a new customer in the database
public class CreateCustomerView extends JFrame {

	private CreateCustomerController controller;
	private JTextField fName;
	private JTextField lName;
	private JTextField mobile;
	private JTextField cardNum;
	private	JLabel errorLbl;
	private int membershipType;
	private JTextField emailTF;
	
	//Class constructor
	public CreateCustomerView(CreateCustomerController controller) {
		this.controller = controller;
		this.addWindowListener(controller);
		settings();
		createElements();
	}

	//create buttons, layout manager, labels and textbox and adds them to the frame
	private void createElements() {

		//creating Layout Manager
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 0, 0, 10);
		c.anchor = GridBagConstraints.WEST;

		//creating labels and textfields for customer info
		JLabel firstNameLb = new JLabel("First Name:");
		this.add(firstNameLb, c);
		JTextField firstNameTF = new JTextField(15);
		c.gridx = 1;
		this.fName = firstNameTF;
		this.add(firstNameTF, c);
		JLabel lastNameLb = new JLabel("Last Name:");
		c.gridx = 0;
		c.gridy = 1;
		this.add(lastNameLb, c);
		JTextField lastNameTF = new JTextField(15);
		c.gridx = 1;
		this.lName = lastNameTF;
		this.add(lastNameTF, c);
		JLabel mobileLb = new JLabel("Contact Number:");
		c.gridx = 0;
		c.gridy = 2;
		this.add(mobileLb, c);
		JTextField mobileTF = new JTextField(15);
		c.gridx = 1;
		this.mobile = mobileTF;
		this.add(mobileTF, c);
		JLabel cardLb = new JLabel("Bank Card Number:");
		c.gridx = 0;
		c.gridy = 3;
		this.add(cardLb, c);
		JTextField cardTF = new JTextField(15);
		c.gridx = 1;
		this.cardNum = cardTF;
		this.add(cardTF, c);
		JLabel emailLb = new JLabel("Email Address:");
		c.gridx = 0;
		c.gridy = 4;
		this.add(emailLb, c);
		JTextField emailTF = new JTextField(15);
		c.gridx = 1;
		this.emailTF = emailTF;
		this.add(emailTF, c);

		//creating RadioButtons and ButtonGroup for the membership types
		JLabel memberLbl = new JLabel("Select type of membership:");
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 5;
		this.add(memberLbl, c);
		c.anchor = GridBagConstraints.WEST;
		JRadioButton musicRB = new JRadioButton("Music Lover");
		musicRB.setActionCommand("music");
		musicRB.addActionListener(controller);
		c.gridwidth = 1;
		c.gridy = 6;
		this.add(musicRB, c);
		JRadioButton videoRB = new JRadioButton("Video Lover");
		videoRB.setActionCommand("video");
		videoRB.addActionListener(controller);
		c.gridx = 1;
		this.add(videoRB, c);
		JRadioButton tvRB = new JRadioButton("TV Lover");
		tvRB.setActionCommand("tv");
		tvRB.addActionListener(controller);
		c.gridx = 0;
		c.gridy = 7;
		this.add(tvRB, c);
		JRadioButton premiumRB = new JRadioButton("Premium");
		premiumRB.setActionCommand("premium");
		premiumRB.addActionListener(controller);
		c.gridx = 1;
		this.add(premiumRB, c);
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(premiumRB);
		btnGroup.add(tvRB);
		btnGroup.add(videoRB);
		btnGroup.add(musicRB);
		
		//creating buttons
		JButton registerBtn = new JButton("Register Costumer");
		registerBtn.addActionListener(controller);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 8;
		c.insets = new Insets(20,10,0,10);
		this.add(registerBtn, c);
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setActionCommand("cancel");
		cancelBtn.addActionListener(controller);
		c.gridx = 1;
		this.add(cancelBtn, c);	
		JLabel errorLbl = new JLabel("# Ultra-Vision #");
		this.errorLbl = errorLbl;
		c.gridwidth = 2;
		errorLbl.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 9;
		this.add(errorLbl, c);

		this.validate();
		this.repaint();
	}

	//create main settings for the frame
	private void settings() {
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(350,380);
		this.validate();
		this.repaint();

	}

	//getters and setters
	public String getfName() {
		return this.fName.getText();
	}

	public String getlName() {
		return this.lName.getText();
	}

	public String getMobileNum() {
		return this.mobile.getText();
	}

	public String getCardNum() {
		return this.cardNum.getText();
	}

	public JLabel getErrorLbl() {
		return this.errorLbl;
	}

	public int getMembershipType() {
		return membershipType;
	}
	
	public String getEmail() {
		return this.emailTF.getText();
	}

	public void setMembershipType(int membershipType) {
		this.membershipType = membershipType;
	}

}
