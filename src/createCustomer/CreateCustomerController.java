package createCustomer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import frontPage.FrontPageController;

/*
 *@author = Caue Meireles Duarte 
 */


//controller for the view that creates a new customer in the system 
public class CreateCustomerController implements WindowListener, ActionListener{
	
	private CreateCustomerView view;
	
	public CreateCustomerController() {
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
		} else if (e.getActionCommand().equals("music")) {
			view.setMembershipType("Music Lover");
		} else if (e.getActionCommand().equals("video")) {
			view.setMembershipType("Video Lover");
		} else if (e.getActionCommand().equals("tv")) {
			view.setMembershipType("TV Lover");
		} else if (e.getActionCommand().equals("premium")) {
			view.setMembershipType("Premium");
		
		//register button, verifies if all fields were filled	
		} else {
			
			String fName = view.getfName();
			String lName = view.getlName();
			String mobile = view.getMobileNum();
			String membership = view.getMembershipType();
			String cardNum = view.getCardNum();
		
			//verifying if all text fields were filled, changes the lbl to an error msg if not
			if (fName.isBlank() || lName.isBlank() || mobile.isBlank() ||
					membership == null|| cardNum.isBlank()) {
				view.getErrorLbl().setForeground(Color.RED);
				view.getErrorLbl().setText("Please complete all fields.");
			} else {
				System.out.println("ae");
				
			}
		}
	}


}
