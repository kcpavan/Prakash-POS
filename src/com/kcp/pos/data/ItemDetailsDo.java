/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.data;

import com.kcp.pos.modal.BillingType;


import com.kcp.pos.modal.ItemDetails;
import com.kcp.pos.modal.Items;
import com.kcp.pos.modal.Users;
import java.util.Date;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Prakash
 */
public class ItemDetailsDo {

    private SimpleIntegerProperty idPk = new SimpleIntegerProperty();
    private SimpleStringProperty itemName = new SimpleStringProperty();
    private SimpleStringProperty barcode = new SimpleStringProperty();
    private SimpleDoubleProperty mrp = new SimpleDoubleProperty();
    private SimpleDoubleProperty weight = new SimpleDoubleProperty();
    private SimpleStringProperty uom = new SimpleStringProperty();
    private SimpleDoubleProperty actualPrice = new SimpleDoubleProperty();
    //private SimpleDoubleProperty sellingPrice=new SimpleDoubleProperty();
    private SimpleDoubleProperty wholesalePrice = new SimpleDoubleProperty();
    private SimpleDoubleProperty tax = new SimpleDoubleProperty();
    private SimpleBooleanProperty hasFree = new SimpleBooleanProperty();
    
    private SimpleDoubleProperty retailPrice = new SimpleDoubleProperty();
    private SimpleIntegerProperty itemId = new SimpleIntegerProperty();
    private SimpleIntegerProperty startRange = new SimpleIntegerProperty();
    private SimpleIntegerProperty endRange = new SimpleIntegerProperty();
    private SimpleDoubleProperty retailBillingPrice = new SimpleDoubleProperty();
    private SimpleDoubleProperty wholesaleBillingPrice = new SimpleDoubleProperty();
    private SimpleIntegerProperty billingTypeId = new SimpleIntegerProperty();

    public ItemDetailsDo(ItemDetails itemDetails) {
        this.idPk.set(itemDetails.getIdPk());
        this.itemName.set(itemDetails.getItem().getItemName());
        this.barcode.set(itemDetails.getItem().getBarcode());
//        this.mrp.set(items.getMrp());
        this.weight.set(itemDetails.getItem().getWeight());
        this.uom.set(itemDetails.getItem().getUom().getUomDesc());
        this.itemId.set(itemDetails.getItem().getIdPk());
        /*this.startRange.set(itemDetails.getStartRange());
         this.endRange.set(itemDetails.getEndRange());*/
        this.retailBillingPrice.set(itemDetails.getRetailBillingPrice());
        this.wholesaleBillingPrice.set(itemDetails.getWholesaleBillingPrice());
        this.tax.set(itemDetails.getTax());
        this.mrp.set(itemDetails.getMrp());
        this.actualPrice.set(itemDetails.getActualPrice());
        this.hasFree.set(itemDetails.isHasfree());
        
        


        /*this.startRange2.set(retailBillingPrice.getStartRange2());
         this.endRange2.set(retailBillingPrice.getEndRange2()) ;
         this.billingPrice2.set(retailBillingPrice.getBillingPrice2());
         this.startRange3.set(retailBillingPrice.getStartRange3() );
         this.endRange3.set(retailBillingPrice.getEndRange3() );
         this.billingPrice3.set(retailBillingPrice.getBillingPrice3() );*/
    }

    public Integer getItem() {
        return itemId.get();
    }

    public void setItem(SimpleIntegerProperty itemId) {
        this.itemId = itemId;
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
        return retailBillingPrice.get();
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
    public Integer getItemId() {
        return itemId.get();
    }

    public void setItemId(SimpleIntegerProperty itemId) {
        this.itemId = itemId;
    }

    public Double getRetailBillingPrice() {
        return retailBillingPrice.get();
    }

    public void setRetailBillingPrice(SimpleDoubleProperty retailBillingPrice) {
        this.retailBillingPrice = retailBillingPrice;
    }

    public Double getWholesaleBillingPrice() {
        return wholesaleBillingPrice.get();
    }

    public void setWholesaleBillingPrice(SimpleDoubleProperty wholesaleBillingPrice) {
        this.wholesaleBillingPrice = wholesaleBillingPrice;
    }

    public Double getMrp() {
        return mrp.get();
    }

    public void setMrp(SimpleDoubleProperty mrp) {
        this.mrp = mrp;
    }

    public Double getActualPrice() {
        return actualPrice.get();
    }

    public void setActualPrice(SimpleDoubleProperty actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Integer getBillingTypeId() {
        return billingTypeId.get();
    }

    public void setBillingTypeId(SimpleIntegerProperty billingTypeId) {
        this.billingTypeId = billingTypeId;
    }

    public String getItemName() {
        return itemName.get();
    }

    public String getBarcode() {
        return barcode.get();
    }

    public void setBarcode(SimpleStringProperty barcode) {
        this.barcode = barcode;
    }

    public double getWeight() {
        return weight.get();
    }

    public void setWeight(SimpleDoubleProperty weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return uom.get();
    }

    public void setUom(SimpleStringProperty uom) {
        this.uom = uom;
    }

    public Integer getIdPk() {
        return idPk.get();
    }

    public void setIdPk(SimpleIntegerProperty idPk) {
        this.idPk = idPk;
    }

    public void setItemName(SimpleStringProperty itemName) {
        this.itemName = itemName;
    }

    public Double getWholesalePrice() {
        return wholesalePrice.get();
    }

    public void setWholesalePrice(SimpleDoubleProperty wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public Double getRetailPrice() {
        return retailPrice.get();
    }

    public void setRetailPrice(SimpleDoubleProperty retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getTax() {
        return tax.get();
    }

    public void setTax(SimpleDoubleProperty tax) {
        this.tax = tax;
    }

    public Boolean getHasFree() {
        return hasFree.get();
    }

    public void setHasFree(SimpleBooleanProperty hasFree) {
        this.hasFree = hasFree;
    }
    
    
    
}
