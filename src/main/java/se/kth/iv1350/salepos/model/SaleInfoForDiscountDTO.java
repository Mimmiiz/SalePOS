package se.kth.iv1350.salepos.model;

import java.util.List;

/**
 * A DTO that contains all the sale information that is needed for discounts.
 * The DTO can only be created in the model layer.
 */
public class SaleInfoForDiscountDTO {
    private final List<Item> items;
    
    /**
     * Creates a new instance.
     * 
     * @param items The list of items that are registered in the sale.
     * @param totalPriceWithVat The total price of the sale. 
     */
    SaleInfoForDiscountDTO(List<Item> items) {
        this.items = items;
    }

    /**
     * @return The list of items.
     */
    public List<Item> getItems() {
        return items;
    }
}
