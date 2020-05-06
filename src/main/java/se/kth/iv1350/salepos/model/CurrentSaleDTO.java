package se.kth.iv1350.salepos.model;

/**
 * This class is a DTO of the sale. It contains current sale information.
 */
public final class CurrentSaleDTO {
    private Amount totalPriceWithVat;
    private String itemName;
    private Amount itemPrice;
    private Amount itemVatRate;
    private Amount itemQuantity;

   /**
    * Creates a new instance.
    * 
    * @param item The latest item that was added to the sale.
    * @param totalPriceWithVat The total price with VAT.
    */
    public CurrentSaleDTO(Item item, Amount totalPriceWithVat) {
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
    public CurrentSaleDTO(Amount totalPriceWithVat) {
        this.totalPriceWithVat = new Amount(totalPriceWithVat);
    }

    /**
     * Get the value of totalPriceWithVat.
     * 
     * @return The value of totalPriceWithVat.
     */
    public Amount getTotalPriceWithVat() {
        return new Amount(totalPriceWithVat);
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
    public Amount getItemPrice() {
        return new Amount(itemPrice);
    }

    /**
     * Get the value of itemQuantity.
     * 
     * @return The value of itemQuantity.
     */
    public Amount getItemQuantity() {
        return new Amount(itemQuantity);
    }

    /**
     * Get the value of itemVatRate.
     * 
     * @return The value of itemVatRate.
     */
    public Amount getItemVatRate() {
        return new Amount(itemVatRate);
    } 
}