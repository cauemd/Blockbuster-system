
/*
 *  @Author = Caue Meireles Duarte
 */
package medias;

public class LiveConcert extends Media {
	
	private String band;
	
	//Default Constructor
	public LiveConcert(int id, String title, int release, String genre, String band) {
		super(id, title, release, genre);
		this.genre = genre;
		this.band = band;
	}
	
	//Overriding toString method, useful for testing/debugging purposes
	@Override
	public String toString() {
		return "Title: " + this.title + ". Released in: " + this.yearOfRelease + 
				". Band: " + this.band + ". Genre: " + this.genre + "."; 
	}

	//band attribute getter
	public String getBand() {
		return this.band;
	}

}
