
/*
 *  @Author = Caue Meireles Duarte
 */
package medias;

import enuns.VideoGenre;

public class Movie extends Media {

	private String director;
	
	public Movie (int id, String title, int release, String genre, String director) {
		super(id, title, release, genre);
		this.genre = genre;
		this.director = director;
	}
	
	//Overriding toString method, useful for testing/debugging purposes
	@Override
	public String toString() {
		return "Movie: " + this.title + ". Released in: " + this.yearOfRelease + ". Directed By:" +
				this.director + ". Genre: " + this.genre + ".";
	}
	
	public String getDirector() {
		return director;
	}
	
}
