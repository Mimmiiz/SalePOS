package se.kth.iv1350.salepos.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AmountTest {
    Amount firstNumber = new Amount(15);
    Amount secondNumber = new Amount(4);

    @Test
    public void testSubtract() {
        Amount result = firstNumber.subtract(secondNumber);
        double expectedResult = 11;
        double marginOfError = 0.01;
        assertEquals(expectedResult, result.getAmount(), marginOfError, "Wrong result from subtraction.");
    }

    @Test
    public void testAdd() {
        Amount result = firstNumber.add(secondNumber);
        double expectedResult = 19;
        double marginOfError = 0.01;
        assertEquals(expectedResult, result.getAmount(), marginOfError, "Wrong result from addition.");
    }

    @Test
    public void testMultiply() {
        Amount result = firstNumber.multiply(secondNumber);
        double expectedResult = 60;
        double marginOfError = 0.01;
        assertEquals(expectedResult, result.getAmount(), marginOfError, "Wrong result from multiplication.");
    }

    @Test
    public void testDivideBy() {
        Amount result = firstNumber.divideBy(secondNumber);
        double expectedResult = 3.75;
        double marginOfError = 0.01;
        assertEquals(expectedResult, result.getAmount(), marginOfError, "Wrong result from division.");
    }   
}