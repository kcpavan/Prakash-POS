/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.data;

import com.kcp.pos.modal.Distributor;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.Stocks;
import com.kcp.pos.modal.Users;
import java.util.Date;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Prakash
 */
public class StocksDo {
    
   
        
     private SimpleStringProperty itemName = new SimpleStringProperty();
    private SimpleStringProperty barcode = new SimpleStringProperty();
    private SimpleDoubleProperty mrp = new SimpleDoubleProperty();
    private SimpleIntegerProperty quantity = new SimpleIntegerProperty();
    private SimpleDoubleProperty total = new SimpleDoubleProperty();
    
     public StocksDo(Stocks stocks) {
       //this.itemName.set(invoiceDetails.getItems().getItemName());
       this.itemName.set(stocks.getItemDetails().getItem().getItemName());
       this.barcode.set(stocks.getItemDetails().getItem().getBarcode());
       this.mrp.set(stocks.getItemDetails().getMrp());
       this.quantity.set(stocks.getQuantity());
     }
       
    
}
