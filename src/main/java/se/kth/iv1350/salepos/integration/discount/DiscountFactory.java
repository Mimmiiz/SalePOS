package se.kth.iv1350.salepos.integration.discount;

/**
 * A singleton that creates instances of the algorithms used for calculating discounts. 
 * All instances must implement <code>Discounter</code>.
 */
public class DiscountFactory {
    private static final DiscountFactory FACTORY_INSTANCE = new DiscountFactory();
    
    private DiscountFactory () {
    }
    
    /**
     * @return Returns the only existing instance of this singleton.
     */
    public static DiscountFactory getFactory() {
        return FACTORY_INSTANCE;
    }
    
    /**
     * Gets the correct discount from the specified discount name.
     * 
     * @param discountName The name of the wished discount.
     * @return An instance of the algorithm that calculates the wished discount, 
     * the instance implements <code>Discounter</code>.
     */
    public Discounter getDiscount(String discountName) {
        if (discountName == null)
            return null;
        if (discountName.equalsIgnoreCase("Baguette Discount"))
            return new BaguetteDiscount();
        else if (discountName.equalsIgnoreCase("Soap Discount"))
            return new SoapDiscount();
        else if (discountName.equalsIgnoreCase("Whole Sale Discount"))
            return new WholeSaleDiscount();   
        return null;
    }
}
