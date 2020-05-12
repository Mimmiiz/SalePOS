package se.kth.iv1350.salepos.view;

import se.kth.iv1350.salepos.model.Amount;
import se.kth.iv1350.salepos.model.SaleObserver;

/**
 * Prints the total revenue to the display.
 */
public class TotalRevenueView implements SaleObserver{
    Amount totalRevenue = new Amount();
    
    /**
     * Updates the total revenue.
     * 
     * @param totalPrice the total price that will be added to the total revenue.
     */
    @Override
    public void updateTotalRevenue(Amount totalPrice) {
        totalRevenue = (totalRevenue.add(totalPrice));
        printOutTotalRevenue();
    }
    
    private void printOutTotalRevenue() {
        System.out.println("---------------- DISPLAY ----------------");
        System.out.println("Total revenue: " + totalRevenue.getAmount());
        System.out.println("-----------------------------------------\n");
    }
}
