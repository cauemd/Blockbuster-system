
/*
 *  @Author = Caue Meireles Duarte
 */
package customer;

import java.util.ArrayList;

import medias.Media;

public class Customer {
	
	private String name;
	private String mobile;
	private ArrayList<Media> rentedItens;
	private Membership memberCard;
	
	public Customer(String fName, String lName, String mobile, Membership memberCard) {
		this.name = fName + " " + lName;
		this.mobile = mobile;
		this.memberCard = memberCard;
	}
	
	

}
