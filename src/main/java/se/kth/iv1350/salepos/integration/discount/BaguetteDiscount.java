package se.kth.iv1350.salepos.integration.discount;

import se.kth.iv1350.salepos.integration.ItemDTO;
import se.kth.iv1350.salepos.model.Amount;
import se.kth.iv1350.salepos.model.ItemID;

/**
 * This class represents a discount for baguettes. If customer has the correct ID and buys
 * two baguettes they are eligible for this disount. The baguettes will be discounted by 20%.
 */
class BaguetteDiscount implements Discounter {
    private final ItemDTO baguette = new ItemDTO("Baguette", new Amount(15), new Amount(1.25), new ItemID(10001));
    private final Amount numberOfBaguettes = new Amount(2);
    private final Amount discountAmount = new Amount(0.2);

    /**
     * Calculates the total price with discounts.
     * 
     * @param totalPrice The total price of the sale that will be discounted.
     * @return The total price of the sale with discount added.
     */
    @Override
    public Amount calculateDiscount(Amount totalPrice) {
        Amount baguettePriceWithVat = baguette.getPrice().multiply(numberOfBaguettes).multiply(baguette.getVatRate());
        Amount baguetteDiscount = baguettePriceWithVat.multiply(discountAmount);
        Amount totalPriceWithDiscount = totalPrice.subtract(baguetteDiscount);
        return totalPriceWithDiscount;
    }
}
