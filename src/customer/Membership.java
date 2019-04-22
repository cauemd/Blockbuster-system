
/*
 *  @Author = Caue Meireles Duarte
 */
package customer;


//Membership class, used to manage information on a customer's card number, membership type and fidelity feature
public class Membership {
	
	private int loyaltyPts;
	private int cardNum;
	private int membershipType;
	
	public Membership(int pointNum, int cardNum, int membershipType) {
		this.loyaltyPts = pointNum;
		this.cardNum = cardNum;
		this.membershipType = membershipType;		
	}
	
	//method to deduct the 100 points in case the customer wants to get the free rental
	public void freeRental() {
		this.loyaltyPts -= 100;
	}
	
	public int getPointNum() {
		return this.loyaltyPts;
	}

	public int getmembershipType() {
		return this.membershipType;
	}
}
