
/*
 *  @Author = Caue Meireles Duarte
 */
package medias;

import enuns.VideoGenre;

public class TvBox extends Media {
	
	private int seasonNumber;
	
	//Default Constructor
	public TvBox(int id, String title, int release, String genre, int season) {
		super(id, title, release, genre);
		this.genre = genre;
		this.seasonNumber = season;
	}

	//Overriding toString method, useful for testing/debugging purposes
	@Override
	public String toString() {
		return "Tv Show: " + this.title + ". Season: " + this.seasonNumber + 
				". Genre: " + this.genre + ". Year of Release: " + this.yearOfRelease + ".";
	}

	//seasonNumber attribute getter
	public int getSeasonNum() {
		return this.seasonNumber;
	}
}
