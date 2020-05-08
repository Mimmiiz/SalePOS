package se.kth.iv1350.salepos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salepos.integration.ItemDTO;
import se.kth.iv1350.salepos.integration.ItemRegistry;
import se.kth.iv1350.salepos.integration.RegistryCreator;

public class SaleTest {
    private Sale instanceToTest;
    private ItemID itemID;
    private int identifierNumber = 89991;
    private RegistryCreator regCreator;
    private ItemRegistry itemReg;
    private ItemList itemList;
    
    @BeforeEach
    public void setUp() {
        instanceToTest = new Sale();
        itemID = new ItemID(identifierNumber);
        regCreator = new RegistryCreator();
        itemReg = regCreator.getItemRegistry();
        itemList = new ItemList();
    }
    
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        itemID = null;
        regCreator = null;
        itemReg = null;
        itemList = null;
    }

    @Test
    public void testSearchForItemReturnValue() {
        CurrentSaleDTO saleInfoInstance = instanceToTest.registerItem(itemID, itemReg);
        assertTrue(saleInfoInstance instanceof CurrentSaleDTO, "System did not return the correct value.");
    }
    
    @Test
    public void testGetSaleInfo() {
        CurrentSaleDTO saleInfoInstance = instanceToTest.getSaleInfo();
        assertTrue(saleInfoInstance instanceof CurrentSaleDTO, "System did not return correct value");
    }
}

