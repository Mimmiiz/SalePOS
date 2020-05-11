package se.kth.iv1350.salepos.integration;

/**
 * This exception is thrown if something goes wrong while performing operations in the 
 * <code>ItemRegistry</code>. 
 */
public class ItemRegistryException extends RuntimeException {
    
    /**
     * Creates a new instance representing the condition stated in the specified message.
     * 
     * @param msg A message that decribes what went wrong.
     */
    public ItemRegistryException(String msg) {
        super(msg);
    } 
}
