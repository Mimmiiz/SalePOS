package se.kth.iv1350.salepos.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class handles error messages to the user.
 */
public class ErrorMessageHandler {
    
    /**
     * Prints out error message to the user interface.
     * The message includes date and time of the error and the error message.
     * 
     * @param message The error message that will be printed out.
     */
    void showErrorMessage (String message) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append("-------------------- NOTIFICATION --------------------\n");
        errorMessageBuilder.append(getLocalDateAndTime());
        errorMessageBuilder.append(", ERROR: \n");
        errorMessageBuilder.append(message).append("\n");
        errorMessageBuilder.append("-------------------------------------------------------\n");
        System.out.println(errorMessageBuilder);
    }
    
    /** 
     * @return The local time and date as a String.
     */
    private String getLocalDateAndTime() {
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return timeNow.format(formatter);
    }  
}
