/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.data;

import com.kcp.pos.modal.BillingPrice;
import com.kcp.pos.modal.Items;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Prakash
 */
public class BillingPriceDo {
    
   
    private Items item;
    /*private SimpleIntegerProperty startRange1;
    private SimpleIntegerProperty endRange1;
    private SimpleDoubleProperty billingPrice1;
    private SimpleIntegerProperty startRange2;
    private SimpleIntegerProperty endRange2;
    private SimpleDoubleProperty billingPrice2;
    private SimpleIntegerProperty startRange3;
    private SimpleIntegerProperty endRange3;
    private SimpleDoubleProperty billingPrice3;*/
    private SimpleIntegerProperty startRange=new SimpleIntegerProperty();
    private SimpleIntegerProperty endRange= new SimpleIntegerProperty();
    private SimpleDoubleProperty billingPrice = new SimpleDoubleProperty();
    

    public BillingPriceDo(BillingPrice billingPrice) {
        this.item = item;
        this.startRange.set(billingPrice.getStartRange());
        this.endRange.set(billingPrice.getEndRange());
        this.billingPrice.set(billingPrice.getBillingPrice());
        
        /*this.startRange2.set(billingPrice.getStartRange2());
        this.endRange2.set(billingPrice.getEndRange2()) ;
        this.billingPrice2.set(billingPrice.getBillingPrice2());
        this.startRange3.set(billingPrice.getStartRange3() );
        this.endRange3.set(billingPrice.getEndRange3() );
        this.billingPrice3.set(billingPrice.getBillingPrice3() );*/
    }

    public BillingPriceDo() {

    }

    
    
    
    
    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public Integer getStartRange() {
        return startRange.get();
    }

    public void setStartRange(SimpleIntegerProperty startRange1) {
        this.startRange = startRange1;
    }

    public Integer getEndRange() {
        return endRange.get();
    }

    public void setEndRange(SimpleIntegerProperty endRange1) {
        this.endRange = endRange1;
    }

    public Double getBillingPrice() {
        return billingPrice.get();
    }

    
    /*public void setBillingPrice1(SimpleDoubleProperty billingPrice1) {
        this.billingPrice1 = billingPrice1;
    }

    public Integer getStartRange2() {
        return startRange2.get();
    }

    public void setStartRange2(SimpleIntegerProperty startRange2) {
        this.startRange2 = startRange2;
    }

    public Integer getEndRange2() {
        return endRange2.get();
    }

    public void setEndRange2(SimpleIntegerProperty endRange2) {
        this.endRange2 = endRange2;
    }

    public Double getBillingPrice2() {
        return billingPrice2.get();
    }

    public void setBillingPrice2(SimpleDoubleProperty billingPrice2) {
        this.billingPrice2 = billingPrice2;
    }

    public Integer getStartRange3() {
        return startRange3.get();
    }

    public void setStartRange3(SimpleIntegerProperty startRange3) {
        this.startRange3 = startRange3;
    }

    public Integer getEndRange3() {
        return endRange3.get();
    }

    public void setEndRange3(SimpleIntegerProperty endRange3) {
        this.endRange3 = endRange3;
    }

    public Double getBillingPrice3() {
        return billingPrice3.get();
    }

    public void setBillingPrice3(SimpleDoubleProperty billingPrice3) {
        this.billingPrice3 = billingPrice3;
    }*/
    
    

    
}
