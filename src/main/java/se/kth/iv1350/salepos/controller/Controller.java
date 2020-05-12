package se.kth.iv1350.salepos.controller;

import se.kth.iv1350.salepos.integration.DiscountRegistry;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.salepos.integration.*;
import se.kth.iv1350.salepos.model.*;

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
    private List<SaleObserver> saleObservers = new ArrayList<>();
    
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
        sale.addSaleObservers(saleObservers);
    }
    
    /**
     * Searches for an Item with the specified item ID. Returns sale information, including item description to
     * the view.
     * 
     * @param itemID The item ID of a specific item.
     * @return Current sale information.
     * @throws NoSuchItemIdentifierException if the specified ItemID does not exist.
     * @throws OperationFailedException 
     */
    public CurrentSaleDTO registerItem(ItemID itemID) throws NoSuchItemIdentifierException, 
            OperationFailedException {
        try {
            CurrentSaleDTO saleInfo = sale.registerItem(itemID, itemRegistry);
            return  saleInfo; 
        } catch (ItemRegistryException itemRegExc) {
            throw new OperationFailedException("The item could not be registered.", itemRegExc);
        }
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
    
    public CurrentSaleDTO requestDiscount(CustomerID customerID) {
        List<Item> items = sale.getSaleInfoForDiscounts();
        Amount totalPrice = sale.getSaleInfo().getTotalPriceWithVat();
        Amount totalPriceAfterDiscount = discountRegistry.calculateEligibleDiscount(customerID, items, totalPrice);
        if (totalPriceAfterDiscount.getAmount() != totalPrice.getAmount()) {
            sale.setTotalPriceWithDiscount(totalPriceAfterDiscount);
        }
        return sale.getSaleInfo();
    }
    
    /**
     * The specified observer will be notified when the sale has been paid.
     * There will only be notifications for sales after this method has been called.
     * 
     * @param saleObserver The observer to notify.
     */
    public void addSaleObserver(SaleObserver saleObserver) {
        saleObservers.add(saleObserver);
    }

    /**
     * Get the value of sale.
     * 
     * @return the value of sale.
     */
    Sale getSale() {
        return sale;
    }

    private Exception OperationFailedException(String could_not_add_discounts_customer_might_no) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
