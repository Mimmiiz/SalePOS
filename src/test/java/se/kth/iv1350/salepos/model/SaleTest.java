package se.kth.iv1350.salepos.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salepos.integration.*;
import se.kth.iv1350.salepos.integration.discount.Discounter;

public class SaleTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    private Sale instanceToTest;
    private RegistryCreator regCreator;
    private ItemRegistry itemReg;
    private DiscountRegistry discountReg;
    
    @BeforeEach
    public void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        instanceToTest = new Sale();
        regCreator = new RegistryCreator();
        itemReg = regCreator.getItemRegistry();
        discountReg = regCreator.getDiscountRegistry();
    }
    
    @AfterEach
    public void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        instanceToTest = null;
        regCreator = null;
        itemReg = null;
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
    public void testAddDiscount() { 
        CustomerID customerID = new CustomerID(980325);
        SaleInfoForDiscountDTO saleInfo = instanceToTest.getSaleInfoForDiscounts();
        List<Discounter> discounts = discountReg.getEligibleDiscount(customerID, saleInfo);
        try {
            CurrentSaleDTO currentSaleInfo = instanceToTest.addDiscount(discounts);
            assertTrue(currentSaleInfo instanceof CurrentSaleDTO);
        } catch (NoEligibleDiscountException e) {
            fail("Got exception");
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAddDiscountThatGivesException() {
        CustomerID customerID = new CustomerID(700101);
        SaleInfoForDiscountDTO saleInfo = instanceToTest.getSaleInfoForDiscounts();
        List<Discounter> discounts = discountReg.getEligibleDiscount(customerID, saleInfo);
        try {
            instanceToTest.addDiscount(discounts);
            fail("Added discounts.");
        } catch (NoEligibleDiscountException e) {
            assertTrue(e.getMessage().contains("not eligible for any discounts"));
        }
    }
    
    @Test
    public void testAddDiscountCorrectCalculationBaguetteAndSoap() {
        ItemID baguette = new ItemID(10001);
        ItemID soap = new ItemID(70707);
        ItemID apple = new ItemID(89991);
        CustomerID customerID = new CustomerID(730620);
        try {
            instanceToTest.registerItem(baguette, itemReg);
            instanceToTest.registerItem(baguette, itemReg);
            instanceToTest.registerItem(soap, itemReg);
            instanceToTest.registerItem(soap, itemReg);
            instanceToTest.registerItem(apple, itemReg);
        } catch (NoSuchItemIdentifierException e) {
            System.out.println("Could not start test.");
            e.printStackTrace();
        }
        SaleInfoForDiscountDTO saleInfoDiscount = instanceToTest.getSaleInfoForDiscounts();
        List<Discounter> discounts = discountReg.getEligibleDiscount(customerID, saleInfoDiscount);
        try {
            instanceToTest.addDiscount(discounts);
            double expectedResult = 66.55;
            CurrentSaleDTO saleInfo = instanceToTest.getSaleInfo();
            Amount result = saleInfo.getTotalPriceWithDiscount();
            double marginOfError = 0.01;
            assertEquals(expectedResult, result.getAmount(), marginOfError, "Did not get the correct value.");
        } catch (NoEligibleDiscountException e) {
            fail("Got exception.");
            e.printStackTrace();
        }
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

