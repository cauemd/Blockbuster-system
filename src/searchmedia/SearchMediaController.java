
/*
 *  @Author = Caue Meireles Duarte
 */
package searchmedia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import frontPage.FrontPageController;
import medias.*;
import medias.TvBox;

//controller to the view that will list all the titles in the DB
public class SearchMediaController implements ActionListener, WindowListener {

	private SearchMediaView view;
	private SearchMediaModel model;

	//Constructor instantiates both View and Model classes and saves them in the
	//class variables
	public SearchMediaController() {

		this.model = new SearchMediaModel();
		this.view = new SearchMediaView(this);
	}

	//Takes the user to the front page if the view is closed
	@Override
	public void windowClosing(WindowEvent e) {
		new frontPage.FrontPageController();	
		view.dispose();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

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

	//handles the radiobuttons, updating the view's table with data the model receives from the database 
	//and the button that returns the user to the front page.
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("movies")) {
			settingUpJTable(1);
			populatingTable(1);
			
		} else if (e.getActionCommand().equals("concerts")) {
			settingUpJTable(2);
			populatingTable(2);

		} else if (e.getActionCommand().equals("tv")) {
			settingUpJTable(3);
			populatingTable(3);
			
		//return to front page button	
		} else {
			new FrontPageController();
			view.dispose();
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
			data[0] = media.getTitle();
			data[1] = media.getGenre();
			data[2] = media.getYearOfRelease();
			
			//Movie subclass data
			if(mediaType == 1) {
				data[3] = ((Movie) media).getDirector();
				data[4] = model.isRented(media.getID(), mediaType);
				
			//Concert subclass data	
			} else if(mediaType == 2) {
				data[3] = ((LiveConcert) media).getBand();
				data[4] = model.isRented(media.getID(), mediaType);
				
			//TVBox subclass data	
			} else {
				data[3] = ((TvBox) media).getSeasonNum();
				data[4] = model.isRented(media.getID(), mediaType);
			}
			
			tblModel.addRow(data);			
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
		view.getTable().getColumnModel().getColumn(1).setHeaderValue("Genre");
		view.getTable().getColumnModel().getColumn(2).setHeaderValue("Year of Release");
		view.getTable().getColumnModel().getColumn(4).setHeaderValue("Available");
		
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

}
