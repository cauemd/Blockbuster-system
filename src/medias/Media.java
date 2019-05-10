
/*
 *  @Author = Caue Meireles Duarte
 */
package medias;

//Abstract class responsible for keeping general information regarding the different types of media
public abstract class Media {
	
	protected int id;
	protected String title;
	protected int yearOfRelease;
	protected String genre;
	protected String rentedDate;
	
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

	/**
	 *Return the genre of the Media
	 */
	public String getGenre() {
		return this.genre;
	}
	
	/**
	 *Sets the date the media was rented
	 */
	public void setRentedDate(String date) {
		this.rentedDate = date;
	}

	/**
	 *Return the date the media was rented
	 */
	public String getDate() {
		return rentedDate;
	}
	
}
