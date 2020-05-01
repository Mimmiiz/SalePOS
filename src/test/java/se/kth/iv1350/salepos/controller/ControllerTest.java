package se.kth.iv1350.salepos.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.salepos.integration.ExternalCreator;
import se.kth.iv1350.salepos.integration.Printer;
import se.kth.iv1350.salepos.integration.RegistryCreator;
import se.kth.iv1350.salepos.model.CurrentSaleDTO;
import se.kth.iv1350.salepos.model.ItemID;
import se.kth.iv1350.salepos.model.Sale;

public class ControllerTest {
    private Controller instanceToTest;
    private RegistryCreator regCreator;
    private ExternalCreator extCreator;
    private Printer printer;
    
    @BeforeEach
    public void setUp() {
        printer = new Printer();
        extCreator = new ExternalCreator();
        regCreator = new RegistryCreator();
        instanceToTest = new Controller(regCreator, extCreator, printer);
    }
    
    @AfterEach
    public void tearDown() {
        printer = null;
        extCreator = null;
        instanceToTest = null;
        regCreator = null;
    }
    
    @Test
    public void testStartSale() {
        instanceToTest.startSale();
        assertTrue(instanceToTest.getSale() instanceof Sale, "A new sale did not initiate correctly.");
    }

    @Test
    public void testSearchForItem() {
        int identifierNumber = 89991;
        instanceToTest.startSale();
        ItemID itemID = new ItemID(identifierNumber);
        CurrentSaleDTO saleInfoInstance = instanceToTest.searchForItem(itemID);
        assertTrue(saleInfoInstance instanceof CurrentSaleDTO, "Sale information was not retreived correctly.");
    }
    
    @Test
    public void testEndSale() {
        instanceToTest.startSale();
        CurrentSaleDTO saleInfoInstance = instanceToTest.endSale();
        assertTrue(saleInfoInstance instanceof CurrentSaleDTO, "Sale information was not retreived correctly.");
    }
}