
/*
 *  @Author = Caue Meireles Duarte
 */
package createCustomer;

import customer.Customer;
import customer.Membership;
import dbConnector.DbConnector;

public class CreateCustomerModel {
	
	private CreateCustomerController controller;

	public CreateCustomerModel(CreateCustomerController controller) {
		this.controller = controller;
	}

	/**
	 *Verifies if all textFields from the view were completed correctly and calls method
	 *to insert customer in the database, returning true if successful.
	 *If there any field is not valid, calls views method to change the error lbl and returns false.
	 *
	 * @param customer's first name
	 * @param customer's last name
	 * @param customer's mobile number
	 * @param customer's membership type(1 for MovieLover, 2 for MusicLover, 3 for TVLover and 4 for Premium)
	 * @param customer's card number
	 * @param customer's email
	 * @return true if the data is valid, false otherwise.
	 * @see Customer, CreateCustomerView
	 */
	public boolean verifyCustomer(String fName, String lName, String mobile, 
			int membership, String card, String email) {
		
		//verifying if all text fields are filled
		if (fName == null || lName == null || mobile == null || 
				membership == 0 || card == null || email == null) {
			controller.errorMsg(1);
			return false;
			
		//verifying	if the card text field contains only numbers
		} else if (!card.matches("[0-9]+")){
			controller.errorMsg(2);
			return false;
			
		//verifying if the email is valid	
		} else if (!email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")){
			controller.errorMsg(3);
			return false;
		
		//Inserting customer in the database
		} else {
		Membership memberCard = new Membership(0, Double.parseDouble(card), membership);
		Customer cust = new Customer(0, fName, lName, mobile, email, memberCard, null);
		DbConnector db = new DbConnector();
		if (!db.insertCustomerInDB(cust, memberCard)) {
			controller.errorMsg(4);
			return false;
		}
				
		return true;
		
		}
	}
	

}
