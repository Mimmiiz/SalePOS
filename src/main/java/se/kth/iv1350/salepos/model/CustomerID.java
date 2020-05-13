package se.kth.iv1350.salepos.model;

/**
 * This class represents a customer ID.
 */
public class CustomerID {
    private int customerIDNumber;

    /**
     * Creates a new instance.
     * 
     * @param customerIDNumber The specified customer identification number.
     */
    public CustomerID(int customerIDNumber) {
        this.customerIDNumber = customerIDNumber;
    }
    
    public String toString() {
        return "" + customerIDNumber;
    }
    
    /**
     * @return The customer ID number.
     */
    public int getCustomerIDNumber() {
        return customerIDNumber;
    }   
}