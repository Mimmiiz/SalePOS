package se.kth.iv1350.salepos.model;

import se.kth.iv1350.salepos.integration.ItemDTO;

/**
 * This class represents an item that is being bought by a customer in the current sale.
 */
public class Item {
    private ItemID itemID;
    private String name;
    private int price;
    private int vatRate;
    private int quantity;

    /**
     * Creates a new instance of an Item using an ItemDTO.
     * 
     * @param itemDTO The ItemDTO that contains specific item information from the item registry.
     */
    Item(ItemDTO itemDTO) {
        name = itemDTO.getName();
        itemID = new ItemID(itemDTO.getItemID().getIdentifierNumber());
        price = itemDTO.getPrice();
        vatRate = itemDTO.getVatRate();
        quantity = 1;
    }
    
    /**
     * Get the value of itemID.
     * 
     * @return The itemID of the specified Item.
     */
    ItemID getItemID() {
        return itemID;
    }
    
    /**
     * Get the value of vatRate
     * 
     * @return The value of vatRate.
     */
    int getVatRate() {
        return vatRate;
    }
    
    /**
     * Get the value of price.
     * 
     * @return The value of price.
     */
    int getPrice() {
        return price;
    }
    
    /**
     * Get the value of name.
     * 
     * @return the value of name.
     */
    String getName() {
        return name;
    }
    
    /**
     * Get the value of quantity.
     * 
     * @return The value of quantity.
     */
    int getQuantity() {
        return quantity;
    }
    
    /**
     * Set the value of quantity.
     * 
     * @param quantity The new quantity of the item. 
     */
    void setQuantity(int quantity) {
        this.quantity = quantity; 
    }
}
