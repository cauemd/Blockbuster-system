
/*
 *  @Author = Caue Meireles Duarte
 */
package medias;

import searchcustomer.SearchMediaView;

public abstract class Media {
	
	protected int id;
	protected String title;
	protected int yearOfRelease;
	protected String genre;
	
	protected Media(int id, String title, int yearOfRelease, String genre) {
		this.id = id;
		this.title = title;
		this.yearOfRelease = yearOfRelease;
		this.genre = genre;
	}
	
	/**
	 *Return the title of the Media
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 *Return the title of the Media
	 */
	public int getYearOfRelease() {
		return this.yearOfRelease;
	}
	
	/**
	 *Return the ID of the Media
	 */
	public int getID() {
		return this.id;
	}

	public String getGenre() {
		return this.genre;
	}
	
}
