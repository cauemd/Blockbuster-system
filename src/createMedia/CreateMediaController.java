package createMedia;

import java.awt.Color;

/*
 *@author = Caue Meireles Duarte 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import frontPage.FrontPageController;

//controller for the view that creates new media in the system
public class CreateMediaController implements ActionListener, WindowListener{
	
	private CreateMediaView view;
	private CreateMediaModel model;
	
	public CreateMediaController() {
		this.view = new CreateMediaView(this);
		this.model = new CreateMediaModel();
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
		} else if (e.getActionCommand().equals("concert")) {
			view.addConcert();
		} else if (e.getActionCommand().equals("tv")) {
			view.addTV();
		
		//this will include the 3 buttons to add a movie, concert or tv set. Since the 3 of them need the same validation,
		//validating here and specifying the behavior for each button after that will reduce code repetition
		} else {
			String title = view.getMediaTitle();
			String release = view.getYear();
			String genre = view.getGenre();
			String creator = view.getCreator();
			
			if (title.isBlank() || release.isBlank() || creator.isBlank()) {
				view.getErrorLb().setForeground(Color.RED);
				view.getErrorLb().setText("Please complete all fields.");
			} else {
				
			}
			
		}
	}

}
