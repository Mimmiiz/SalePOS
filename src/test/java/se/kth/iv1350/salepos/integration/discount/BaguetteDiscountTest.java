package se.kth.iv1350.salepos.integration.discount;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salepos.model.Amount;

public class BaguetteDiscountTest {
    private BaguetteDiscount instanceToTest = new BaguetteDiscount();

    @Test
    public void testCalculateDiscount() {
        Amount totalPrice = new Amount(100);
        double expectedResult = 92.5;
        Amount result = instanceToTest.calculateDiscount(totalPrice);
        double marginOfError = 0.01;
        assertEquals(expectedResult, result.getAmount(), marginOfError, "The price with discount was not correct.");
    } 
}