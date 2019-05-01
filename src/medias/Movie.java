
/*
 *  @Author = Caue Meireles Duarte
 */
package medias;

import enuns.VideoGenre;

public class Movie extends Media {

	private String genre;
	private String director;
	
	public Movie (String title, int release, String genre, String director) {
		this.title = title;
		this.yearOfRelease = release;
		this.genre = genre;
		this.director = director;
	}
	
	public String toString() {
		return "Movie: " + this.title + ". Released in: " + this.yearOfRelease + ". Directed By:" +
				this.director + ". Genre: " + this.genre + ".";
	}
	
}
