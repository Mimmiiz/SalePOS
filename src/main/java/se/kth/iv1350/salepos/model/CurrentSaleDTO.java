package se.kth.iv1350.salepos.model;

/**
 * This class is a DTO of the sale. It contains current sale information.
 */
public final class CurrentSaleDTO {
    private double totalPriceWithVat;
    private String itemName;
    private int itemPrice;
    private int itemVatRate;
    private int itemQuantity;

   /**
    * Creates a new instance.
    * 
    * @param item The latest item that was added to the sale.
    * @param totalPriceWithVat The total price with VAT.
    */
    public CurrentSaleDTO(Item item, double totalPriceWithVat) {
       this.totalPriceWithVat = totalPriceWithVat;
       itemName = item.getName();
       itemPrice = item.getPrice();
       itemVatRate = item.getVatRate();
       itemQuantity = item.getQuantity();
    }
    
    /**
     * Creates a new instance. Specifically used for endSale operation where only total price with 
     * VAT is presented. 
     * 
     * @param totalPriceWithVat The total price with VAT.
     */
    public CurrentSaleDTO(double totalPriceWithVat) {
        this.totalPriceWithVat = totalPriceWithVat;
    }

    /**
     * Get the value of totalPriceWithVat.
     * 
     * @return The value of totalPriceWithVat.
     */
    public double getTotalPriceWithVat() {
        return totalPriceWithVat;
    }

    /**
     * Get the value of itemName.
     * 
     * @return  The value of itemName.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Get the value of itemPrice.
     * 
     * @return The value of itemPrice.
     */
    public int getItemPrice() {
        return itemPrice;
    }

    /**
     * Get the value of itemQuantity.
     * 
     * @return The value of itemQuantity.
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /**
     * Get the value of itemVatRate.
     * 
     * @return The value of itemVatRate.
     */
    public int getItemVatRate() {
        return itemVatRate;
    } 
}