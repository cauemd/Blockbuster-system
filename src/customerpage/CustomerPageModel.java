
/*
 *  @Author = Caue Meireles Duarte
 */
package customerpage;

import java.util.ArrayList;

import customer.Customer;
import dbConnector.DbConnector;
import medias.LiveConcert;
import medias.Media;
import medias.Movie;

public class CustomerPageModel {
	
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
	public boolean isAvailable(int id, int mediaType) {
		DbConnector db = new DbConnector();
		return db.checkAvailability(id,mediaType); 
	}

	/**
	 *Uses the database connector to add an entry to the rented items table in the database according to the
	 *arguments passed.
	 *
	 *@param email - email of the customer making the rental.
	 *@param id - int representing the id of the media being rented.
	 *@param  mediaType - int representing the type of media. 1 for Movies, 2 for Concerts, 3 for TvShows.
	 * @see DbConnector
	 */
	public void rent(int custID, int id, int mediaType) {
		DbConnector db = new DbConnector();
		db.rentItem(custID, id, mediaType);
	}

	/**
	 *Uses the database connector to return a media, removing the entry from the corresponding table.
	 *Uses reflection to choose correct table.
	 *
	 *@param media - Media Object being returned
	 *@see DbConnector
	 */
	public void returnMedia(Media media) {
		DbConnector db = new DbConnector();
		if (media instanceof Movie) {
			db.returnMedia(media.getID(), 1);
		} else if (media instanceof LiveConcert) {
			db.returnMedia(media.getID(), 2);
		} else {
			db.returnMedia(media.getID(), 3);
		}
		
	}
	
	/**
	 *Updates customer's data according to the database.
	 *
	 *@param email - the customer's email that will be used to retrieve the data from the database.
	 *@see DbConnector, Customer
	 */
	public Customer updateCustomer(String email) {
		DbConnector db = new DbConnector();
		return db.getCustomerInfo(email);
		
	}

	/**
	 *Calls method that updates customer's Loyalty Points in the database if he/she returns a movie.
	 *
	 *@param points - the new amount of loyalty points the membership card has
	 *@param custID - Customer's ID
	 *@see DbConnector, Customer, Membership
	 */
	public void updateLoyaltyPoints(int points, int custID) {
		DbConnector db = new DbConnector();
		db.updatePoints(points, custID);
		
	}

}
