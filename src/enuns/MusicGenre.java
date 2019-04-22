package enuns;

/*
 *@author = Caue Meireles Duarte 
 */

//Enuns for all Live Concert genres
public enum MusicGenre {
	
	ROCK("Rock"),
	BLUES("Blues"),
	REGGAE("Reggae"),
	COUNTRY("Country"),
	LOFI("Lo-Fi"),
	POP("Pop"),
	DISCO("Disco"),
	RAP("Rap"),
	CLASSIC("Classic"),
	JAZZ("Jazz"),
	ELETRONIC("Eletronic"),
	IRISH("Irish Trad");
	
	private final String genre;
	
	MusicGenre(String genre){
		this.genre = genre;
	}

	//Returns a string array with all the values of the constants
	public static String[] getGenres() {
		MusicGenre[] genresTemp = MusicGenre.values();
		String[] genres = new String[genresTemp.length];
		for (int x = 0; x < genres.length; x++) {
			genres[x] = genresTemp[x].genre;
		}
		return genres;
	}
}
