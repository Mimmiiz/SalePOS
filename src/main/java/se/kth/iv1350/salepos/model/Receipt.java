package se.kth.iv1350.salepos.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

/**
 * This class represents a receipt that contains date and time of sale. The store's name and adress.
 * Name, quantity and price for each item. Total price for the entire sale and VAT for the entire sale.
 */
public class Receipt {
    private final String storeAddress = "Super Street 10, Super Town, 19021";
    private final String storeName = "Super Store";   
    private final LocalDateTime saleTime;
    private final ArrayList<Item> list;
    private final Amount totalPrice;
    private final Amount totalPriceWithDiscount;
    private final Amount totalVat;

    /**
     * Creates a new instance.
     * 
     * @param saleTime The time of the sale.
     * @param list The list of the items of the sale.
     * @param totalPrice The total price of the sale, including VAT.
     * @param totalPriceWithDiscount The total price of the sale with discount. 
     * @param totalVat The total VAT of the sale.
     */
    public Receipt(LocalDateTime saleTime, ArrayList<Item> list, Amount totalPrice, Amount totalPriceWithDiscount, Amount totalVat) {
        this.saleTime = saleTime;
        this.list = list;
        this.totalPrice = new Amount(totalPrice);
        this.totalPriceWithDiscount = new Amount(totalPriceWithDiscount);
        this.totalVat = new Amount(totalVat);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("---------------- RECEIPT ----------------\n");
        builder.append(storeName).append("\n");
        builder.append(storeAddress).append("\n");
        builder.append(saleTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        builder.append("\n   ITEM:\tUNIT PRICE:\tTOTAL:\n");
        
        for (Item item : list) {
            builder.append((int)item.getQuantity().getAmount()).append("  ");
            builder.append(item.getName()).append("\t");
            builder.append(item.getPrice().getAmount()).append("\t\t");
            builder.append(item.getPrice().multiply(item.getQuantity()).getAmount()).append("\n");
        }
        builder.append("------------------------------------------\n");
        builder.append("Total:\t\t\t\t").append(totalPrice.getAmount());
        if (totalPriceWithDiscount.getAmount() != 0)
            builder.append("\nTotal with discount:\t\t").append(totalPriceWithDiscount.getAmount());
        builder.append("\nTax:\t\t\t\t").append(totalVat.getAmount()).append("%");
        builder.append("\n------------------------------------------\n");
        return builder.toString();
    }
}
