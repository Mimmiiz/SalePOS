package se.kth.iv1350.salepos.integration.discount;

import se.kth.iv1350.salepos.integration.ItemDTO;
import se.kth.iv1350.salepos.integration.discount.Discounter;
import se.kth.iv1350.salepos.model.Amount;
import se.kth.iv1350.salepos.model.CustomerID;
import se.kth.iv1350.salepos.model.ItemID;

/**
 * This class represents a discount for soap. If the customer buys 2 bottles of soap
 * they will get 1 soap for free.
 */
class SoapDiscount implements Discounter {
    private final ItemDTO soap = new ItemDTO("Soap", new Amount(25), new Amount(1.25), new ItemID(70707));

    /**
     * Calculates the new price with the added discount.
     * 
     * @param totalPrice The total price of the sale.
     * @return The total price of the sale with the added discount.
     */
    @Override
    public Amount calculateDiscount(Amount totalPrice) {
        Amount totalPriceWithDiscount = totalPrice.subtract(soap.getPrice().multiply(soap.getVatRate()));
        return totalPriceWithDiscount;
    } 
}
