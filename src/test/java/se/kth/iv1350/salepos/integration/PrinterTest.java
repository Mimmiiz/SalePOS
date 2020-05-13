package se.kth.iv1350.salepos.integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.salepos.model.Amount;
import se.kth.iv1350.salepos.model.Item;
import se.kth.iv1350.salepos.model.Receipt;

public class PrinterTest {
    private Printer instanceToTest = new Printer();
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    
    @BeforeEach
    public void setUp() {
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }
    
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testPrint() {
        LocalDateTime timeNow = LocalDateTime.now();
        Amount totalPrice = new Amount(35);
        Amount totalPriceWithDiscount = new Amount(20);
        Amount totalVat = new Amount(10);
        ArrayList<Item> items = new ArrayList<>();
        Receipt receipt = new Receipt(timeNow, items, totalPrice, totalPriceWithDiscount, totalVat);
        instanceToTest.print(receipt);
        String resultPrintout = printoutBuffer.toString();
        String expectetText = "\n   ITEM:\tUNIT PRICE:\tTOTAL:\n";
        String expectedTime = timeNow.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        String expectedTotalPrice = "" + totalPrice.getAmount();
        String expectedTotalPriceWithDiscount = "" + totalPriceWithDiscount.getAmount();
        String expectedTotalVat = "" + totalVat.getAmount();
        
        assertTrue(resultPrintout.contains(expectetText), "Wrong printout text.");
        assertTrue(resultPrintout.contains(expectedTime), "Wrong time printed out.");
        assertTrue(resultPrintout.contains(expectedTotalPrice), "Wrong total price printed out.");
        assertTrue(resultPrintout.contains(expectedTotalPriceWithDiscount), "Wrong total price with VAT printed.");
        assertTrue(resultPrintout.contains(expectedTotalVat), "Wrong total VAT printed out.");             
    }
}
