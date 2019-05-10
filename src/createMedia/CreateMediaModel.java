
/*
 *  @Author = Caue Meireles Duarte
 */
package createMedia;

import dbConnector.DbConnector;
import enuns.MusicGenre;
import enuns.VideoGenre;
import medias.LiveConcert;
import medias.Media;
import medias.Movie;
import medias.TvBox;

//Logic part of the "add media" functionality of the system
public class CreateMediaModel {

	private CreateMediaController controller;

	public CreateMediaModel(CreateMediaController controller) {
		this.controller = controller;
	}

	//generates a String array with the values from all constants in the VideoGenre enum
	public String[] movieGenreList() {
		String[] genreList = VideoGenre.getGenres();
		return genreList;
	}

	//generates a String array with the values from all constants in the MusicGenre enum
	public String[] musicGenreList() {
		String[] genreList = MusicGenre.getGenres();
		return genreList;
	}

	/**
	 *Validates the data from the text fields. If data is invalid, calls method that displays error message 
	 *according to the invalid information and returns false. Otherwise returns true
	 *
	 * @param title - String representing the Title TextField
	 * @param release - String representing the Year Of Release TextField
	 * @param creator - String representing the director, band or number of season of the media
	 * @param mediaType - A int representing the media type. 1 - Movie, 2 - Live Concert. 3 - Tv Show.
	 * @return true if the data is valid, false otherwise.
	 * @see CreateMediaView, CreateMediaController
	 */
	public boolean validating(String title, String release, String creator, int mediaType) {
		if (title.isBlank() || release.isBlank() || creator.isBlank() || release.isBlank()) {
			controller.changingErrorLbl(1);
			return false;
		} else if (!release.matches("[0-9]+")){
			controller.changingErrorLbl(2);
			return false;
		} else if (!creator.matches("[0-9]+") && mediaType == 3) {
			controller.changingErrorLbl(3);
			return false;
		}
		return true;
	}

	/**
	 *Creates Media object with the information from the view and calls method to add the media to the
	 *database
	 *
	 * @param title - String representing the Title TextField
	 * @param release - String representing the Year Of Release TextField
	 * @param creator - String representing the director, band or number of season of the media
	 * @param mediaType - A int representing the media type. 1 - Movie, 2 - Live Concert. 3 - Tv Show.
	 * @return true if the data is valid, false otherwise.
	 * @see CreateMediaView, CreateMediaController
	 */
	public void createNewMedia(String title, int release, String genre, String creator, int mediaType) {
		Media newMedia = null;
		DbConnector db = new DbConnector();
		
		//creating movie
		if (mediaType == 1) {
			newMedia = new Movie(0, title, release, genre, creator);
		//creating live concert	
		} else if (mediaType == 2) {
			newMedia = new LiveConcert(mediaType, title, release, genre, creator);
		//creating tv show
		} else {
			int season = Integer.parseInt(creator);
			newMedia = new TvBox(0, title, release, genre, season);
		}
		db.addMediaToDB(newMedia, mediaType);
	}


}
