package se.kth.iv1350.salepos.integration;

import java.util.List;
import se.kth.iv1350.salepos.integration.discount.DiscountFactory;
import se.kth.iv1350.salepos.integration.discount.Discounter;
import se.kth.iv1350.salepos.model.Amount;
import se.kth.iv1350.salepos.model.CustomerID;
import se.kth.iv1350.salepos.model.Item;
import se.kth.iv1350.salepos.model.ItemID;

/**
 * Contains all calls to the external discount registry that provides discount information and calculates
 * that discount. 
 */
public class DiscountRegistry {
    
    public DiscountRegistry() {
    }

    /**
     * Finds and calculates eligible discounts for the customer with the specified customer ID.
     * 
     * @param customerID The ID of the customer that wants a discount.
     * @param items The list of items that are registeres in the current sale.
     * @param totalPrice The total price of the sale (without any discounts).
     * @return The total price if the sale with added discounts.
     * @throws NoEligibleDiscountException If the customer is not eligible for any discounts.
     */
    public Amount calculateEligibleDiscount(CustomerID customerID, List<Item> items, Amount totalPrice) throws 
            NoEligibleDiscountException {
        Amount totalPriceAfterDiscount = new Amount(totalPrice);
        DiscountFactory discountFactory = DiscountFactory.getFactory();   
        
        if (checkForBaguetteDiscount(customerID, items)) {
            Discounter discounter = discountFactory.getDiscount("Baguette Discount");
            totalPriceAfterDiscount = discounter.calculateDiscount(totalPriceAfterDiscount);
        }
        if (checkForSoapDiscount(items)) {
            Discounter discounter = discountFactory.getDiscount("Soap Discount");
            totalPriceAfterDiscount = discounter.calculateDiscount(totalPriceAfterDiscount);
        }
        if (checkForWholeSaleDiscount(customerID)) {
            Discounter discounter = discountFactory.getDiscount("Whole Sale Discount");
            totalPriceAfterDiscount = discounter.calculateDiscount(totalPriceAfterDiscount);
        }
        else
            throw new NoEligibleDiscountException(customerID);
        
        return totalPriceAfterDiscount;
    }
    
    private boolean checkForBaguetteDiscount (CustomerID customerID, List<Item> items) {
        ItemID baguetteItemID = new ItemID(10001);
        if (customerID.getCustomerIDNumber() != 730620)
            return false;
        for (Item item : items) {
            if (baguetteItemID.checkItemIDMatch(baguetteItemID, item.getItemID()) && 
                    item.getQuantity().getAmount() == 2) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkForSoapDiscount (List<Item> items) {
        ItemID soapItemID = new ItemID(70707);
        for (Item item : items) {
            if (soapItemID.checkItemIDMatch(soapItemID, item.getItemID()) 
                    && item.getQuantity().getAmount() == 2) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkForWholeSaleDiscount (CustomerID customerID) {
        return customerID.getCustomerIDNumber() == 980325;
    }        
}
