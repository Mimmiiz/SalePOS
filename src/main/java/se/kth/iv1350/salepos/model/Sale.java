package se.kth.iv1350.salepos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.salepos.integration.NoSuchItemIdentifierException;
import se.kth.iv1350.salepos.integration.ItemDTO;
import se.kth.iv1350.salepos.integration.ItemRegistry;
import se.kth.iv1350.salepos.integration.Printer;

/**
 * One single sale made by one customer and paid with one payment.
 */
public class Sale {
    private LocalDateTime saleTime;
    private ItemList itemList;
    private Amount totalPriceWithoutVat = new Amount();
    private Amount totalPriceWithVat = new Amount();
    private Amount saleVatRate = new Amount();
    private List<SaleObserver> saleObservers = new ArrayList<>();
    
    /**
     * Creates a new instance and sets the current local time of the sale.
     */
    public Sale () {
        saleTime = LocalDateTime.now();
        itemList = new ItemList();
    }
    
    /**
     * Searches for the item requested by the itemID. If the item with the specified item ID already exists in the 
     * current sale, the program increases the sold quantity by one. If the item with the specified item ID does 
     * not exist in the current sale, the program searches for the item in the item registry.
     * 
     * @param itemID The specified item ID.
     * @param itemRegistry The item registry that has item information.
     * @return A CurrentSaleDTO that contains current sale information.
     */
    public CurrentSaleDTO registerItem(ItemID itemID, ItemRegistry itemRegistry) throws NoSuchItemIdentifierException {
        boolean existingItemID = itemList.searchExistingItemID(itemID);
        ItemDTO searchedItem;
        
        if (existingItemID == true)
            itemList.incrementItemQuantity(itemID);
        else {
            searchedItem = itemRegistry.searchForItem(itemID);
            itemList.saveItemToList(searchedItem);
        }
       
        calculateTotal();
        
        CurrentSaleDTO saleInfo = createCurrentSaleDTO(itemID);
    
        return saleInfo;
    } 
    
    /**
     * Gets the final sale info after ending sale. The sale info only includes the total price of the sale,
     * including VAT.
     * 
     * @return A CurrentSaleDTO that contains current sale information.
     */
    public CurrentSaleDTO getSaleInfo() {
        CurrentSaleDTO saleInfo = new CurrentSaleDTO(totalPriceWithVat);
        
        return saleInfo;
    }
    
    /**
     * Takes care of the cash payment and returns the amount of change that should be given to the customer.
     * 
     * @param payment The cash payment from the customer.
     * @return The amount of change.
     */
    public Amount pay(CashPayment payment) {
        Amount totalPrice = new Amount(totalPriceWithVat.getAmount());
        payment.setTotalPrice(totalPrice);
        notifyObservers();
        Amount change = calculateChange(payment);
        
        
        return change;
    }
    
    /**
     * The specified observers will be notified when a sale is paid.
     * 
     * @param saleObserver The observers to notify.
     */
    public void addSaleObservers(List<SaleObserver> saleObservers) {
        this.saleObservers.addAll(saleObservers);
    }
    
    private void notifyObservers() {
        for (SaleObserver observer : saleObservers) {
            observer.updateTotalRevenue(totalPriceWithVat);
        }
    }
    
    /**
     * Get the completed sale info from the sale.
     * 
     * @return The completed sale info stored in a DTO.
     */
    public CompletedSaleDTO getCompletedSaleInfo() {
        CompletedSaleDTO completedSaleInfo = new CompletedSaleDTO ();
        
        return completedSaleInfo;
    }
    
    /**
     * Creates a receipt and calls the printer to print the created receipt.
     * 
     * @param printer The printer that will print the receipt.
     */
    public void printReceipt(Printer printer) {
        Receipt receipt = new Receipt(saleTime, itemList.getList(), totalPriceWithVat, saleVatRate);
        printer.print(receipt);
    }
    
    /**
     * Calculates the amount of change that should be given to the customer.
     * 
     * @param payment The cash payment from the customer.
     * @return The amount of change.
     */
    private Amount calculateChange(CashPayment payment) {
        Amount totalPrice = new Amount(totalPriceWithVat.getAmount());
        Amount change = payment.getPaidAmount().subtract(totalPrice);
        
        return change;
    }
    
    /**
     * Creates a new CurrentSaleDTO that contains sale info. The sale info includes item description, price,
     * and running total, including VAT.
     * 
     * @param itemID The item ID of the item that should be included in the item description.
     * @return The sale info which contains information about the current sale.
     */
    private CurrentSaleDTO createCurrentSaleDTO(ItemID itemID) {
        Item latestItemAddedToList = itemList.getSpecifiedItemFromList(itemID);
        CurrentSaleDTO saleInfo = new CurrentSaleDTO(latestItemAddedToList, totalPriceWithVat);
        
        return saleInfo;
    }
    
    /**
     * Calculates the total price of the sale at the given moment.
     */
    private void calculateTotal() {
        totalPriceWithoutVat = itemList.getTotalPrice();
        totalPriceWithVat = itemList.getTotalPriceWithVat();
        calculateSaleVat();
    }
    
    /**
     * Calculates the VAT rate for the entire sale.
     * 
     * @param priceWithoutVat The price without VAT.
     * @param priceWithVat The price with VAT.
     * @return The VAT rate for the whole sale.
     */
    private void calculateSaleVat() {
         Amount multiplier = new Amount(100);
         Amount adder = new Amount(1);
         saleVatRate =((totalPriceWithVat.divideBy(totalPriceWithoutVat)).subtract(adder)).multiply(multiplier);
    }
}
