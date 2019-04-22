
/*
 *  @Author = Caue Meireles Duarte
 */
package createMedia;

import enuns.MusicGenre;
import enuns.VideoGenre;

//Logic part of the "add media" functionality of the system
public class CreateMediaModel {
	
	//no constructor yet
	
	//generates a String array with the values from all constants in the VideoGenre enum
	public String[] movieGenreList() {
		String[] genreList = VideoGenre.getGenres();
		return genreList;
	}

	//generates a String array with the values from all constants in the MusicGenre enum
	public String[] musicGenreList() {
		String[] genreList = MusicGenre.getGenres();
		return genreList;
	}
	

}
