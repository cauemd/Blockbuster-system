
/*
 *  @Author = Caue Meireles Duarte
 */
package dbConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import medias.LiveConcert;
import medias.Media;
import medias.Movie;
import medias.TvBox;


public class DbConnector {

	private Statement stmt;
	private Connection conn;

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

			// Get a statement from the connection
			this.stmt = conn.createStatement() ;
		}
		catch( SQLException se ){
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
					Media movie = new Movie(rs.getString("title"), rs.getInt("yearOfRelease"), 
							rs.getString("genre"), rs.getString("director"));
					mediaArray.add(movie);
				}

			//Checking Live Concerts table	
			} else if (type == 2) {
				String query = "SELECT * FROM liveconcerts;";
				stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Media concert = new LiveConcert(rs.getString("title"), rs.getInt("yearOfRelease"), 
							rs.getString("genre"), rs.getString("band"));
					mediaArray.add(concert);
				}

			//Checking TvBox table 
			} else {
				String query = "SELECT * FROM tvshow;";
				stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Media tvshow = new TvBox(rs.getString("title"), rs.getInt("yearOfRelease"), 
							rs.getString("genre"), rs.getInt("seasonNumber"));
					mediaArray.add(tvshow);
					System.out.println(tvshow);
				
				}
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}


}
