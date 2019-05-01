
/*
 *  @Author = Caue Meireles Duarte
 */
package searchcustomer;

import java.util.ArrayList;

import dbConnector.DbConnector;
import medias.Media;
import medias.TvBox;

public class SearchMediaModel {
	
	//Default constructor
	public SearchMediaModel() {	}

	//returns an ArrayList of Media Objects
	public Object[][] getTitlesFromDB(int x) {
		DbConnector db = new DbConnector();
		ArrayList<Media> list = db.getMediaArrayFromDB(1);
		
		Object[][] data = new Object[list.size()][2];
		for (int row = 0; row < list.size(); row++) {
			data[row][0] = list.get(row).getTitle();
			data[row][1] = true;
		}
		return data;

	}

}
