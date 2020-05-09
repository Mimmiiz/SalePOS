package se.kth.iv1350.salepos.integration;

import se.kth.iv1350.salepos.model.Amount;
import se.kth.iv1350.salepos.model.ItemID;

/**
 * This class is an item DTO that transfers item information from the integration layer to the model.
 */
public final class ItemDTO {
    private final String name;
    private final Amount price;
    private final Amount vatRate;
    private final ItemID itemID;
    
    /**
     * Creates a new instance.
     * 
     * @param name The specified item name.
     * @param price The specified item price.
     * @param vatRate The specified item vatRate.
     * @param itemID The specified ItemID.
     */
    public ItemDTO(String name, Amount price, Amount vatRate, ItemID itemID) {
        this.name = name;
        this.price = new Amount(price);
        this.vatRate = new Amount(vatRate);
        this.itemID = itemID;
    }

    /**
     * Get the value of name.
     * 
     * @return The value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the value of price.
     * 
     * @return The value of price.
     */
    public Amount getPrice() {
        return new Amount(price);
    }

    /**
     * Get the value of vatRate.
     * 
     * @return The value of vatRate.
     */
    public Amount getVatRate() {
        return new Amount(vatRate);
    }
    
    /**
     * Get the value of itemID
     * 
     * @return The value of itemID.
     */
    public ItemID getItemID() {
        return new ItemID(itemID);
    }
}
