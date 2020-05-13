package se.kth.iv1350.salepos.integration;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.salepos.integration.discount.Discounter;
import se.kth.iv1350.salepos.model.CustomerID;
import se.kth.iv1350.salepos.model.ItemID;
import se.kth.iv1350.salepos.model.Sale;
import se.kth.iv1350.salepos.model.SaleInfoForDiscountDTO;

public class DiscountRegistryTest {
    private DiscountRegistry instanceToTest = new DiscountRegistry();
    private RegistryCreator regCreator = new RegistryCreator();
    private ItemID soap = new ItemID(70707);
    private ItemID baguette = new ItemID(10001);
    private ItemRegistry itemRegistry;
    private Sale sale = new Sale();
    
    @BeforeEach
    public void setUp() {
        itemRegistry = regCreator.getItemRegistry();        
    }
    
    @AfterEach
    public void tearDown() {
        itemRegistry = null;
    }
   
    @Test
    public void testGetEligibleDiscountGetWholeSaleDiscount() {
        CustomerID customerID = new CustomerID(980325);
        SaleInfoForDiscountDTO saleInfo = sale.getSaleInfoForDiscounts();
        List <Discounter> discounts = instanceToTest.getEligibleDiscount(customerID, saleInfo);
        assertTrue(discounts.get(0) instanceof Discounter, "The discount was not received.");
        assertTrue(discounts.size() == 1, "More discounts than the expected discount was received.");
    } 
    
    @Test
    public void testGetEligibleDiscountGetBaguetteDiscount() {
        try {
            sale.registerItem(baguette, itemRegistry);
            sale.registerItem(baguette, itemRegistry);
        } catch (NoSuchItemIdentifierException e) {
            System.out.println("Could not register item and start the test.");
            e.printStackTrace();
        }  
        CustomerID customerID = new CustomerID(730620);
        SaleInfoForDiscountDTO saleInfo = sale.getSaleInfoForDiscounts();
        List <Discounter> discounts = instanceToTest.getEligibleDiscount(customerID, saleInfo);
        assertTrue(discounts.get(0) instanceof Discounter, "The discount was not received.");
        assertTrue(discounts.size() == 1, "More discounts than the expected discount was received.");
    }  
    
    @Test
    public void testGetEligibleDiscountGetSoapDiscount() {
        try {
            sale.registerItem(soap, itemRegistry);
            sale.registerItem(soap, itemRegistry);
            sale.registerItem(soap, itemRegistry);
        } catch (NoSuchItemIdentifierException e) {
            System.out.println("Could not register item and start the test.");
            e.printStackTrace();
        }
        CustomerID customerID = new CustomerID(730620);
        SaleInfoForDiscountDTO saleInfo = sale.getSaleInfoForDiscounts();
        List <Discounter> discounts = instanceToTest.getEligibleDiscount(customerID, saleInfo);
        assertTrue(discounts.get(0) instanceof Discounter, "The discount was not received.");
        assertTrue(discounts.size() == 1, "More discounts than the expected discount was received.");
    }
    
    @Test
    public void testGetEligibleDiscountGetNoDiscount() {
        CustomerID customerID = new CustomerID(730620);
        SaleInfoForDiscountDTO saleInfo = sale.getSaleInfoForDiscounts();
        List <Discounter> discounts = instanceToTest.getEligibleDiscount(customerID, saleInfo);
        assertTrue(discounts.isEmpty(), "The discount was not received.");
    }
    
    @Test
    public void testGetEligibleDiscountGetBaguetteAndSoapDiscount() {
        try {
            sale.registerItem(soap, itemRegistry);
            sale.registerItem(soap, itemRegistry);
            sale.registerItem(baguette, itemRegistry);
            sale.registerItem(baguette, itemRegistry);
        } catch (NoSuchItemIdentifierException e) {
            System.out.println("Could not register item and start the test.");
            e.printStackTrace();
        }
        CustomerID customerID = new CustomerID(730620);
        SaleInfoForDiscountDTO saleInfo = sale.getSaleInfoForDiscounts();
        List <Discounter> discounts = instanceToTest.getEligibleDiscount(customerID, saleInfo);
        assertTrue(discounts.get(0) instanceof Discounter, "The discount was not received.");
        assertTrue(discounts.get(1) instanceof Discounter, "The discount was not received.");
        assertTrue(discounts.size() == 2, "More discounts than the expected discount was received.");
    }
    
    @Test
    public void testGetEligibleDiscountGetSoapAndWholeSaleDiscount() {
        try {
            sale.registerItem(soap, itemRegistry);
            sale.registerItem(soap, itemRegistry);
        } catch (NoSuchItemIdentifierException e) {
            System.out.println("Could not register item and start the test.");
            e.printStackTrace();
        }
        CustomerID customerID = new CustomerID(980325);
        SaleInfoForDiscountDTO saleInfo = sale.getSaleInfoForDiscounts();
        List <Discounter> discounts = instanceToTest.getEligibleDiscount(customerID, saleInfo);
        assertTrue(discounts.get(0) instanceof Discounter, "The discount was not received.");
        assertTrue(discounts.get(1) instanceof Discounter, "The discount was not received.");
        assertTrue(discounts.size() == 2, "More discounts than the expected discount was received.");
    }
}
    
