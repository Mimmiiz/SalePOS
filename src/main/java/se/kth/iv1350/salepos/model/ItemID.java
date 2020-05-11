package se.kth.iv1350.salepos.model;

/**
 * This class represents an item identifier.  Each item has a unique item ID.
 */
public class ItemID {
    int identifierNumber;
    
    /**
     * Creates a new instance.
     * 
     * @param identifierNumber The identifier number of a specific item.
     */
    public ItemID(int identifierNumber) {
        this.identifierNumber = identifierNumber;
    }
    
    /**
     * Creates a new instance of an already existing <code>ItemID</code>.
     * 
     * @param itemID The <code>ItemID</code> to create a copy of.
     */
    public ItemID(ItemID itemID) {
        this.identifierNumber = itemID.identifierNumber;
    }
    
    @Override
    public String toString() {
        return "" + identifierNumber;
    }

    /**
     * Get the value of identifierNumber.
     * 
     * @return The value of identifierNumber.
     */
    int getIdentifierNumber() {
        return identifierNumber;
    }
    
    /**
    * Check if two ItemIDs are matching.
    * 
    * @param itemID One of the ItemIDs to compare.
    * @param otherItemID One of the ItemIDs to compare.
    * @return true if the ItemIDs match, false if they do not match.
    */
   public boolean checkItemIDMatch (ItemID itemID, ItemID otherItemID) {
        return itemID.identifierNumber == otherItemID.identifierNumber;
   }
}
