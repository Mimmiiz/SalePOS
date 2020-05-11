package se.kth.iv1350.salepos.model;

/**
 * Throw when trying to find an item with an item identifier that does not exist.
 */
public class NoSuchItemIdentifierException extends Exception {
    private ItemID itemIDThatDoesNotExist;
    
    public NoSuchItemIdentifierException (ItemID itemIDThatDoesNotExist) {
        super("No item with the specified item identifier number " + itemIDThatDoesNotExist + " was found.");
        this.itemIDThatDoesNotExist = itemIDThatDoesNotExist;
    }
    
}
