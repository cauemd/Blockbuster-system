
/*
 *  @Author = Caue Meireles Duarte
 */
package dbConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import customer.Customer;
import customer.Membership;
import medias.LiveConcert;
import medias.Media;
import medias.Movie;
import medias.TvBox;

//Class responsible for handling interaction with the Database
public class DbConnector {

	private Connection conn;

	/**
	 *Creates a new object that will handle the connection to the database.
	 *
	 * @return An instance of the DbConnector class.
	 */
	public DbConnector(){

		try{
			// Load the database driver
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance() ;

			String dbServer = "jdbc:mysql://localhost:3306/ultravision?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "root";
			String password = "";


			// Get a connection to the database
			Connection conn = DriverManager.getConnection(dbServer, user, password) ;
			this.conn = conn;

		}catch( SQLException se ){
			System.out.println( "SQL Exception:" ) ;

			// Loop through the SQL Exceptions
			while( se != null ){
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e ){
			System.out.println( e ) ;
		}
	}

	/**
	 *Creates a new entry for a customer in the database, returning true if successful.
	 *If there is already an user with the same email address, returns false.
	 *
	 * @param a Customer object from which data will be taken to put into the database.
	 * @return true if the data can be inserted into the database, false otherwise.
	 * @see Customer, Membership
	 */
	public boolean insertCustomerInDB(Customer customer, Membership memberCard) {

		PreparedStatement stmt = null;

		//verifying if user already exists
		try {
			String query = "SELECT * FROM customers WHERE email = ?;";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, customer.getEmail());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return false;
			}	

			//inserting user into the database
			query = "INSERT INTO `customers` (`custID`, `email`, `fName`, `lName`, `mobile`, `cardNo`,"
					+ " `memberType`, `loyaltyPts`) VALUES (NULL, ?, ?, ?, ?, ?, ?, '0');";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, customer.getEmail());
			stmt.setString(2, customer.getfName());
			stmt.setString(3, customer.getlName());
			stmt.setString(4, customer.getMobile());
			stmt.setDouble(5, memberCard.getCardNum());
			stmt.setInt(6, memberCard.getmembershipType());
			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 *Returns an ArrayList of Media objects with all the entries in the database for and specific
	 *type of media. 1 for movies, 2 for live concerts and 3 for Tv shows.
	 *
	 * @param type an int that will pick the table which table from the database to read.
	 * @return A collection of one of the subclasses of Media
	 * @see Media
	 */
	public ArrayList<Media> getMediaArrayFromDB(int type){

		PreparedStatement stmt = null;
		ArrayList<Media> mediaArray = new ArrayList<>();

		try {
			//Checking movies table
			if (type == 1) {				
				String query = "SELECT * FROM movies;";
				stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Media movie = new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("yearOfRelease"), 
							rs.getString("genre"), rs.getString("director"));
					mediaArray.add(movie);
				}

				//Checking Live Concerts table	
			} else if (type == 2) {
				String query = "SELECT * FROM liveconcerts;";
				stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Media concert = new LiveConcert(rs.getInt("id"), rs.getString("title"), rs.getInt("yearOfRelease"), 
							rs.getString("genre"), rs.getString("band"));
					mediaArray.add(concert);
				}

