
/*
 *  @Author = Caue Meireles Duarte
 */
package frontPage;

import customer.Customer;
import dbConnector.DbConnector;

public class FrontPageModel {
	
	public FrontPageModel() {
		
	}
	
	/**
	 *Gets customer information from the DbConnector in the customers table that has the same email value
	 * as the argument and returns a Customer object. The object is null if there is no customer entry for 
	 * the email.
	 *
	 * @param email - the email of the customer.
	 * @return A Customer object if the customer exists in the database or null if it doesn't.
	 * @see Customer, DbConnector
	 */
	public Customer getCustomerInfo(String email) {
		DbConnector db = new DbConnector();
		return db.getCustomerInfo(email);
		
	}

}
