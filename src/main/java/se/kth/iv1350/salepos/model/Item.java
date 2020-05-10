package se.kth.iv1350.salepos.model;

import se.kth.iv1350.salepos.integration.ItemDTO;

/**
 * This class represents an item that is being bought by a customer in the current sale.
 */
public class Item {
    private ItemID itemID;
    private String name;
    private Amount price;
    private Amount vatRate;
    private Amount quantity;

    /**
     * Creates a new instance of an Item using an ItemDTO.
     * 
     * @param itemDTO The ItemDTO that contains specific item information from the item registry.
     */
    Item(ItemDTO itemDTO) {
        name = itemDTO.getName();
        itemID = new ItemID(itemDTO.getItemID().getIdentifierNumber());
        price = new Amount(itemDTO.getPrice());
        vatRate = new Amount(itemDTO.getVatRate());
        quantity = new Amount(1);
    }
    
    /**
     * Creates a new instance of an Item that already exists, a copy of an Item.
     * 
     * @param item The Item that is copied.
     */
    Item(Item item) {
        this.itemID = new ItemID(item.itemID);
        this.name = item.name;
        this.price = new Amount(item.price);
        this.vatRate = new Amount(item.vatRate);
        this.quantity = new Amount(item.quantity);
    }
    
    /**
     * Get the value of itemID.
     * 
     * @return The itemID of the specified Item.
     */
    ItemID getItemID() {
        return new ItemID(this.itemID);
    }
    
    /**
     * Get the value of vatRate
     * 
     * @return The value of vatRate.
     */
    Amount getVatRate() {
        return new Amount(this.vatRate);
    }
    
    /**
     * Get the value of price.
     * 
     * @return The value of price.
     */
    Amount getPrice() {
        return new Amount(this.price);
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
    Amount getQuantity() {
        return new Amount(this.quantity);
    }
    
    /**
     * Set the value of quantity.
     * 
     * @param quantity The new quantity of the item. 
     */
    void setQuantity(Amount quantity) {
        this.quantity = new Amount(quantity); 
    }
}