				//Checking TvBox table 
			} else {
				String query = "SELECT * FROM tvshow;";
				stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Media tvshow = new TvBox(rs.getInt("id"), rs.getString("title"), rs.getInt("yearOfRelease"), 
							rs.getString("genre"), rs.getInt("seasonNumber"));
					mediaArray.add(tvshow);
				}
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}

		return mediaArray;
	}

	/**
	 *Verifies if there's an entry with the ID passed as argument is in one the database's table with
	 *the medias that are currently being rented and unavailable. The second parameter specifies the 
	 *media type, therefore which table will be consulted.
	 *
	 * @param mediaID - the ID of the media that will be searched in the database.
	 * @param  mediaType - int representing the type of media. 1 for Movies, 2 for Concerts, 3 for TvShows
	 * @return true if the Connector can't find the media id in the respective rented 
	 * 		   media table in the database, false otherwise.
	 * @see SearchMediaModel
	 */
	public boolean checkAvailability(int mediaID, int mediaType) {
		PreparedStatement stmt = null;

		try {
			//Checking movies table
			if (mediaType == 1) {				
				String query = "SELECT * FROM customers_movies WHERE movieID = ?;";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, mediaID);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					return false;
				}

				//Checking Live Concerts table	
			} else if (mediaType == 2) {
				String query = "SELECT * FROM customers_concerts WHERE concertID = ?;";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, mediaID);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					return false;
				}

				//Checking TvBox table 
			} else {
				String query = "SELECT * FROM customers_tvshow WHERE tvshowID = ?;";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, mediaID);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					return false;
				}
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 *Creates a new entry in one of the rented items table according to the media type, customer ID
	 * and the media id passed as arguments, using the current system time as date.
	 *
	 * @param custID - the ID of the customer making the rental.
	 * @param mediaID - the ID of the media that will be searched in the database.
	 * @param  mediaType - int representing the type of media. 1 for Movies, 2 for Concerts, 3 for TvShows
	 * 
	 * @see CustomerPageModel
	 */
	public void rentItem(int custID, int mediaID, int mediaType) {

		PreparedStatement stmt = null;

		try {
			//movie rental
			if (mediaType == 1) {
				String query = "INSERT INTO customers_movies (custID, movieID, date) VALUES (?, ?, now());";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, custID);
				stmt.setInt(2, mediaID);
				stmt.executeUpdate();	

				//concert rental
			} else if (mediaType == 2) {
				String query = "INSERT INTO customers_concerts (custID, concertID, date) VALUES (?, ?, now());";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, custID);
				stmt.setInt(2, mediaID);
				stmt.executeUpdate();	

				//tv show rental	
			} else {
				String query = "INSERT INTO customers_tvshow (custID, tvshowID, date) VALUES (?, ?, now());";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, custID);
				stmt.setInt(2, mediaID);
				stmt.executeUpdate();	
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 *Searches in the database for an entry in the customers table that has the same email value as the 
	 *argument. If there is no such entry, returns null. Otherwise returns a Customer object with the
	 *data from the database.
	 *
	 * @param  email - email of the Customer.
	 * @return A customer object if there is an entry matching the email, null otherwise.
	 * 
	 * @see CustomerPageModel, Customer, Membership
	 */
	public Customer getCustomerInfo(String email) {

		PreparedStatement stmt = null;

		try {

			String query = "SELECT * FROM customers WHERE email = ?;";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				//creating Membership
				Double cardNum = rs.getDouble("cardNo");
				int memberType = rs.getInt("memberType");
				int loyaltyPts = rs.getInt("loyaltyPts");
				Membership mship = new Membership(loyaltyPts, cardNum, memberType);

				//creating Customer
				int custID = rs.getInt("custID");
				String fName = rs.getString("fName");
				String lName = rs.getString("lName");
				String mobile = rs.getString("mobile");

				//getting rented media, if any
				ArrayList<Media> mediaList = getRentedItemsFromDB(custID, memberType);


				Customer cust = new Customer(custID, fName, lName, mobile, email, mship, mediaList);
				return cust;
			} 

		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 *Searches in the database for any entries in the rented media tables that matches the customer ID.
	 *If customer has rented media, goes to the media table in the database to retrieve media detais.
	 *Uses member type to select the correct table (or all of them in case of premium membership).
	 *
	 *
	 * @param  custID - id of the Customer.
	 * @param memberType - type of membership. 1 - MovieLover, 2- MusicLover, 3- TvLover, 4 - Premium.
	 * @return An ArrayList with all medias currently being rented by the customer.
	 * 
	 * @see CustomerPageModel, Customer, Media
	 */
	private ArrayList<Media> getRentedItemsFromDB(int custID, int memberType) {

		ArrayList<Media> rentedItems = new ArrayList<>();
		PreparedStatement stmt = null;

		try {
			//Checking movies table
			if (memberType == 1 || memberType == 4) {				
				String query = "SELECT * FROM customers_movies WHERE custID = ?;";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, custID);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int mediaID = rs.getInt("movieID");
					String date = rs.getString("date");

					//getting Movie data
					query = "SELECT * FROM movies WHERE id = ?";
					stmt = conn.prepareStatement(query);
					stmt.setInt(1, mediaID);
					ResultSet rs2 = stmt.executeQuery();
					rs2.next();

					String title = rs2.getString("title");
					int year = rs2.getInt("yearOfRelease");
					String genre = rs2.getString("genre");
					String director = rs2.getString("director");
					Media media = new Movie(mediaID, title, year, genre, director);
					media.setRentedDate(date);
					rentedItems.add(media);
				}

			}

			//Checking Live Concerts table	
			if (memberType == 2 || memberType == 4) {
				String query = "SELECT * FROM customers_concerts WHERE custID = ?;";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, custID);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					int mediaID = rs.getInt("concertID");
					String date = rs.getString("date");

					//getting Concert data
					query = "SELECT * FROM liveconcerts WHERE id = ?";
					stmt = conn.prepareStatement(query);
					stmt.setInt(1, mediaID);
					ResultSet rs2 = stmt.executeQuery();
					rs2.next();

					String title = rs2.getString("title");
					int year = rs2.getInt("yearOfRelease");
					String genre = rs2.getString("genre");
					String band = rs2.getString("band");
					Media media = new LiveConcert(mediaID, title, year, genre, band);
					media.setRentedDate(date);
					rentedItems.add(media);

				}
			}

			//Checking TvBox table 
			if (memberType == 3 || memberType == 4){
				String query = "SELECT * FROM customers_tvshow WHERE custID = ?;";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, custID);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					int mediaID = rs.getInt("tvshowID");
					String date = rs.getString("date");

					//getting Concert data
					query = "SELECT * FROM tvshow WHERE id = ?";
					stmt = conn.prepareStatement(query);
					stmt.setInt(1, mediaID);
					ResultSet rs2 = stmt.executeQuery();
					rs2.next();

					String title = rs2.getString("title");
					int year = rs2.getInt("yearOfRelease");
					String genre = rs2.getString("genre");
					int seasonN = rs2.getInt("seasonNumber");
					Media media = new TvBox(mediaID, title, year, genre, seasonN);
					media.setRentedDate(date);
					rentedItems.add(media);
				}
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}


		return rentedItems;
	}

	/**
	 *Deletes the entry related to the media id being passed as argument from the corresponding rented
	 *medias table in the database.
	 *
	 *@param mediaID - id of the media being returned
	 *@param mediaType - type of the media. 1 - Movie, 2-LiveConcert, 3- TvShow
	 *@see Media, CustomerPageModel
	 */
	public void returnMedia(int id, int mediaType) {
		PreparedStatement stmt = null;

		try {
			//movie return
			if (mediaType == 1) {
				String query = "DELETE FROM customers_movies WHERE movieID = ?;";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, id);
				stmt.executeUpdate();
			}

			//live concert return
			if (mediaType == 2) {
				String query = "DELETE FROM customers_concerts WHERE concertID = ?;";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, id);
				stmt.executeUpdate();
			}

			//Tv show return
			if (mediaType == 3) {
				String query = "DELETE FROM customers_tvshow WHERE tvshowID = ?;";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, id);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 *Updates customer's loyalty points in the database.
	 *
	 *@param points - the new amount of loyalty points the membership card has
	 *@param custID - Customer's ID
	 *@see Customer, Membership
	 */
	public void updatePoints(int points, int custID) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try {
			String query = "UPDATE customers SET loyaltyPts = ? WHERE custID = ?;";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, points);
			stmt.setInt(2, custID);
			stmt.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 *Creates a new media entry in the database.
	 *
	 * @param newMedia - the Media being inserted in the database
	 * @param mediaType - the type of media being created. 1 - Movie. 2 - Live Concert. 3 - Tv Show.
	 *
	 * @see Media, CreateCustomerModel
	 */
	public void addMediaToDB(Media newMedia, int mediaType) {
		
		PreparedStatement stmt = null;
		//Inserting movie
		try {
		if (mediaType == 1) {
			String query = "INSERT INTO movies (id, title, yearOfRelease, genre, director)"
					+ "VALUES(null, ?, ?, ?, ?);";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, newMedia.getTitle());
			stmt.setInt(2, newMedia.getYearOfRelease());
			stmt.setString(3, newMedia.getGenre());
			stmt.setString(4, ((Movie) newMedia).getDirector());
			stmt.executeUpdate();
		//Inserting live concert
		} else if (mediaType == 2) {
			String query = "INSERT INTO liveconcerts (id, title, yearOfRelease, genre, band)"
					+ "VALUES(null, ?, ?, ?, ?);";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, newMedia.getTitle());
			stmt.setInt(2, newMedia.getYearOfRelease());
			stmt.setString(3, newMedia.getGenre());
			stmt.setString(4, ((LiveConcert) newMedia).getBand());
			stmt.executeUpdate();
		//Inserting tv show
		} else {
			String query = "INSERT INTO tvshow (id, title, yearOfRelease, genre, seasonNumber)"
					+ "VALUES(null, ?, ?, ?, ?);";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, newMedia.getTitle());
			stmt.setInt(2, newMedia.getYearOfRelease());
			stmt.setString(3, newMedia.getGenre());
			stmt.setInt(4, ((TvBox) newMedia).getSeasonNum());
			stmt.executeUpdate();
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
