package se.kth.iv1350.salepos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salepos.model.ItemID;

public class ItemRegistryTest {
    private ItemRegistry instanceToTest;
    
    public ItemRegistryTest() {
    }
    
    @BeforeEach
    public void setUp() {
        instanceToTest = new ItemRegistry();
    }
    
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
    }

    @Test
    public void testSearchForItemReturnCorrectValue() {
        ItemID itemIDinstance = new ItemID(89991);
        try {
            ItemDTO itemDTOinstance = instanceToTest.searchForItem(itemIDinstance);
            assertTrue(itemDTOinstance instanceof ItemDTO, "The ItemDTO was not created correcly.");
        } catch (NoSuchItemIdentifierException | ItemRegistryException e) {
            fail("Got exception.");
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSearchForItemCorrectItemDTO() {
        ItemID itemIDinstance = new ItemID(60606);
        try {
            ItemDTO itemDTOinstance = instanceToTest.searchForItem(itemIDinstance);
            double expectedPrice = 8;
            double marginOfError = 0.01;
            assertEquals(expectedPrice, itemDTOinstance.getPrice().getAmount(), marginOfError, "The price did not get the expected value.");
        } catch (NoSuchItemIdentifierException | ItemRegistryException e) {
            fail("Got exception.");
            e.printStackTrace();
        }
    } 
    
    @Test
    public void testSearchForItemThatDoesNotExistInRegistry() {
        ItemID itemIDinstance = new ItemID(55555);
        try {
            instanceToTest.searchForItem(itemIDinstance);
            fail("Found an item with an item identifier that does not exist.");
        } catch (NoSuchItemIdentifierException e) {
            assertTrue(e.getMessage().contains("" + itemIDinstance), "Wrong exception message,"
                    + " does not contain the specified item identifier: " + e.getMessage());
            assertTrue(itemIDinstance.checkItemIDMatch(e.getItemIDThatDoesNotExist(), 
                    itemIDinstance), " Wrong item identifier"+ "is specified: " + e.getItemIDThatDoesNotExist());
        } catch (ItemRegistryException e) {
            fail("Got exception");
            e.printStackTrace();
        }     
    }
    
    @Test
    public void testSearchForItemThatGivesRegistryError() {
        ItemID itemID = new ItemID(88888);
        try {
            instanceToTest.searchForItem(itemID);
            fail("Could search for item identifier.");
        } catch (NoSuchItemIdentifierException e) {
            fail("Got exception.");
            e.printStackTrace();
        } catch (ItemRegistryException e) {
            assertTrue(e.getMessage().contains("connect to the database"), "Exception did not contain the correct "
                    + "error message: " + e.getMessage());
        }
    }
}
