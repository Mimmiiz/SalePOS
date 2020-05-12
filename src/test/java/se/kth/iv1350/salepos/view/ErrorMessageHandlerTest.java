package se.kth.iv1350.salepos.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class ErrorMessageHandlerTest {
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;
    ErrorMessageHandler instanceToTest = new ErrorMessageHandler();
    
    @BeforeEach
    public void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void cleanUp() {
        outContent = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testShowErrorMessage() {
        String errorMessage = "This is an error message.";
        String expectedMessage = ", ERROR: \n" + errorMessage;
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String expectedTime = formatter.format(timeNow);
        instanceToTest.showErrorMessage(errorMessage);
        String result = outContent.toString();
        assertTrue(result.contains(expectedMessage), "The error message did not contain the correct message.");
        assertTrue(result.contains(expectedTime), "The error message did not contain the correct time.");
    }    
}
