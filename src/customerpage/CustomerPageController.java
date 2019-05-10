
/*
 *  @Author = Caue Meireles Duarte
 */
package customerpage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import customer.Customer;
import frontPage.FrontPageController;
import medias.LiveConcert;
import medias.Media;
import medias.Movie;
import medias.TvBox;
import searchmedia.SearchMediaView;

public class CustomerPageController implements WindowListener, ActionListener {

	private CustomerPageView view;
	private CustomerPageModel model;
	private Customer customer;
	private int mediaSelected;
	private boolean customerReturnedMedia;

	public CustomerPageController(Customer cust) {
		this.model = new CustomerPageModel();
		this.customer = cust;
		this.view = new CustomerPageView(this);

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		model.updateLoyaltyPoints(customer.getPoints(), customer.getID());
		new FrontPageController();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		//handling buttons on the left-bar menu
		if (e.getActionCommand().equals("rented")) {
			view.rentedPanel();
		} else if(e.getActionCommand().equals("newRent")) {
			if(customer.getRentedItems().size() == 4 ) {
				JOptionPane.showMessageDialog(view,"Customer already renting the maximum number of medias!",
						"Rental Canceled",
						JOptionPane.WARNING_MESSAGE);
			} else {
				view.availableMediaPanel(customer.getMembershipType());
			}
		} else if (e.getActionCommand().equals("close")) {
			new FrontPageController();
			if (customerReturnedMedia) {
				model.updateLoyaltyPoints(customer.getPoints(), customer.getID());
			}
			view.dispose();

			//Handling the buttons in the rented media functionality	
		} else if(e.getActionCommand().equals("0")) {
			returningMedia(0);
			view.rentedPanel();
		} else if(e.getActionCommand().equals("1")) {
			returningMedia(1);
			view.rentedPanel();
		} else if(e.getActionCommand().equals("2")) {
			returningMedia(2);
			view.rentedPanel();
		} else if(e.getActionCommand().equals("3")) {
			returningMedia(3);
			view.rentedPanel();

			//Handling radio buttons in the media selection menu
		} else if (e.getActionCommand().equals("movies")) {
			mediaSelected = 1;
			settingUpJTable(1);
			populatingTable(1);			
		} else if (e.getActionCommand().equals("concerts")) {
			mediaSelected = 2;
			settingUpJTable(2);
			populatingTable(2);
		} else if (e.getActionCommand().equals("tv")) {
			mediaSelected = 3;
			settingUpJTable(3);
			populatingTable(3);

			//Handling media rental button
		} else if (e.getActionCommand().equals("rentItem")) {
			rentMedia();
		}
	}

