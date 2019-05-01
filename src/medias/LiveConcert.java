
/*
 *  @Author = Caue Meireles Duarte
 */
package medias;

import enuns.MusicGenre;

public class LiveConcert extends Media {
	
	private String genre;
	private String band;
	
	public LiveConcert(String title, int release, String genre, String band) {
		this.title = title;
		this.yearOfRelease = release;
		this.genre = genre;
		this.band = band;
	}
	
	public String toString() {
		return "Title: " + this.title + ". Released in: " + this.yearOfRelease + 
				". Band: " + this.band + ". Genre: " + this.genre + "."; 
	}

}
