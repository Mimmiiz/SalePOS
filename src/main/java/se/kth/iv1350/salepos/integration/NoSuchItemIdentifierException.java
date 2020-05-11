package se.kth.iv1350.salepos.integration;

import se.kth.iv1350.salepos.model.ItemID;

/**
 * Throw when trying to find an item with an item identifier that does not exist.
 */
public class NoSuchItemIdentifierException extends Exception {
    private ItemID itemIDThatDoesNotExist;
    
    /**
     * Creates a new instance that specifies which item identifier was not found in the item registry.
     * 
     * @param itemIDThatDoesNotExist the item identifier that could not be found.
     */
    public NoSuchItemIdentifierException (ItemID itemIDThatDoesNotExist) {
        super("No item with the specified item identifier number " + itemIDThatDoesNotExist + " was found.");
        this.itemIDThatDoesNotExist = itemIDThatDoesNotExist;
    }
    
    /**
     * @return The item identifier that does not exist.
     */
    public ItemID getItemIDThatDoesNotExist() {
        return itemIDThatDoesNotExist;
    }
    
}
