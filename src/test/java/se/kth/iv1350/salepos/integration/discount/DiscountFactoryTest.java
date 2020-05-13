package se.kth.iv1350.salepos.integration.discount;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiscountFactoryTest {
    private DiscountFactory instanceToTest = DiscountFactory.getFactory();
    
    @Test
    public void testGetDiscountBaguette() {
        String discountName = "Baguette Discount";
        Discounter discount = instanceToTest.getDiscount(discountName);
        assertTrue(discount instanceof Discounter, "Did not return a Discounter.");
    } 
    
    @Test
    public void testGetDiscountSoap() {
        String discountName = "Soap Discount";
        Discounter discount = instanceToTest.getDiscount(discountName);
        assertTrue(discount instanceof Discounter, "Did not return a Discounter.");
    }
    
    @Test
    public void testGetDiscountWholeSale() {
        String discountName = "Whole Sale Discount";
        Discounter discount = instanceToTest.getDiscount(discountName);
        assertTrue(discount instanceof Discounter, "Did not return a Discounter.");
    }
}
