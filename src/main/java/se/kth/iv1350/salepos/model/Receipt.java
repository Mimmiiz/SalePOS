/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salepos.model;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * This class represents a receipt that contains date and time of sale. The store's name and adress.
 * Name, quantity and price for each item. Total price for the entire sale and VAT for the entire sale.
 */
public class Receipt {
    private String storeAddress = "Super Street 10, Super Town, 19021";
    private String storeName = "Super Store";   
    private LocalTime saleTime;
    private ArrayList<Item> list;
    private Amount totalPrice;
    private double totalVat;

    /**
     * Creates a new instance.
     * 
     * @param saleTime The time of the sale.
     * @param list The list of the items of the sale.
     * @param totalPrice The total price of the salem, including VAT.
     * @param totalVat The total VAT of the sale.
     */
    public Receipt(LocalTime saleTime, ArrayList<Item> list, double totalPrice, double totalVat) {
        this.saleTime = saleTime;
        this.list = list;
        this.totalPrice = totalPrice;
        this.totalVat = totalVat;
    }
}
