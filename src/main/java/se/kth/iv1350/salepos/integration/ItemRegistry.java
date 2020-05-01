package se.kth.iv1350.salepos.integration;

import java.util.ArrayList;
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
     */
    public ItemDTO findItem(ItemID itemID) {
        ItemDTO searchedItem = null;
        int counter = 0;
        
        while (itemDTOs.size() > counter && itemDTOs.get(counter) != null) {
            if (itemID.checkItemIDMatch(itemID, itemDTOs.get(counter).getItemID()))
                searchedItem = itemDTOs.get(counter);
            counter++;
        }
        return searchedItem;
    }   
    
    /**
     * Adds ItemDTOs to a list to resemble and actual item registry. Enables execution of the program and
     * easier testing.
     */
    private void addItems() {
        itemDTOs.add(new ItemDTO("Apple", 5, 6, new ItemID(89991)));
        itemDTOs.add(new ItemDTO("Banana", 3, 12, new ItemID(89990)));
        itemDTOs.add(new ItemDTO("Bread", 15, 25, new ItemID(10001)));
        itemDTOs.add(new ItemDTO("Milk", 8, 12, new ItemID(60606)));          
    }
}
