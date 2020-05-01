package se.kth.iv1350.salepos.integration;

/**
 * This class is responsible for instantiating external systems.
 */
public class ExternalCreator {
    ExternalInventory externalInventory = new ExternalInventory ();
    ExternalAccount externalAccount = new ExternalAccount ();
    
    /** 
     * Get the value of externalAccount.
     * 
     * @return the value of external Account.
     */
   public ExternalAccount getExternalAccount (){
       return externalAccount;
   }
   
   /**
    * Get the value of externalInventory.
    * 
    * @return  the value of externalInventory.
    */
   public ExternalInventory getExternalInventory (){
       return externalInventory;
   }
}
