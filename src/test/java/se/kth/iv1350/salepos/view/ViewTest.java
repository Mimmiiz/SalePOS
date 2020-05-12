package se.kth.iv1350.salepos.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.salepos.controller.Controller;
import se.kth.iv1350.salepos.integration.ExternalCreator;
import se.kth.iv1350.salepos.integration.Printer;
import se.kth.iv1350.salepos.integration.RegistryCreator;

public class ViewTest {
    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
   
    @BeforeEach
    public void setUp() {
        try {
            RegistryCreator regCreator = new RegistryCreator();
            ExternalCreator extCreator = new ExternalCreator();
            Printer printer = new Printer();
            Controller contr = new Controller(regCreator, extCreator, printer);
            instanceToTest = new View(contr);
        } catch (IOException e) {
            System.out.println("Could not set up the test.");
            e.printStackTrace();
        }
        
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
    public void testRunFakeExecution() {
        instanceToTest.runFakeExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "started";
        assertTrue(printout.contains(expectedOutput), "UI did not start correctly.");
    }
}
