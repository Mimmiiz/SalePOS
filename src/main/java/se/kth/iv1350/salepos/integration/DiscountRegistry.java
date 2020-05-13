package se.kth.iv1350.salepos.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.salepos.integration.discount.DiscountFactory;
import se.kth.iv1350.salepos.integration.discount.Discounter;
import se.kth.iv1350.salepos.model.CustomerID;
import se.kth.iv1350.salepos.model.Item;
import se.kth.iv1350.salepos.model.ItemID;
import se.kth.iv1350.salepos.model.SaleInfoForDiscountDTO;

/**
 * Contains all calls to the external discount registry that provides discount information.
 * The discounts a customer is eligible for is based on bought item, numer of items bought,
 * total cost of the entier sale, and customer ID. A customer might be eligible for more than one discount.
 * Both available discounts and how to combine them vary over time.
 */
public class DiscountRegistry {
    
    public DiscountRegistry() {
    }

    /**
     * Finds and calculates eligible discounts for the customer with the specified customer ID.
     * 
     * @param customerID The ID of the customer that wants a discount.
     * @param saleInfo A DTO that contains all the sale info that is needed for discounts.
     * @return The total price if the sale with added discounts.
     */
    public List<Discounter> getEligibleDiscount(CustomerID customerID, SaleInfoForDiscountDTO saleInfo) {
        DiscountFactory discountFactory = DiscountFactory.getFactory();
        List <Discounter> discounts = new ArrayList<>();
        
        if (checkForBaguetteDiscount(customerID, saleInfo.getItems())) {
            discounts.add(discountFactory.getDiscount("Baguette Discount"));
        }
        if (checkForSoapDiscount(saleInfo.getItems())) {
            discounts.add(discountFactory.getDiscount("Soap Discount"));
        }
        if (checkForWholeSaleDiscount(customerID)) {
            discounts.add(discountFactory.getDiscount("Whole Sale Discount"));
        }
        return discounts;
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
