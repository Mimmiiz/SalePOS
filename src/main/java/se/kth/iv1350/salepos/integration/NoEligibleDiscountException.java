package se.kth.iv1350.salepos.integration;

import se.kth.iv1350.salepos.model.CustomerID;

/**
 * Thrown when the <code>DiscountRegistry</code> can not find an eligible discount for the customer.
 */
public class NoEligibleDiscountException extends Exception {
    private CustomerID customerID;
    
    /** 
     * Creates a new instance that specifies that the customer with the specified customer ID is not eligible
     * for any discounts.
     * 
     * @param customerID The ID of the customer.
     */
    public NoEligibleDiscountException(CustomerID customerID) {
        super("Customer with the customer ID " + customerID + " is not eligible for any discounts.");
        this.customerID = customerID;
    }
}
