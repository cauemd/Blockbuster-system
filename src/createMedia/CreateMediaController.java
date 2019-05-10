package createMedia;

import java.awt.Color;

/*
 *@author = Caue Meireles Duarte 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import frontPage.FrontPageController;

//controller for the view that creates new media in the system
public class CreateMediaController implements ActionListener, WindowListener{
	
	private CreateMediaView view;
	private CreateMediaModel model;
	private int mediaType;
	
	public CreateMediaController() {
		this.model = new CreateMediaModel(this);
		this.view = new CreateMediaView(this);
	}
	
	public String[] gettingMovieGenres() {
		return model.movieGenreList();
	}
	
	public String[] gettingMusicGenres() {
		return model.musicGenreList();
	}
	
	/*
	 *  implementing WindowListener only to respond to closing event
	 */
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
		
		if(e.getActionCommand().equals("cancel")) {
			new FrontPageController();
			view.dispose();
		} else if (e.getActionCommand().equals("movie")) {
			view.addMovie();
			mediaType = 1;
		} else if (e.getActionCommand().equals("concert")) {
			view.addConcert();
			mediaType = 2;
		} else if (e.getActionCommand().equals("tv")) {
			view.addTV();
			mediaType = 3;
		
		/*last action command handling include the 3 buttons to add a movie, concert or tv set. 
		 * Since the 3 of them need the same validation, validating here and specifying the 
		 * behavior for each button after that will reduce code repetition
		 */ 
		} else {
			String title = view.getMediaTitle().trim();
			String release = view.getYear().trim();
			String genre = view.getGenre().trim();
			String creator = view.getCreator().trim();
			
			//validation method
			if (model.validating(title, release, creator, mediaType)) {
				int yearOfRelease = Integer.parseInt(release);
				model.createNewMedia(title, yearOfRelease, genre, creator, mediaType);
				JOptionPane.showMessageDialog(view,
					    "Item added to the database!");
				view.dispose();
				new FrontPageController();

			}
		}
	}
	
	public void changingErrorLbl(int error) {
		switch (error){
		case 1:
			view.getErrorLb().setForeground(Color.RED);
			view.getErrorLb().setText("Please complete all fields.");
			break;
		case 2:
			view.getErrorLb().setForeground(Color.RED);
			view.getErrorLb().setText("Use only number for the year of release.");
			break;
		case 3:
			view.getErrorLb().setForeground(Color.RED);
			view.getErrorLb().setText("Use only number for the Season Number.");
			break;
		}
		
	}
}
