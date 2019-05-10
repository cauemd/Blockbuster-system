
/*
 *  @Author = Caue Meireles Duarte
 */
package customer;

import java.util.ArrayList;
import medias.Media;

public class Customer {

	private int custID;
	private String fName;
	private String lName;
	private String mobile;
	private String email;
	private ArrayList<Media> rentedItens;
	private Membership memberCard;

	//Customer Class constructor
	public Customer(int custID, String fName, String lName, String mobile, String email, Membership memberCard, 
			ArrayList<Media> mediaList) {
		this.custID = custID;
		this.fName = fName;
		this.lName = lName;
		this.mobile = mobile;
		this.email = email;
		this.memberCard = memberCard;
		this.rentedItens = mediaList;
	}

	/**
	 *Return customer first name.
	 */
	public String getfName() {
		return fName;
	}
	
	/**
	 *Return customer's last name.
	 */
	public String getlName() {
		return lName;
	}

	/**
	 *Return customer's mobile number.
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 *Return customer's email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 *Returns an ArrayList with all the medias the customer is currently renting.
	 *
	 *@see Media
	 */
	public ArrayList<Media> getRentedItems() {
		return rentedItens;
	}

	/**
	 *Return number of loyalty points in the membership card.
	 *
	 *@see Membership
	 */
	public int getPoints() {
		return memberCard.getPointNum();
	}
	
	/**
	 *Return membership card type. 1 - MovieLover, 2 - MusicLover, 3 - TvLover, 4 - Premium
	 *
	 *@see Membership
	 */
	public int getMembershipType() {
		return memberCard.getmembershipType();
	}
	
	/**
	 *Return customer ID.
	 */
	public int getID() {
		return custID;
	}

	/**
	 *Calls method that adds 10 loyalty points to the membership card
	 *
	 *@see Membership
	 */
	public void addPoints() {
		this.memberCard.addTenPoints();
		
	}

	/**
	 *Calls method that subtracts 100 loyalty points to the membership card
	 *
	 *@see Membership
	 */
	public void removePoints() {
		this.memberCard.subtractHundredPoints();
		
	}
}
