package se.kth.iv1350.salepos.controller;

import se.kth.iv1350.salepos.integration.DiscountRegistry;
import se.kth.iv1350.salepos.integration.ExternalAccount;
import se.kth.iv1350.salepos.integration.ExternalCreator;
import se.kth.iv1350.salepos.integration.ExternalInventory;
import se.kth.iv1350.salepos.integration.NoSuchItemIdentifierException;
import se.kth.iv1350.salepos.integration.ItemRegistry;
import se.kth.iv1350.salepos.integration.Printer;
import se.kth.iv1350.salepos.integration.RegistryCreator;
import se.kth.iv1350.salepos.model.Amount;
import se.kth.iv1350.salepos.model.CashPayment;
import se.kth.iv1350.salepos.model.CashRegister;
import se.kth.iv1350.salepos.model.CompletedSaleDTO;
import se.kth.iv1350.salepos.model.CurrentSaleDTO;
import se.kth.iv1350.salepos.model.ItemID;
import se.kth.iv1350.salepos.model.Sale;

/**
 * This is the application's only controller class. All calls to the model pass through here.
 */
public class Controller {
    private DiscountRegistry discountRegistry;
    private ItemRegistry itemRegistry;
    private ExternalInventory externalInventory;
    private ExternalAccount externalAccount;
    private Printer printer;
    private CashRegister cashRegister;
    private Sale sale;
    
    /**
     * Creates a new instance. 
     * 
     * @param regCreator Used to get all classes that handles the external registries.
     * @param extCreator Used to get all classes that handles external database systmems.
     * @param printer Interface to printer.
     */
    public Controller(RegistryCreator regCreator, ExternalCreator extCreator, Printer printer) {
        this.discountRegistry = regCreator.getDiscountRegistry();
        this.itemRegistry = regCreator.getItemRegistry();
        this.externalAccount = extCreator.getExternalAccount();
        this.externalInventory = extCreator.getExternalInventory();
        this.printer = printer;
        this.cashRegister = new CashRegister();
    }
    
    /**
     * Starts a new sale. This method must be called before performing any sale operations.
     */
    public void startSale() {
        sale = new Sale();
    }
    
    /**
     * Searches for an Item with the specified item ID. Returns sale information, including item description to
     * the view.
     * 
     * @param itemID The item ID of a specific item.
     * @return Current sale information.
     * @throws NoSuchItemIdentifierException if the specified ItemID does not exist.
     */
    public CurrentSaleDTO registerItem(ItemID itemID) throws NoSuchItemIdentifierException {
        CurrentSaleDTO saleInfo = sale.registerItem(itemID, itemRegistry);
        
        return  saleInfo; 
    }
    
    /**
     * Ends the current sale. The total price of the sale is returned.
     * 
     * @return  Current sale information.
     */
    public CurrentSaleDTO endSale() {
        CurrentSaleDTO saleInfo = sale.getSaleInfo();
        
        return saleInfo;
    }
    
    /**
     * Performs the payment of the current sale and returns the amount of change.
     * 
     * @param paidAmount The entered amount by the customer.
     * @return The amount of change.
     */
    public Amount pay(Amount paidAmount) {
        CashPayment payment = new CashPayment(paidAmount);
        Amount change = sale.pay(payment);
        cashRegister.addPayment(payment);
        CompletedSaleDTO completedSaleInfo = sale.getCompletedSaleInfo();
        externalInventory.updateInventory(completedSaleInfo);
        externalAccount.updateAccount(completedSaleInfo);
        sale.printReceipt(printer);
        
        return change;
    }

    /**
     * Get the value of sale.
     * 
     * @return the value of sale.
     */
    Sale getSale() {
        return sale;
    }
}
