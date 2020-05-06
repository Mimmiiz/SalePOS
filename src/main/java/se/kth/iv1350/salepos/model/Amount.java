package se.kth.iv1350.salepos.model;

/**
 * This class represents an amount and contains methods for calculating with amounts.
 */
public class Amount {
    private double amount;
    
    /**
     * Creates a new instance.
     * 
     * @param amount The amount represented by the newly created amount intance.
     */
    public Amount(double amount) {
        this.amount = amount;
    }
    
    /**
     * Creates a new instance of an already existing <code>Amount</code>.
     * 
     * @param newAmount The <code>Amount</code> to create a copy of.
     */
    public Amount(Amount newAmount){
        this.amount = newAmount.amount;
    }
    
      /**
     * Creates a new instance. Represents an amount that is 0.
     */
    public Amount() {
        this.amount = 0;
    }
    
    /**
     * Subtracts the specified <code>Amount</code> from this object and returns an <code>Amount</code>
     * instance with the result. 
     * 
     * @param otherAmount The <code>Amount</code> to subtract.
     * @return The result of the subtraction.
     */
    public Amount subtract(Amount otherAmount) {
        return new Amount (amount - otherAmount.amount);
    }
    
     /**
     * Adds the specified <code>Amount</code> from this object and returns an <code>Amount</code>
     * instance with the result. 
     * 
     * @param otherAmount The <code>Amount</code> to add.
     * @return The result of the addition.
     */
    public Amount add(Amount otherAmount) {
        return new Amount (amount + otherAmount.amount);
    }
    
    /**
     * Get the value of amount.
     * 
     * @return The value of amount.
     */
    public double getAmount() {
        return amount;
    }
}
