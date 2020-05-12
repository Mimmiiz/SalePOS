package se.kth.iv1350.salepos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import se.kth.iv1350.salepos.integration.ItemRegistry;
import se.kth.iv1350.salepos.integration.ItemRegistryException;
import se.kth.iv1350.salepos.integration.NoSuchItemIdentifierException;
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
    public void testRegisterItemReturnValue() {
        try {
            CurrentSaleDTO saleInfoInstance = instanceToTest.registerItem(itemID, itemReg);
            assertTrue(saleInfoInstance instanceof CurrentSaleDTO, "System did not return the correct value.");
        } catch (NoSuchItemIdentifierException | ItemRegistryException e) {
            fail("Got exception.");
            e.printStackTrace();
        }
    }
    
    @Test
    public void testRegisterItemThatDoesNotExistInRegistry() {
        int identifierNumber = 10000;
        ItemID itemID = new ItemID(identifierNumber);
        try {
            instanceToTest.registerItem(itemID, itemReg);
            fail("Item was registered even if it does not exist in item registry.");
        } catch (NoSuchItemIdentifierException e) {
            assertTrue(e.getMessage().contains("" + identifierNumber), "Wrong exception message,"
                    + " does not contain the specified item identifier: " + e.getMessage());
            assertTrue(e.getItemIDThatDoesNotExist().checkItemIDMatch(itemID, itemID), " Wrong item identifier"
                    + "is specified: " + e.getItemIDThatDoesNotExist());
        } catch (ItemRegistryException e) {
            fail("Got exception");
            e.printStackTrace();
        }
    }
    
    @Test
    public void testRegisterItemThatGivesRegistryError() {
        int identifierNumber = 88888;
        ItemID itemID = new ItemID(identifierNumber);
        try {
            instanceToTest.registerItem(itemID, itemReg);
            fail("Item was registered.");
        } catch (NoSuchItemIdentifierException e) {
            fail("Got exception");
            e.printStackTrace();
        } catch (ItemRegistryException e) {
            assertTrue(e.getMessage().contains("connect to the database"), "Exception did not contain the correct "
                    + "error message: " + e.getMessage());
        }
    }
    
    @Test
    public void testGetSaleInfo() {
        CurrentSaleDTO saleInfoInstance = instanceToTest.getSaleInfo();
        assertTrue(saleInfoInstance instanceof CurrentSaleDTO, "System did not return correct value");
    }
    
    @Test
    public void testPayCorrectAmountOfChange() {
        Amount amountToPay = new Amount(10);
        CashPayment cashPayment = new CashPayment(amountToPay);
        Amount changeInstance = instanceToTest.pay(cashPayment);
        double expectedResult = 10;
        double marginOfError = 0.01;
        assertEquals(changeInstance.getAmount(), expectedResult, marginOfError, "Did not receive the correct "
                + "amount of change.");
    }
    
    @Disabled
    public void testGetCompletedSaleInfo() {
    }
    
    @Disabled
    public void testPrintReceipt() {}
}

