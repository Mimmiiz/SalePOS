package se.kth.iv1350.salepos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import se.kth.iv1350.salepos.integration.ItemDTO;
import se.kth.iv1350.salepos.integration.ItemRegistry;
import se.kth.iv1350.salepos.integration.RegistryCreator;

public class ItemListTest {
    ItemList instanceToTest;
    Sale sale;
    RegistryCreator regCreator;
    ItemRegistry itemRegistry;
    
    @BeforeEach
    public void setUp() {
        instanceToTest = new ItemList();
        sale = new Sale();
        regCreator = new RegistryCreator(); 
        itemRegistry = regCreator.getItemRegistry();
        
        instanceToTest.saveItemToList(new ItemDTO("Apple", 5, 10, new ItemID(89991)));
        instanceToTest.saveItemToList(new ItemDTO("Banana", 3, 12, new ItemID(89990)));
        instanceToTest.saveItemToList(new ItemDTO("Bread", 15, 25, new ItemID(10001)));
    }
    
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        sale = null;
        regCreator = null;
        itemRegistry = null;
    }
    
    @Disabled
    public void testSearchExistingItemIDNoItemsInList() {
        ItemID itemIDInstance = new ItemID(89991);
        boolean result = instanceToTest.searchExistingItemID(itemIDInstance);
        assertFalse(result, "The result is not the expected result.");
    }

    @Test
    public void testSearchExistingItemIDReturnTrue() {
        ItemID itemIDInstance = new ItemID(89991);
        boolean result = instanceToTest.searchExistingItemID(itemIDInstance);
        assertTrue(result, "The result is not the expected result.");
    }
    
    @Test
    public void testSearchExistingItemIDReturnFalse() {
        ItemID itemIDInstance = new ItemID(55000);
        boolean result = instanceToTest.searchExistingItemID(itemIDInstance);
        assertFalse(result, "The result is not the expected result.");
    }

    @Test
    public void testIncrementItemQuantity() {
        ItemID itemIDInstance = new ItemID(89991);
        double expectedQuantity = 2.0;
        instanceToTest.incrementItemQuantity (itemIDInstance);
        Item itemFromList = instanceToTest.getSpecifiedItemFromList(itemIDInstance);
        double resultQuantity = itemFromList.getQuantity().getAmount();
        assertEquals(expectedQuantity, resultQuantity, "The expected result did not match the actual result.");
    }
         
    @Test
    public void testSaveItemToList() {
        ItemID itemIDInstance = new ItemID(40032);
        ItemDTO  itemDTOInstance = new ItemDTO("Juice", 12, 6, new ItemID(40032));
        instanceToTest.saveItemToList(itemDTOInstance);
        boolean expectedResult = true;
        boolean result = instanceToTest.searchExistingItemID(itemIDInstance);
        assertEquals(expectedResult, result, "The expected result did not match the actual result.");      
    }
    
      @Test
    public void testGetTotalPriceWithVat() {
        double expectedResult = 27.61;
        double marginOfError = 0.01;
        double result = instanceToTest.getTotalPriceWithVat().getAmount();
          assertEquals(expectedResult, result, marginOfError, "The expected result did not match the actual result.");
    }
    
    @Test
    public void testGetTotalPrice() {
        double expectedResult = 23.0;
        double marginOfError = 0.01;
        double result = instanceToTest.getTotalPrice().getAmount();
        assertEquals(expectedResult, result, marginOfError, "The expected result did not match the actual result.");
    }
    
    @Test
    public void testGetSpecifiedItemFromList() {
        ItemID itemIDinstance = new ItemID(89991);
        Item itemInstance = instanceToTest.getSpecifiedItemFromList(itemIDinstance);
        boolean result = itemIDinstance.checkItemIDMatch(itemIDinstance, itemInstance.getItemID());
        assertTrue(result, "The expected result did not match the actual result."); 
    }
}

