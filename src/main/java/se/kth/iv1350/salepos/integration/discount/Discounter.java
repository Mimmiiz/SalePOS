package se.kth.iv1350.salepos.integration.discount;

import se.kth.iv1350.salepos.model.Amount;

/**
 *
 * @author Maria
 */
public interface Discounter {
    
    Amount calculateDiscount(Amount totalPrice);
}
