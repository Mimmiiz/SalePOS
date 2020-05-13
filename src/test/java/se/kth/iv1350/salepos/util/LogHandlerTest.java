package se.kth.iv1350.salepos.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salepos.integration.ItemRegistryException;

public class LogHandlerTest {
    private LogHandler instanceToTest = LogHandler.getLogHandler();
    private String logFileName = "sale-dto-log.txt";

    @Test
    public void testLogException() throws IOException {
        ItemRegistryException exception = new ItemRegistryException("This is an exception message.");
        String expectedMessageResult = ("This is an exception message.");
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String expectedTime = formatter.format(timeNow);
        String expectedStackTrace = "at se.kth.iv1350.salepos.util.LogHandlerTest.testLogException";
        instanceToTest.logException(exception);
        assertTrue(fileContains(expectedTime), "File contains wrong printout.");
        assertTrue(fileContains(expectedMessageResult), "File contains wrong printout.");
        assertTrue(fileContains(expectedStackTrace), "File contains wrong printout.");
    }
    
    private boolean fileContains(String searchedString) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader(logFileName));
        String line = null;
        while ((line = file.readLine()) != null) {
            if (line.contains(searchedString)) {
                return true;
            }
        }
        return false;
    } 
}
