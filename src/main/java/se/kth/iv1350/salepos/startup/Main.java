package se.kth.iv1350.salepos.startup;

import se.kth.iv1350.salepos.controller.Controller;
import se.kth.iv1350.salepos.integration.ExternalCreator;
import se.kth.iv1350.salepos.integration.Printer;
import se.kth.iv1350.salepos.integration.RegistryCreator;
import se.kth.iv1350.salepos.view.View;

/**
 * Contains the main method and startups the entire application.
 *
 */
public class Main {
    
    /**
     * Starts the application. 
     * 
     * @param args The application does not take any command line parameters.
     */
    public static void main (String[] args){
        RegistryCreator registryCreator = new RegistryCreator();
        ExternalCreator externalCreator = new ExternalCreator();
        Printer printer = new Printer();
        Controller contr = new Controller(registryCreator, externalCreator, printer);
        View view = new View(contr);
        
        view.runFakeExecution();
    }
    
}
