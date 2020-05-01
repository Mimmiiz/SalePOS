package se.kth.iv1350.salepos.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.salepos.integration.ExternalCreator;
import se.kth.iv1350.salepos.integration.Printer;
import se.kth.iv1350.salepos.integration.RegistryCreator;
import se.kth.iv1350.salepos.model.Amount;
import se.kth.iv1350.salepos.model.CurrentSaleDTO;
import se.kth.iv1350.salepos.model.ItemID;
import se.kth.iv1350.salepos.model.Sale;

public class ControllerTest {
    private Controller instanceToTest;
    private RegistryCreator regCreator;
    private ExternalCreator extCreator;
    private Printer printer;
    
    @BeforeEach
    public void setUp() {
        printer = new Printer();
        extCreator = new ExternalCreator();
        regCreator = new RegistryCreator();
        instanceToTest = new Controller(regCreator, extCreator, printer);
        instanceToTest.startSale();
    }
    
    @AfterEach
    public void tearDown() {
        printer = null;
        extCreator = null;
        instanceToTest = null;
        regCreator = null;
    }
    
    @Test
    public void testStartSale() {
        assertTrue(instanceToTest.getSale() instanceof Sale, "A new sale did not initiate correctly.");
    }

    @Test
    public void testSearchForItemCorrectTypeReturned() {
        int identifierNumber = 89991;
        ItemID itemID = new ItemID(identifierNumber);
        CurrentSaleDTO saleInfoInstance = instanceToTest.searchForItem(itemID);
        assertTrue(saleInfoInstance instanceof CurrentSaleDTO, "Sale information was not retreived correctly.");
    }
    
    @Test
    public void testSearchForItemCorrectItemInfoReturned() {
        int identifierNumber = 89990;
        ItemID itemID = new ItemID(identifierNumber);
        CurrentSaleDTO saleInfoInstance = instanceToTest.searchForItem(itemID);
        String expectedResult = "Banana";
        String result = saleInfoInstance.getItemName();
        assertEquals(expectedResult, result, "The expected item info was not retrieved.");
    }
    
    @Test
    public void testSearchForItemCorrectRunningTotalReturned() {
        int identifierNumber = 60606;
        ItemID itemID = new ItemID(identifierNumber);
        CurrentSaleDTO saleInfoInstance = instanceToTest.searchForItem(itemID);
        double expectedResult = 8.96;
        double result = saleInfoInstance.getTotalPriceWithVat();
        double marginOfError = 0.01;
        assertEquals(expectedResult, result, marginOfError, "The expected running total (incl. VAT) was not retrieved.)");
    }
    
    @Test
    public void testEndSaleCorrectTypeReturned() {
        CurrentSaleDTO saleInfoInstance = instanceToTest.endSale();
        assertTrue(saleInfoInstance instanceof CurrentSaleDTO, "Sale information was not retreived correctly.");
    }
    
    @Test
    public void testEndSaleCorrectTotalPriceReturned() {
        CurrentSaleDTO saleInfoInstance = instanceToTest.endSale();
        double expectedResult = 0.0;
        double result = saleInfoInstance.getTotalPriceWithVat();
        assertEquals(expectedResult, result, "The correct total price was not retreived.");
    }
    
    @Test
    public void testPayReturnsCorrectTypeReturned() {
        Amount paidAmountInstance = new Amount(50);
        Amount result = instanceToTest.pay(paidAmountInstance);
        assertTrue(result instanceof Amount, "The change amount was not retreived correcly. Not the right type.");
    }
    
    @Test
    public void testPayReturnsCorrectValueOfChange() {
        int identifierNumber = 89991;
        double paidAmountValue = 20.0;
        ItemID itemID = new ItemID(identifierNumber);
        instanceToTest.searchForItem(itemID);
        instanceToTest.endSale();
        Amount paidAmountInstance = new Amount(paidAmountValue);
        Amount expectedResult = new Amount(14.7);
        Amount result = instanceToTest.pay(paidAmountInstance);
        double marginOfError = 0.01;
        assertEquals(expectedResult.getAmount(), result.getAmount(), marginOfError, "Not the expected value of the returned amount." );
    }
}