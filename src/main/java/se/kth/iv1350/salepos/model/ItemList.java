package se.kth.iv1350.salepos.model;

import java.util.ArrayList;
import se.kth.iv1350.salepos.integration.ItemDTO;

/**
 * This class is contains a list that stores and manages bought items of the current sale. 
 */
public class ItemList {
    private ArrayList<Item> list = new ArrayList<>();
    
    /**
     * Searches the ItemList for an existing ItemID.
     * 
     * @param itemID The ItemID that is being searched for.
     * @return true if itemID matches an existing ItemID, false if there is no matching ItemID.
     */
   boolean searchExistingItemID(ItemID itemID) {
       int counter = 0;
       
       while (list.size() > counter && list.get(counter) != null) {
           if (itemID.checkItemIDMatch(itemID, list.get(counter).getItemID()))
               return true;
           counter++;
        }
        return false;
   }
   
   /**
    * Increments the quantity of an item by one. The specific item is specified by the ItemID.
    * 
    * @param itemID The ItemID of the item that shall be incremented by one.
    */
   void incrementItemQuantity (ItemID itemID) {
       int newItemQuantity;
       
       for (Item item : list) {
           if (itemID.checkItemIDMatch(itemID, item.getItemID())) {
               newItemQuantity = item.getQuantity() + 1;
               item.setQuantity(newItemQuantity);
           } 
       }
   }
   
   /** 
    * Saves a new Item to the list. 
    * 
    * @param searchedItem The ItemDTO that contains item information from the ItemRegistry.
    */
   void saveItemToList (ItemDTO searchedItem) {
       Item newItem = new Item(searchedItem);
       addNewItemToList(newItem);
   }
   
   /**
    * Gets the total price including VAT of all items in the item list.
    * 
    * @return The total price including VAT of all items.
    */
   Amount getTotalPriceWithVat () {
       int counter = 0;
       Amount totalPriceWithVat = new Amount();
       
       while (list.size() > counter && list.get(counter) != null) {
           Amount itemPrice = list.get(counter).getPrice().multiply(list.get(counter).getQuantity());
           Amount itemVat = list.get(counter).getVatRate().divideBy(new Amount(100.0)).add(new Amount(1.0));
           totalPriceWithVat = (itemPrice.multiply(itemVat).add(totalPriceWithVat));
 
           counter++;
       }
       return totalPriceWithVat;
   }
   
   /**
    * Gets the total price of all items in the item list.
    * 
    * @return The total price of all items.
    */
   Amount getTotalPrice () {
       int counter = 0;
       Amount totalPrice = new Amount();
       
       while (list.size() > counter && list.get(counter) != null) {
           totalPrice = totalPrice.add(list.get(counter).getPrice().multiply(list.get(counter).getQuantity()));
           counter++;
       }
       return totalPrice;
   }
   
   /**
    * Get a specific item from the item list by entering the item ID.
    * 
    * @param itemID The item ID is the desired item.
    * @return The specified item from the list.
    */
   Item getSpecifiedItemFromList(ItemID itemID) {
        Item itemFromList = null;
        int counter = 0;
        while (list.size() > counter && list.get(counter) != null) {
            if (itemID.checkItemIDMatch(itemID, list.get(counter).getItemID()))
                itemFromList = list.get(counter);
            counter++;
        }
        return itemFromList;
    }
 
   /**
    * Get the value of list.
    * 
    * @return The value of list.
    */
   ArrayList<Item> getList() {
        return list;
    }
   
   /**
    * Adds a new Item to the ArrayList of Items.
    * 
    * @param newItem The new item that will be added to the list. 
    */
   private void addNewItemToList(Item newItem) {
       list.add(newItem);
   }
}