	/**
	 *Returning media functionality of the system. Gets the media being returned and calculates the fee
	 *considering rental date and current date and adds 10 loyalty points. If the user has more than 100
	 *loyalty points, gives the option of free rental. Overdue returns don't get points or free rental. 
	 *
	 *@param An int representing the index in the ArrayList of the media being returned 
	 *@see SearchMediaView, Customer
	 */
	private void returningMedia(int index) {

		//calculating for how many days the media was rented
		Media returnedMedia = customer.getRentedItems().get(index);
		Date today = new Date();
		Date dateRented = null;
		try {
			dateRented = new SimpleDateFormat("yyyy-MM-dd").parse(returnedMedia.getDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long difference = TimeUnit.DAYS.convert(today.getTime() - dateRented.getTime(), TimeUnit.MILLISECONDS);

		//return on time
		if(difference <= 3) {
			Object[] options = {"Ok", "Cancel", "Use Loyalty Points"};
			int n = JOptionPane.showOptionDialog(view, "Returning Media: " + returnedMedia.getTitle() + ".\n" +
					"It will be €5.00. Do you want to use your Loyalty Points?","Returning Media",
					JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
					null, options, options[0]);
			if (n == 0) {
				customer.getRentedItems().remove(index);
				model.returnMedia(returnedMedia);
				JOptionPane.showMessageDialog(view, "Item Returned!");
				customer.addPoints();
				this.customerReturnedMedia = true;
				view.getPointsLbl().setText("The customer has " + getCustomerPoints() + " loyalty points.");

				//Using loyalty points
			} else if (n == 2) {
				if(customer.getPoints() < 100) {
					JOptionPane.showMessageDialog(view, "The costumer doesn't have enough points.",
							"Insuficient Points", JOptionPane.WARNING_MESSAGE);
				} else {
					customer.getRentedItems().remove(index);
					model.returnMedia(returnedMedia);
					JOptionPane.showMessageDialog(view, "Item Returned!");
					customer.removePoints();
					this.customerReturnedMedia = true;
					view.getPointsLbl().setText("The customer has " + getCustomerPoints() + " loyalty points.");
				}
			}

			//overdue
		} else {
			Object[] options = {"Ok", "Cancel"};
			int n = JOptionPane.showOptionDialog(view, "Returning Media: " + returnedMedia.getTitle() +
					"\nThe fee is €5.00 plus €2.50 per overdue day = €" + (difference - 3) * 2.50 + 5, "Overdue",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
			if (n == 0) {
				customer.getRentedItems().remove(index);
				model.returnMedia(returnedMedia);
				customer.addPoints();
				this.customerReturnedMedia = true;
				JOptionPane.showMessageDialog(view, "Item Returned!");
				view.getPointsLbl().setText("The customer has " + getCustomerPoints() + " loyalty points.");
			}
		}
	}

	/**
	 *Remove all rows from the view's JTable and change the TableHeaders to the appropriated 
	 *type of media according to the type of media selected in the RadioButton
	 *
	 *@param an int representing the type of media selected. 1 for Movies, 2 for Concerts, 3 for TvShows
	 * @see SearchMediaView
	 */
	private void settingUpJTable(int mediaType) {

		//setting up the TableHeaders
		DefaultTableModel tblModel = (DefaultTableModel) view.getTable().getModel();
		tblModel.setColumnCount(5);	
		view.getTable().getColumnModel().getColumn(0).setHeaderValue("Title");
		view.getTable().getColumnModel().getColumn(0).setPreferredWidth(230);
		view.getTable().getColumnModel().getColumn(1).setHeaderValue("Genre");
		view.getTable().getColumnModel().getColumn(2).setHeaderValue("Year of Release");
		view.getTable().getColumnModel().getColumn(4).setHeaderValue("ID");

		if (mediaType == 1) {			//movies
			view.getTable().getColumnModel().getColumn(3).setHeaderValue("Director");

		} else if (mediaType == 2) {	//concerts
			view.getTable().getColumnModel().getColumn(3).setHeaderValue("Band");

		} else {						//TvShow
			view.getTable().getColumnModel().getColumn(3).setHeaderValue("Season N.");
		}

		//Removing all rows from table
		while(tblModel.getRowCount() > 0) {
			tblModel.removeRow(0);
		}
	}

	/**
	 *Retrieves an ArrayList<Media> of one of the subclasses from the database using the model, 
	 *verifies which of them are being rented and feeds the data to the view's table;
	 *
	 *@param an int representing the type of media selected. 1 for Movies, 2 for Concerts, 3 for TvShows
	 * @see SearchMediaView, SearchMediaModel
	 */
	private void populatingTable(int mediaType) {
		DefaultTableModel tblModel = (DefaultTableModel) view.getTable().getModel();
		ArrayList<Media> mediaList = model.getTitlesFromDB(mediaType);
		Object[] data = new Object[5];

		//Media class generic data
		for (Media media : mediaList) {

			if (!model.isAvailable(media.getID(), mediaType)) {
				continue;
			}

			data[0] = media.getTitle();
			data[1] = media.getGenre();
			data[2] = media.getYearOfRelease();
			data[4] = media.getID();

			//Movie subclass data
			if(mediaType == 1) {
				data[3] = ((Movie) media).getDirector();

				//Concert subclass data	
			} else if(mediaType == 2) {
				data[3] = ((LiveConcert) media).getBand();

				//TVBox subclass data	
			} else {
				data[3] = ((TvBox) media).getSeasonNum();
			}


			tblModel.addRow(data);			
		}

	}

	/**
	 *Returns number of loyalty point of customer profile currently being accessed by the system.
	 *
	 * @see Customer, Membership
	 */
	public int getCustomerPoints() {
		return customer.getPoints();
	}

	/**
	 *Returns an Array List with all medias the customer is currently renting (if any).
	 *
	 * @see Customer, Membership
	 */
	public ArrayList<Media> getCustomerRentedList() {
		return customer.getRentedItems();
	}

	/**
	 *Gets the "ID" value of selected row in the table with available medias and the 
	 *type of media selected then passes the values to the model. Includes confirmation dialog.
	 *
	 * @see CustomerPageView, CustomerPageModel
	 */
	public void rentMedia() {
		int selectedRow = view.getTable().getSelectedRow();
		if (selectedRow == -1) {
			return;
		} else {
			int id = (int) view.getTable().getValueAt(selectedRow, 4);
			String title = (String) view.getTable().getValueAt(selectedRow, 0); 
			int n = JOptionPane.showConfirmDialog(view,
					"Now renting: " + title + "\nConfirm?",
					"Rental Confirmation",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {

				/*can't use customer type or media id on to determine the kind of media selected  because
				 *the customer could be a premium and the id sequence might change, which would happen
				 *if the number of Movies or Live Concerts exceeds 500
				 */
				model.rent(this.customer.getID(), id, this.mediaSelected);
				this.customer = model.updateCustomer(customer.getEmail());
				JOptionPane.showMessageDialog(view, "Rental Successful!");
				view.availableMediaPanel(customer.getMembershipType());
			}
		}

	}
}
