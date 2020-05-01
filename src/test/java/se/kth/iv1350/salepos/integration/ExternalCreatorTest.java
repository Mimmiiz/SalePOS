package se.kth.iv1350.salepos.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExternalCreatorTest {
    private ExternalCreator instanceToTest;
    
    public ExternalCreatorTest() {
    }

    @Test
    public void testCreateExternalInventory() {
        instanceToTest = new ExternalCreator();
        ExternalInventory extInventoryInstance = instanceToTest.getExternalInventory();
        assertTrue(extInventoryInstance instanceof ExternalInventory);
    }

    @Test
    public void testCreateExternalAccount() {
        instanceToTest = new ExternalCreator();
        ExternalAccount extAccountInstance = instanceToTest.getExternalAccount();
        assertTrue(extAccountInstance instanceof ExternalAccount);
    }   
}
