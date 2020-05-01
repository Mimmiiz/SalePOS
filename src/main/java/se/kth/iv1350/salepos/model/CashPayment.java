package se.kth.iv1350.salepos.model;

/**
 * This class represents a single cash payment for one specific sale. One cash payment pays one sale.
 */
public class CashPayment {
    private Amount paidAmount;
    private Amount totalPrice;
    
    /**
     * Creates a new instance.
     * 
     * @param paidAmount The amount paid by the customer. 
     */
    public CashPayment(Amount paidAmount) {
        this.paidAmount = paidAmount;
    }
    
    /**
     * Get the value of paidAmount.
     * 
     * @return The value of paid amount.
     */
    public Amount getPaidAmount() {
        return paidAmount;
    }
    
     /**
     * Get the value of totalPrice.
     * 
     * @return The value of totalPrice.
     */
    public Amount getTotalPrice() {
        return totalPrice;
    }
    
    /**
     * Sets the total price of the sale, includes VAT.
     * 
     * @param totalPrice the total price of the sale. 
     */
    public void setTotalPrice(Amount totalPrice) {
        this.totalPrice = totalPrice;
    }
}
