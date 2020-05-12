package se.kth.iv1350.salepos.integration;

import java.util.ArrayList;
import se.kth.iv1350.salepos.model.Amount;
import se.kth.iv1350.salepos.model.ItemID;

/**
 * Contains all calls to external the item registry that provides store item information.
 */
public class ItemRegistry {
    private ArrayList<ItemDTO> itemDTOs = new ArrayList<>();

    /**
     * Creates a new instance.
     */
    ItemRegistry() {
        addItems();
    }
    
    /**
     * Finds the item with the specified ItemID in the item registry.
     * 
     * @param itemID the specified ItemID.
     * @return An ItemDTO with all the item information.
     * @throws ItemRegistryException If an item with the specified ItemID does not exist.
     * @throws ItemRegistryException If the database call failed.
     */
    public ItemDTO searchForItem(ItemID itemID) throws NoSuchItemIdentifierException {
        ItemID errorItemID = new ItemID(88888);
        
        if (itemID.checkItemIDMatch(itemID, errorItemID)) {
            throw new ItemRegistryException("Could not connect to the database.");
        }
        for (ItemDTO itemDTO : itemDTOs) {
            if (itemID.checkItemIDMatch(itemDTO.getItemID(), itemID)) {
                return new ItemDTO(itemDTO.getName(), itemDTO.getPrice(), itemDTO.getVatRate(), 
                        itemDTO.getItemID());  
            }
        }
        throw new NoSuchItemIdentifierException (itemID);
    }   
    
    /**
     * Adds ItemDTOs to a list to resemble and actual item registry. Enables execution of the program and
     * easier testing.
     */
    private void addItems() {
        itemDTOs.add(new ItemDTO("Apple", new Amount(5), new Amount(6), new ItemID(89991)));
        itemDTOs.add(new ItemDTO("Banana", new Amount(3), new Amount(12), new ItemID(89990)));
        itemDTOs.add(new ItemDTO("Baguette", new Amount(15), new Amount(25), new ItemID(10001)));
        itemDTOs.add(new ItemDTO("Milk", new Amount(8), new Amount(12), new ItemID(60606)));
        itemDTOs.add(new ItemDTO("Soap", new Amount(25), new Amount(25), new ItemID(70707)));
    }
}
