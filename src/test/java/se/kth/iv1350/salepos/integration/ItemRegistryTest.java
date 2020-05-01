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
    public void testFindItemReturnCorrectValue() {
        ItemID itemIDinstance = new ItemID(89991);
        ItemDTO itemDTOinstance = instanceToTest.findItem(itemIDinstance);
        assertTrue(itemDTOinstance instanceof ItemDTO, "The ItemDTO was not created correcly.");
    }
    
    @Test
    public void testFindItemCorrectItemDTO() {
        ItemID itemIDinstance = new ItemID(60606);
        ItemDTO itemDTOinstance = instanceToTest.findItem(itemIDinstance);
        int expectedPrice = 8;
        assertEquals(expectedPrice, itemDTOinstance.getPrice(), "The price did not get the expected value.");  
    }  
}
