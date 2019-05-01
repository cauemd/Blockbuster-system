package enuns;

//Enuns for all movies/tv box genres
public enum VideoGenre {
	
	HORROR("Horror"),
	WESTERN("Western"),
	COMEDY("Comedy"),
	DRAMA("Drama"),
	FANTASY("Fantasy"),
	ACTION("Action"),
	ROMANCE("Romance"),
	SCIFI("Science Fiction"),
	DOCUMENTARY("Documentary"),
	SUSPENSE("Suspense");
	
	private final String genre;
	
	VideoGenre(String genre) {
		this.genre = genre;
	}
	
	//return an array of string with the values of all constants
	public static String[] getGenres() {
		VideoGenre[] genresTemp = VideoGenre.values();
		String[] genres = new String[genresTemp.length];
		for (int x = 0; x < genres.length; x++) {
			genres[x] = genresTemp[x].genre;
		}
		return genres;
	}

}
