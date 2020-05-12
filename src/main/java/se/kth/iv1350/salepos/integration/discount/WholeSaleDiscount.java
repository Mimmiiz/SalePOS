package se.kth.iv1350.salepos.integration.discount;

import se.kth.iv1350.salepos.model.Amount;

/**
 * This class represents a discount for the whole sale. If the customer has the correct ID they are
 * eligible for this discount.
 */
class WholeSaleDiscount implements Discounter {
    private final Amount discountAmount = new Amount(0.25);

    /**
     * Calculates the the price after adding the discount.
     * 
     * @param totalPrice The total price before discount was added.
     * @return The price after total discount is added.
     */
    @Override
    public Amount calculateDiscount(Amount totalPrice) {
        Amount totalPriceWithDiscount = totalPrice.subtract(totalPrice.multiply(discountAmount));
        return totalPriceWithDiscount;
    }
}
