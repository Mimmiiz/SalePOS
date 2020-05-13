package se.kth.iv1350.salepos.integration.discount;

import se.kth.iv1350.salepos.model.Amount;

/**
 * An interface that defines the ability to calculate discounts for the sale. 
 * The interface shall be implemented by classes that calculates discounts.
 */
public interface Discounter {
    
    /**
     * Calculates the discount of a sale.
     * 
     * @param totalPrice The total price of the sale (without any discounts).
     * @return The total price of the sale with the added discounts.
     */
    Amount calculateDiscount(Amount totalPrice);
}
