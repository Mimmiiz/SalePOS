package se.kth.iv1350.salepos.integration;

import se.kth.iv1350.salepos.model.ItemID;

/**
 * This class is an item DTO that transfers item information from the integration layer to the model.
 */
public final class ItemDTO {
    private String name;
    private int price;
    private int vatRate;
    private ItemID itemID;
    
    /**
     * Creates a new instance.
     * 
     * @param name The specified item name.
     * @param price The specified item price.
     * @param vatRate The specified item vatRate.
     * @param itemID The specified ItemID.
     */
    public ItemDTO(String name, int price, int vatRate, ItemID itemID) {
        this.name = name;
        this.price = price;
        this.vatRate = vatRate;
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
    public int getPrice() {
        return price;
    }

    /**
     * Get the value of vatRate.
     * 
     * @return The value of vatRate.
     */
    public int getVatRate() {
        return vatRate;
    }
    
    /**
     * Get the value of itemID
     * 
     * @return The value of itemID.
     */
    public ItemID getItemID() {
        return itemID;
    }
}
