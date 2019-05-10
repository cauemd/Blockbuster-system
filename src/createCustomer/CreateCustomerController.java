package createCustomer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import frontPage.FrontPageController;

/*
 *@author = Caue Meireles Duarte 
 */


//controller for the view that creates a new customer in the system 
public class CreateCustomerController implements WindowListener, ActionListener{

	private CreateCustomerView view;
	private CreateCustomerModel model;

	public CreateCustomerController() {
		this.model = new CreateCustomerModel(this);
		this.view = new CreateCustomerView(this);
	}

	/*
	 *  implementing WindowListener only to respond to closing event
	 */


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	//Takes the user to the front page if the view is closed
	@Override
	public void windowClosing(WindowEvent e) {
		new frontPage.FrontPageController();	
		view.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	//handling the cancel and register btn and assigning the memberbship according to the view's radio button
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("cancel")) {
			new FrontPageController();
			view.dispose();
		} else if (e.getActionCommand().equals("video")) {
			view.setMembershipType(1);
		} else if (e.getActionCommand().equals("music")) {
			view.setMembershipType(2);
		} else if (e.getActionCommand().equals("tv")) {
			view.setMembershipType(3);
		} else if (e.getActionCommand().equals("premium")) {
			view.setMembershipType(4);

			//register button, uses model to perform verification and insert customer in DB
		} else {
			String fName = view.getfName().trim();
			String lName = view.getlName().trim();
			String mobile = view.getMobileNum().trim();
			int membership = view.getMembershipType();
			String cardNum = view.getCardNum().trim();
			String email = view.getEmail().trim();

			if(model.verifyCustomer(fName, lName, mobile, membership, cardNum, email)) {
				JOptionPane.showMessageDialog(this.view,"Customer Successfuly Created!");
				new FrontPageController();
				view.dispose();
			};
		}
	}

	/**
	 *Changes the bottom label (errorLbl) in the view according to the int passed as argument.
	 *Error 1 = not all text fields were filled. Error 2 = card number with letters/symbols in it
	 *Error 3 = invalid email address. Error 4 = email already registered.
	 *
	 * @param error number
	 * @see CreateCustomerModel, CreateCustomerView
	 */
	public void errorMsg(int error) {
		view.getErrorLbl().setForeground(Color.RED);
		switch (error){
			case 1:
				view.getErrorLbl().setText("Please complete all fields.");
				break;
			case 2:
				view.getErrorLbl().setText("Make sure your card number has only numbers.");
				break;
			case 3:
				view.getErrorLbl().setText("Please use a valid email address.");
				break;
			case 4:
				view.getErrorLbl().setText("Email already registered.");
				break;
		}

	}
}