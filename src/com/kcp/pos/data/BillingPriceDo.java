/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.data;

import com.kcp.pos.modal.BillingPrice;
import com.kcp.pos.modal.BillingType;


import com.kcp.pos.modal.ItemDetails;
import com.kcp.pos.modal.Items;
import com.kcp.pos.modal.Users;
import java.util.Date;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Column;

/**
 *
 * @author Prakash
 */
public class BillingPriceDo {

    private SimpleIntegerProperty idPk = new SimpleIntegerProperty();
    private SimpleIntegerProperty itemDetailsId = new SimpleIntegerProperty();
     
    private SimpleDoubleProperty startRange = new SimpleDoubleProperty();
    private SimpleDoubleProperty endRange = new SimpleDoubleProperty();
    private SimpleStringProperty modifiedUser = new SimpleStringProperty();
    private SimpleDoubleProperty billingPrice = new SimpleDoubleProperty();
    //private SimpleDoubleProperty sellingPrice=new SimpleDoubleProperty();
  

    public BillingPriceDo(BillingPrice billingPrice) {
        this.idPk.set(billingPrice.getIdPk());
        this.itemDetailsId.set(billingPrice.getItemDetails().getIdPk());
        this.startRange.set(billingPrice.getStartRange());
        this.endRange.set(billingPrice.getEndRange());
        this.billingPrice.set(billingPrice.getBillingPrice());
        
        
        this.modifiedUser.set(billingPrice.getModifiedUser().getFirstName());
        
        /*this.startRange2.set(retailBillingPrice.getStartRange2());
         this.endRange2.set(retailBillingPrice.getEndRange2()) ;
         this.billingPrice2.set(retailBillingPrice.getBillingPrice2());
         this.startRange3.set(retailBillingPrice.getStartRange3() );
         this.endRange3.set(retailBillingPrice.getEndRange3() );
         this.billingPrice3.set(retailBillingPrice.getBillingPrice3() );*/
    }


    

    public Double getStartRange() {
        return startRange.get();
    }

    public void setStartRange(SimpleDoubleProperty startRange) {
        this.startRange = startRange;
    }

    public Double getEndRange() {
        return endRange.get();
    }

    public void setEndRange(SimpleDoubleProperty endRange) {
        this.endRange = endRange;
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
    
    public void setUom(String uom) {
        this.modifiedUser.set(uom);
    }

    public Integer getIdPk() {
        return idPk.get();
    }

    public void setIdPk(SimpleIntegerProperty idPk) {
        this.idPk = idPk;
    }

    
   /* public String getHasFree() {
        return hasFree.get();
    }

    public void setHasFree(String hasFree) {
        this.hasFree.set(hasFree);
    }*/
    
    
    
}
