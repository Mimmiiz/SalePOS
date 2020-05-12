package se.kth.iv1350.salepos.model;

/**
 * A listener interface for receiving notifications about sale payments.
 */
public interface SaleObserver {
    
    /**
     * Invoked when a sale has been paid.
     * 
     * @param totalPrice The total amount to add to the total revenue.
     */
    void updateTotalRevenue(Amount totalPrice);    
}
