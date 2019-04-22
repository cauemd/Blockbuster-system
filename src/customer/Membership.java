
/*
 *  @Author = Caue Meireles Duarte
 */
package customer;


//Membership class, used to manage information on a customer's card number, membership type and fidelity feature
public class Membership {
	
	private int custNum;
	private int pointNum;
	private int cardNum;
	private String membershipType;
	
	public Membership(int customer, int pointNum, int cardNum, String membershipType) {
		this.custNum = customer;
		this.pointNum = pointNum;
		this.cardNum = cardNum;
		this.membershipType = membershipType;		
	}
	
	//method to deduct the 100 points in case the customer wants to get the free rental
	public void freeRental() {
		this.pointNum -= 100;
	}
	
	public int getPointNum() {
		return this.pointNum;
	}

	public String getmembershipType() {
		return this.membershipType;
	}
}
