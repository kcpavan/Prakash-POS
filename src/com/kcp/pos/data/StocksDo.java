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
    private SimpleDoubleProperty unitQuantity = new SimpleDoubleProperty();
    private SimpleIntegerProperty caseQuantity = new SimpleIntegerProperty();
    private SimpleIntegerProperty unitsPerCase = new SimpleIntegerProperty();
    
    
     public StocksDo(Stocks stocks) {
       this.itemName.set(stocks.getItemDetails().getItem().getItemName());
       this.barcode.set(stocks.getItemDetails().getItem().getBarcode());
       this.mrp.set(stocks.getItemDetails().getMrp());
       this.unitQuantity.set(stocks.getUnitQuantity());
       this.caseQuantity.set(stocks.getCaseQuantity());
       this.unitsPerCase.set(stocks.getUnitsPerCase());
     }

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public String getBarcode() {
        return barcode.get();
    }

    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
    }

    public Double getMrp() {
        return mrp.get();
    }

    public void setMrp(Double mrp) {
        this.mrp.set(mrp);
    }

    public Double getUnitQuantity() {
        return unitQuantity.get();
    }

    public void setUnitQuantity(Integer quantity) {
        this.unitQuantity.set(quantity);
    }

    public Integer getCaseQuantity() {
        return caseQuantity.get();
    }

    public void setCaseQuantity(Integer caseQuantity) {
        this.caseQuantity.set(caseQuantity);
    }

    public Integer getUnitsPerCase() {
        return unitsPerCase.get();
    }

    public void setUnitsPerCase(Integer unitsPerCase) {
        this.unitsPerCase.set(unitsPerCase);
    }
    
    
}
