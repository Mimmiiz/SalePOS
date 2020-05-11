package se.kth.iv1350.salepos.controller;
/**
 * Thrown when an operatrion failed with unknown reason.
 */
public class OperationFailedException extends Exception {
    
    /**
     * Creates a new instance with a specified message and the root cause of the fail.
     * 
     * @param message The exception message.
     * @param cause The exception that caused this excecution.
     */
    public OperationFailedException (String message, Exception cause) {
        super(message);
    }
}
