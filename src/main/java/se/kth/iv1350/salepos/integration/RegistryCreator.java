package se.kth.iv1350.salepos.integration;

/**
 * This class is responsible for instantiating all external registries. 
 */
public class RegistryCreator {
    private ItemRegistry itemRegistry = new ItemRegistry();
    private DiscountRegistry discountRegistry = new DiscountRegistry();
    
    /**
     * Get the value of itemRegistry.
     * 
     * @return the value of itemRegistry.
     */
    public ItemRegistry getItemRegistry(){
        return itemRegistry;
    }
    
    /** 
     * Get the value of discountRegistry.
     * 
     * @return the value of discountRegistry.
     */
    public DiscountRegistry getDiscountRegistry(){
        return discountRegistry;
    } 
}
