package se.kth.iv1350.salepos.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegistryCreatorTest {
    private RegistryCreator instanceToTest;
    
    public RegistryCreatorTest() {
    }

    @Test
    public void testCreateItemRegistry() {
        instanceToTest = new RegistryCreator();
        ItemRegistry itemRegInstance = instanceToTest.getItemRegistry();
        assertTrue(itemRegInstance instanceof ItemRegistry, "RegistryCreator did not create ItemRegistry.");
    }

    @Test
    public void testGetDiscountRegistry() {
        instanceToTest = new RegistryCreator();
        DiscountRegistry discountRegInstance = instanceToTest.getDiscountRegistry();
        assertTrue(discountRegInstance instanceof DiscountRegistry, "RegistryCreator did not create DiscountRegistry.");
    } 
}
