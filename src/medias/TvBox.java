
/*
 *  @Author = Caue Meireles Duarte
 */
package medias;

import enuns.VideoGenre;

public class TvBox extends Media {
	
	private String genre;
	private int seasonNumber;
	
	public TvBox(String title, int release, String genre, int season) {
		this.title = title;
		this.yearOfRelease = release;
		this.genre = genre;
		this.seasonNumber = season;
	}

	public String toString() {
		return "Tv Show: " + this.title + ". Season: " + this.seasonNumber + 
				". Genre: " + this.genre + ". Year of Release: " + this.yearOfRelease + ".";
	}
}
