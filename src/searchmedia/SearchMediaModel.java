
/*
 *  @Author = Caue Meireles Duarte
 */
package searchmedia;

import java.util.ArrayList;

import dbConnector.DbConnector;
import medias.Media;

public class SearchMediaModel {
	
	//Default constructor
	public SearchMediaModel() {	}

	/**
	 *Retrieves an ArrayList<Media> with all the entries on the database 
	 *of one of Media's subclasses, determined by the int used as argument.
	 *
	 *@param an int representing the type of media selected. 1 for Movies, 2 for Concerts, 3 for TvShows
	 * @see DbConnector
	 */
	public ArrayList<Media> getTitlesFromDB(int mediaType) {
		DbConnector db = new DbConnector();
		ArrayList<Media> list = db.getMediaArrayFromDB(mediaType);
		return list;

	}
	
	/**
	 *Uses the database connector to verify if a specific media is currently being rented, 
	 *therefore unavailable.
	 *
	 *@param mediaID - the ID of the media that will be searched in the database.
	 *@param  mediaType - int representing the type of media. 1 for Movies, 2 for Concerts, 3 for TvShows
	 * @see DbConnector
	 */
	public boolean isRented(int mediaID, int mediaType) {
		DbConnector db = new DbConnector();
		return db.checkAvailability(mediaID,mediaType); 
		
	}

}
