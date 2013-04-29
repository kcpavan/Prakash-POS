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
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Column;

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
    //private SimpleStringProperty hasFree = new SimpleStringProperty();
    private SimpleBooleanProperty hasFree = new SimpleBooleanProperty();
    private SimpleDoubleProperty retailPrice = new SimpleDoubleProperty();
    private SimpleIntegerProperty itemId = new SimpleIntegerProperty();
    private SimpleDoubleProperty startRange1 = new SimpleDoubleProperty();
    private SimpleDoubleProperty endRange1 = new SimpleDoubleProperty();
    private SimpleDoubleProperty billingPrice1 = new SimpleDoubleProperty();
    private SimpleDoubleProperty startRange2 = new SimpleDoubleProperty();
    private SimpleDoubleProperty endRange2 = new SimpleDoubleProperty();
    private SimpleDoubleProperty billingPrice2 = new SimpleDoubleProperty();
    private SimpleDoubleProperty startRange3 = new SimpleDoubleProperty();
    private SimpleDoubleProperty endRange3 = new SimpleDoubleProperty();
    private SimpleDoubleProperty billingPrice3 = new SimpleDoubleProperty();
    //private SimpleDoubleProperty retailBillingPrice = new SimpleDoubleProperty();
    //private SimpleDoubleProperty wholesaleBillingPrice = new SimpleDoubleProperty();
    private SimpleIntegerProperty billingTypeId = new SimpleIntegerProperty();
    private SimpleBooleanProperty enabled =  new SimpleBooleanProperty();
    private SimpleDoubleProperty margin = new SimpleDoubleProperty();

    public ItemDetailsDo(ItemDetails itemDetails) {
        this.idPk.set(itemDetails.getIdPk());
        this.itemName.set(itemDetails.getItem().getItemName());
        this.barcode.set(itemDetails.getItem().getBarcode());
//        this.mrp.set(items.getMrp());
        this.weight.set(itemDetails.getWeight());
        this.uom.set(itemDetails.getUom().getUomDesc());
        this.itemId.set(itemDetails.getItem().getIdPk());
       // this.startRange1.set(itemDetails.getStartRange());
        // this.endRange1.set(itemDetails.getEndRange());
        //this.retailBillingPrice.set(itemDetails.getRetailBillingPrice());
        //this.wholesaleBillingPrice.set(itemDetails.getWholesaleBillingPrice());
        this.tax.set(itemDetails.getTax());
        this.mrp.set(itemDetails.getMrp());
        this.actualPrice.set(itemDetails.getActualPrice());
        this.hasFree.set(itemDetails.getHasfree());
        this.enabled.set(itemDetails.isEnabled());
        this.margin.set(itemDetails.getMargin());
        


        /*this.startRange2.set(retailBillingPrice.getStartRange2());
         this.endRange2.set(retailBillingPrice.getEndRange2()) ;
         this.billingPrice2.set(retailBillingPrice.getBillingPrice2());
         this.startRange3.set(retailBillingPrice.getStartRange3() );
         this.endRange3.set(retailBillingPrice.getEndRange3() );
         this.billingPrice3.set(retailBillingPrice.getBillingPrice3() );*/
    }

    public ItemDetailsDo(ItemDetails itemDetails,List<BillingPrice> billingPriceList) {
        this.idPk.set(itemDetails.getIdPk());
        this.itemName.set(itemDetails.getItem().getItemName());
        this.barcode.set(itemDetails.getItem().getBarcode());
//        this.mrp.set(items.getMrp());
        this.weight.set(itemDetails.getWeight());
        this.uom.set(itemDetails.getUom().getUomDesc());
        this.itemId.set(itemDetails.getItem().getIdPk());
        
        //this.retailBillingPrice.set(itemDetails.getRetailBillingPrice());
        //this.wholesaleBillingPrice.set(itemDetails.getWholesaleBillingPrice());
        this.tax.set(itemDetails.getTax());
        this.mrp.set(itemDetails.getMrp());
        this.actualPrice.set(itemDetails.getActualPrice());
        this.hasFree.set(itemDetails.getHasfree());
        this.enabled.set(itemDetails.isEnabled());
        this.margin.set(itemDetails.getMargin());
        
        int i=0;
        for(BillingPrice billingPrice:billingPriceList)
        {
            if(i==0)
            {
                this.startRange1.set(billingPrice.getStartRange());
                this.endRange1.set(billingPrice.getEndRange());
                this.billingPrice1.set(billingPrice.getBillingPrice());
            }
            else if(i==1)
            {
                this.startRange2.set(billingPrice.getStartRange());
                this.endRange2.set(billingPrice.getEndRange());
                this.billingPrice2.set(billingPrice.getBillingPrice());
            }
            if(i==2)
            {
                this.startRange3.set(billingPrice.getStartRange());
                this.endRange3.set(billingPrice.getEndRange());
                this.billingPrice3.set(billingPrice.getBillingPrice());
            }
            i++;
        }
        
        /*this.startRange1.set(billingPrice.getStartRange());
        this.endRange1.set(billingPrice.getEndRange());
        this.startRange2.set(retailBillingPrice.getStartRange2());
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

    public Double getStartRange1() {
     return startRange1.get();
     }

     public void setStartRange1(SimpleDoubleProperty startRange1) {
     this.startRange1 = startRange1;
     }

     public Double getEndRange1() {
     return endRange1.get();
     }

     public void setEndRange1(SimpleDoubleProperty endRange1) {
     this.endRange1 = endRange1;
     }
    
    public void setBillingPrice1(SimpleDoubleProperty billingPrice1) {
     this.billingPrice1 = billingPrice1;
     }

    public Double getBillingPrice1() {
     return billingPrice1.get();
     }
    
     public Double getStartRange2() {
     return startRange2.get();
     }

     public void setStartRange2(SimpleDoubleProperty startRange2) {
     this.startRange2 = startRange2;
     }

     public Double getEndRange2() {
     return endRange2.get();
     }

     public void setEndRange2(SimpleDoubleProperty endRange2) {
     this.endRange2 = endRange2;
     }

     public Double getBillingPrice2() {
     return billingPrice2.get();
     }

     public void setBillingPrice2(SimpleDoubleProperty billingPrice2) {
     this.billingPrice2 = billingPrice2;
     }

     public Double getStartRange3() {
     return startRange3.get();
     }

     public void setStartRange3(SimpleDoubleProperty startRange3) {
     this.startRange3 = startRange3;
     }

     public Double getEndRange3() {
     return endRange3.get();
     }

     public void setEndRange3(SimpleDoubleProperty endRange3) {
     this.endRange3 = endRange3;
     }

     public Double getBillingPrice3() {
     return billingPrice3.get();
     }

     public void setBillingPrice3(SimpleDoubleProperty billingPrice3) {
     this.billingPrice3 = billingPrice3;
     }
    public Integer getItemId() {
        return itemId.get();
    }

    public void setItemId(SimpleIntegerProperty itemId) {
        this.itemId = itemId;
    }

//    public Double getRetailBillingPrice() {
//        return retailBillingPrice.get();
//    }
//
//    public void setRetailBillingPrice(SimpleDoubleProperty retailBillingPrice) {
//        this.retailBillingPrice = retailBillingPrice;
//    }
//
//    public Double getWholesaleBillingPrice() {
//        return wholesaleBillingPrice.get();
//    }
//
//    public void setWholesaleBillingPrice(SimpleDoubleProperty wholesaleBillingPrice) {
//        this.wholesaleBillingPrice = wholesaleBillingPrice;
//    }

    public Double getMrp() {
        return mrp.get();
    }

    public void setMrp(Double mrp) {
        this.mrp.set(mrp);
    }

    public Double getActualPrice() {
        return actualPrice.get();
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice.set(actualPrice);
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

    public void setWeight(Double weight) {
        this.weight.set(weight);
    }

    public String getWeightUnit() {
        return uom.get();
    }

    public void setUom(String uom) {
        this.uom.set(uom);
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

    public void setWholesalePrice(Double wholesalePrice) {
        this.wholesalePrice.set(wholesalePrice);
    }

    public Double getRetailPrice() {
        return retailPrice.get();
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice.set(retailPrice);
    }

    public Double getTax() {
        return tax.get();
    }

    public void setTax(Double tax) {
        this.tax.set(tax);
    }

   /* public String getHasFree() {
        return hasFree.get();
    }

    public void setHasFree(String hasFree) {
        this.hasFree.set(hasFree);
    }*/
    
    public Boolean getHasFree() {
        return hasFree.get();
    }

    public void setHasFree(Boolean hasFree) {
        this.hasFree.set(hasFree);
    }

    public Double getMargin() {
        return margin.get();
    }

    public void setMargin(Double margin) {
        this.margin.set(margin);
    }
    
   
    
}
