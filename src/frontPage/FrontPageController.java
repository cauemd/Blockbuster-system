package frontPage;

/*
 *@author = Caue Meireles Duarte 
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import createCustomer.CreateCustomerController;
import createMedia.CreateMediaController;
import searchcustomer.SearchMediaController;

public class FrontPageController implements ActionListener, WindowListener{
	
	private FrontPageView view;
	
	public FrontPageController() {
		view = new FrontPageView(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	//creates option pane to confirm if the user wants to close the program
	@Override
	public void windowClosing(WindowEvent e) {
		int confirm = JOptionPane.showConfirmDialog(null,"Are You Sure You Want to Exit?","Exit",JOptionPane.YES_NO_OPTION, 1);
		if (confirm == 0) {
			System.exit(0);
		} else {
			view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
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

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//checks if customer exists in DB, changes error msg to RED if doesn't, instantiates customer page if it does
		if (e.getActionCommand().equals("custInfo")) { 		
			view.getErrorMsg().setForeground(Color.RED);
		
		//instantiates createCustomerController class
		} else if (e.getActionCommand().equals("newCust")) { 
			new CreateCustomerController();
			view.dispose();
		
		//instantiates CreateMediaController class
		} else if (e.getActionCommand().equals("addMedia")) { 
			new CreateMediaController();
			view.dispose();
			
		//instantiates SearchMediaController Class	
		} else {
			new SearchMediaController();
			view.dispose();
			
		}
	}

}
