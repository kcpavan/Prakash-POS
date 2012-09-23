/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.data;

import com.kcp.pos.modal.Items;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Prakash
 */
public class ItemDo {
        
        private SimpleIntegerProperty idPk=new SimpleIntegerProperty();
	private SimpleStringProperty itemName=new SimpleStringProperty();
	private SimpleStringProperty barcode=new SimpleStringProperty();
	private SimpleDoubleProperty mrp=new SimpleDoubleProperty();
	private SimpleDoubleProperty weight=new SimpleDoubleProperty();
	private SimpleStringProperty weightUnit=new SimpleStringProperty();
	private SimpleDoubleProperty actualPrice=new SimpleDoubleProperty();
	private SimpleDoubleProperty sellingPrice=new SimpleDoubleProperty();

    public ItemDo(Items items) {
        this.idPk.set(items.getIdPk());
        this.itemName.set(items.getItemName());
        this.barcode.set(items.getBarcode());
        this.mrp.set(items.getMrp());
        this.weight.set(items.getWeight());
        this.weightUnit.set(items.getWeightUnit());
        this.actualPrice.set(items.getActualPrice());
       // this.sellingPrice.set(items.getSellingPrice());
        
                
    }

    public ItemDo()
    {
        
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

    public double getMrp() {
        return mrp.get();
    }

    public void setMrp(SimpleDoubleProperty mrp) {
        this.mrp = mrp;
    }

    public double getWeight() {
        return weight.get();
    }

    public void setWeight(SimpleDoubleProperty weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit.get();
    }

    public void setWeightUnit(SimpleStringProperty weightUnit) {
        this.weightUnit = weightUnit;
    }

    public double getActualPrice() {
        return actualPrice.get();
    }

    public void setActualPrice(SimpleDoubleProperty actualPrice) {
        this.actualPrice = actualPrice;
    }

    public double getSellingPrice() {
        return sellingPrice.get();
    }

    public void setSellingPrice(SimpleDoubleProperty sellingPrice) {
        this.sellingPrice = sellingPrice;
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
        
        
        
 
}
