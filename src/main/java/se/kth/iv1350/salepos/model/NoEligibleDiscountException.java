package se.kth.iv1350.salepos.model;

import se.kth.iv1350.salepos.model.CustomerID;

/**
 * Thrown when the <code>DiscountRegistry</code> can not find an eligible discount for the customer.
 */
public class NoEligibleDiscountException extends Exception {
    private CustomerID customerID;
    
    /** 
     * Creates a new instance that specifies that the customer with the specified customer ID is not eligible
     * for any discounts.
     */
    public NoEligibleDiscountException() {
        super("Customer is not eligible for any discounts.");
    }
}
