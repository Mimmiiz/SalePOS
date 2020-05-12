package se.kth.iv1350.salepos.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salepos.integration.ItemRegistry;
import se.kth.iv1350.salepos.integration.ItemRegistryException;
import se.kth.iv1350.salepos.integration.NoSuchItemIdentifierException;
import se.kth.iv1350.salepos.integration.Printer;
import se.kth.iv1350.salepos.integration.RegistryCreator;

public class SaleTest {
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;
    private Sale instanceToTest;
    private RegistryCreator regCreator;
    private ItemRegistry itemReg;
    private ItemList itemList;
    
    @BeforeEach
    public void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        instanceToTest = new Sale();
        regCreator = new RegistryCreator();
        itemReg = regCreator.getItemRegistry();
        itemList = new ItemList();
    }
    
    @AfterEach
    public void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        instanceToTest = null;
        regCreator = null;
        itemReg = null;
        itemList = null;
    }

    @Test
    public void testRegisterItemReturnValue() {
        int identifierNumber = 89991;
        ItemID itemID = new ItemID(identifierNumber);
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
            assertTrue(itemID.checkItemIDMatch(itemID, e.getItemIDThatDoesNotExist()), " Wrong item identifier"
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
    
    @Test
    public void testGetCompletedSaleInfo() {
        CompletedSaleDTO completedSaleInfo = instanceToTest.getCompletedSaleInfo();
        assertTrue(completedSaleInfo instanceof CompletedSaleDTO, "The completed sale info was not "
                + "created correctly.");
    }
    
    @Test
    public void testPrintReceipt() {
        int itemIdentifier = 89991;
        ItemID itemID = new ItemID(itemIdentifier);
        try {
            instanceToTest.registerItem(itemID, itemReg);
        } catch (NoSuchItemIdentifierException | ItemRegistryException e) {
            fail("Got exception.");
            e.printStackTrace();
        }
        instanceToTest.printReceipt(new Printer());
        String result = outContent.toString();
        LocalDateTime saleTime = LocalDateTime.now();
        String expectedResult = ("\n   ITEM:\tUNIT PRICE:\tTOTAL:\n");
        assertTrue(result.contains(expectedResult), "Wrong printout.");
        assertTrue(result.contains("Apple"), "Wrong printout.");
        assertTrue(result.contains(Integer.toString(saleTime.getYear())), "Wrong sale year.");
        assertTrue(result.contains(Integer.toString(saleTime.getDayOfMonth())), "Wrong sale day.");
        assertTrue(result.contains(Integer.toString(saleTime.getMonthValue())), "Wrong sale month.");
        assertTrue(result.contains(Integer.toString(saleTime.getHour())), "Wrong sale hour.");
        assertTrue(result.contains(Integer.toString(saleTime.getMinute())), "Wrong sale minute.");
    }
}

