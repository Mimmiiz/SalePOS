package se.kth.iv1350.salepos.model;

/**
 * This class represents a cash register. There shall be one instance of this class for each register.
 */
public class CashRegister {
    private Amount balance = new Amount();
    
    /**
     * Adds a payment to the cash register and updates the balance in the cash register.
     * 
     * @param payment The payment made by the customer.
     */
    public void addPayment(CashPayment payment) {
        balance = balance.add(payment.getTotalPrice());   
    } 
}
