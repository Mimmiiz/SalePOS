package se.kth.iv1350.salepos.view;

import java.io.IOException;
import se.kth.iv1350.salepos.controller.Controller;
import se.kth.iv1350.salepos.controller.OperationFailedException;
import se.kth.iv1350.salepos.integration.NoEligibleDiscountException;
import se.kth.iv1350.salepos.integration.NoSuchItemIdentifierException;
import se.kth.iv1350.salepos.model.Amount;
import se.kth.iv1350.salepos.model.CurrentSaleDTO;
import se.kth.iv1350.salepos.model.CustomerID;
import se.kth.iv1350.salepos.model.ItemID;
import se.kth.iv1350.salepos.util.LogHandler;

/**
 * This application does not have a view, instead, this class is a placeholder for the real view. 
 * It contains hardcoded execution with calls to all system operations in the controller.
 */
public class View {
    private Controller contr;
    private ErrorMessageHandler errorMessageHandler = new ErrorMessageHandler();
    private LogHandler logHandler;
    
    /**
     * Creates a new instance.
     * 
     * @param contr The controller that is used for all calls to other layers.
     * @throws java.io.IOException If log handler can not be created.
     */
    public View (Controller contr) throws IOException{
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueView());
        try {
        this.logHandler = new LogHandler();
        } catch (IOException exc) {
            System.out.println("Log handler could not be created.");
        }
    }
    
    /**
     * Performs a fake execution of a sale, by calling all system operations in the controller.
     */
    public void runFakeExecution() {
        contr.startSale();
        System.out.println("A new sale has been started.");
        
        CurrentSaleDTO saleInfo;
        
        addNewItemID(89991);
        addNewItemID(89991);  
        addNewItemID(89990);
        addNewItemID(10001);
        addNewItemID(88888);
        
        System.out.println("Adds an item identifier that does not exist.");
        addNewItemID(55555);
        
        System.out.println("Cashier requests discount.");
        try {
            saleInfo = contr.requestDiscount(new CustomerID(14502));
            System.out.println("Total price including VAT: " + saleInfo.getTotalPriceWithVat().getAmount());
            System.out.println("Total price with the added discount: " + saleInfo.getTotalPriceWithDiscount().getAmount());
        } catch(NoEligibleDiscountException exc) {
            handleException(exc.getMessage(), exc);
        }
        
        System.out.println("The sale has ended.");
        saleInfo = contr.endSale();
        System.out.println("Output: ");
        System.out.println("Total price including VAT: " + saleInfo.getTotalPriceWithVat().getAmount());
        
        Amount paidAmount = new Amount(40);
        Amount change = contr.pay(paidAmount);
        System.out.println("The payment has been started. Paid amount: 40");
        System.out.println ("The amount of change: " + change.getAmount());
    }
    
    /**
     * Enters a new Item ID to the program. Starts a new system call to the controller.
     * 
     * @param identifierNumber The specified identifier number of the Item ID.
     */
    private void addNewItemID (int identifierNumber) {
        CurrentSaleDTO saleInfo;
        try {
            ItemID itemID = new ItemID(identifierNumber);
            System.out.println("A new item identifier has been entered. Item ID: " + identifierNumber);
            saleInfo = contr.registerItem(itemID);
            printOutSaleInformation(saleInfo);
        } catch (NoSuchItemIdentifierException exc) {
            handleException("The entered item identifier " + identifierNumber + " does not exist, "
                    + "please try again.", exc);
        } catch (OperationFailedException exc) {
            handleException("The item could not be registered to the sale. Check connection or try again.", exc);
        }
    }
   
    /**
     * Prints out the retreived sale information to the screen.
     * 
     * @param saleInfo Contains all current information about the sale sent to the view. 
     */
    private void printOutSaleInformation(CurrentSaleDTO saleInfo) {
        System.out.println("Output: ");
        System.out.println("Item name: " + saleInfo.getItemName());
        System.out.println("Item price: " + saleInfo.getItemPrice().getAmount());
        System.out.println("Item VAT rate: " + saleInfo.getItemVatRate().getAmount());
        System.out.println("Item quantity: " + saleInfo.getItemQuantity().getAmount());
        System.out.println("Running total including VAT: " + saleInfo.getTotalPriceWithVat().getAmount());
    }
    
    private void handleException(String message, Exception exc) {
        errorMessageHandler.showErrorMessage(message);
        logHandler.logException(exc);
    }
}
