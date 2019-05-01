
/*
 *  @Author = Caue Meireles Duarte
 */
package searchcustomer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import frontPage.FrontPageController;
import medias.Media;
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


	/**
	 *Gets an Object of Media objects from the database for and specific
	 *type of media, clears the view's table and updates it with the new data.
	 *Param determines which subclass of media. 1 for movies, 2 for live concerts and 3 for Tv shows.
	 *
	 * @param type an int that will pick the table which table from the database to read.
	 * @return A collection of one of the subclasses of Media
	 * @see Media
	 */
	public Object[][] getMediaList(int x) {
		Object[][] testing = model.getTitlesFromDB(x);
		System.out.println(testing[0][0]);
		System.out.println(testing[0][0]);
		return testing ;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("movies")) {
			
			//Object[][] temp = getMediaList(1);
			
		} else if (e.getActionCommand().equals("concerts")) {
			//Object[][] temp = getMediaList(2);

		} else if (e.getActionCommand().equals("tv")) {
			//Object[][] temp = getMediaList(3);
			
		//return to front page button	
		} else {
			new FrontPageController();
			view.dispose();
		}
	}

}
