/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.data;

import com.kcp.pos.modal.UOM;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Prakash
 */
public class UOMDo {
    
     private SimpleIntegerProperty idPk=new SimpleIntegerProperty();
	private SimpleStringProperty uomDesc=new SimpleStringProperty();

    public UOMDo(SimpleIntegerProperty idPk, SimpleStringProperty uomDesc) {
        this.idPk = idPk;
        this.uomDesc = uomDesc;
        
    }
    
    public UOMDo(UOM uom) {
        this.idPk.set(uom.getIdPk());
        this.uomDesc.set(uom.getUomDesc());
       
    }
   

    public Integer getIdPk() {
        return idPk.get();
    }

    public void setIdPk(SimpleIntegerProperty idPk) {
        this.idPk = idPk;
    }

    

    public String getUomUserName() {
        return uomDesc.get();
    }

    public void setUomDesc(SimpleStringProperty uomDesc) {
        this.uomDesc = uomDesc;
    }

   
      
    
        
    
}
