
/*
 *  @Author = Caue Meireles Duarte
 */
package customer;


//Membership class, used to manage information on a customer's card number, membership type and fidelity feature
public class Membership {
	
	private int loyaltyPts;
	private Double cardNum;
	private int membershipType;
	
	public Membership(int pointNum, Double cardNum, int membershipType) {
		this.loyaltyPts = pointNum;
		this.cardNum = cardNum;
		this.membershipType = membershipType;		
	}
	
	public Double getCardNum() {
		return this.cardNum;
	}
	
	public int getPointNum() {
		return this.loyaltyPts;
	}

	public int getmembershipType() {
		return this.membershipType;
	}

	/**
	 *Adds 10 points to the total of loyalty points in the card.
	 *
	 *@see Customer
	 */
	public void addTenPoints() {
		this.loyaltyPts += 10;
		
	}

	/**
	 *Used when the customer opts for the free rental, subtracts 100 points from the total of 
	 *loyalty points in the card.
	 *
	 *@see Customer
	 */
	public void subtractHundredPoints() {
		this.loyaltyPts -= 100;
	}
}
