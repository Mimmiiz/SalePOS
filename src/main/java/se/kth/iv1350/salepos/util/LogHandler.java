package se.kth.iv1350.salepos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Takes care of the exceptions and logs them to a text file.
 */
public class LogHandler {
    private static final String LOG_FILE_NAME = "sale-dto-log.txt";
    private static final LogHandler LOG_HANDLER_INSTANCE = new LogHandler();
    private PrintWriter printWriter;
    
    /**
     * Creates a new instance of the log handler.
     * 
     * @throws IOException 
     */
    private LogHandler() {
        try {
            printWriter = new PrintWriter (new FileWriter(LOG_FILE_NAME), true);
        } catch (IOException exc) {
            System.out.println("Could not create LogHandler.");
            exc.printStackTrace();
        }
    }
    
    public static LogHandler getLogHandler() {
        return LOG_HANDLER_INSTANCE;
    }
    
    /**
     * Logs the exception in a text file which includes the information about the thrown exception.
     * 
     * @param exc The exception that will be logged.
     */
    public void logException (Exception exc) {
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append(getDateAndTime());
        logBuilder.append(", Exception was thrown: ");
        logBuilder.append(exc.getMessage());
        printWriter.println(logBuilder);
        exc.printStackTrace(printWriter);
    }
    
    private String getDateAndTime () {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
