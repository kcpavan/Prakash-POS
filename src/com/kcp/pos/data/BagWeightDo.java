/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.data;

import com.kcp.pos.modal.BagWeight;
import com.kcp.pos.modal.BillingPrice;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author pbhimanna
 */
public class BagWeightDo {
    
    
    private SimpleIntegerProperty idPk = new SimpleIntegerProperty();
  
     
    private SimpleDoubleProperty bagWeight = new SimpleDoubleProperty();
   
  

    public BagWeightDo(BagWeight bagWeight) {
        this.idPk.set(bagWeight.getIdPk());
        this.bagWeight.set(bagWeight.getBagWeight());
        
    }

    public Double getBagWeight() {
        return bagWeight.get();
    }

    public void setBagWeight(Double bagWeight) {
        this.bagWeight.set(bagWeight);
    }


    

   
    public Integer getIdPk() {
        return idPk.get();
    }

    public void setIdPk(SimpleIntegerProperty idPk) {
        this.idPk = idPk;
    }

    
}
