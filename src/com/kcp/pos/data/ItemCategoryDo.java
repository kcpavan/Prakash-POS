/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.data;

import com.kcp.pos.modal.ItemCategory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Prakash
 */
public class ItemCategoryDo {
    private SimpleIntegerProperty idPk=new SimpleIntegerProperty();
	private SimpleStringProperty itemCategory=new SimpleStringProperty();

    public Integer getIdPk() {
        return idPk.get();
    }

    public void setIdPk(SimpleIntegerProperty idPk) {
        this.idPk = idPk;
    }

    public String getItemName() {
        return itemCategory.get();
    }

    public void setItemName(SimpleStringProperty itemName) {
        this.itemCategory = itemName;
    }

    public ItemCategoryDo(ItemCategory itemCategory) {
        this.idPk.set(itemCategory.getIdPk());
        this.itemCategory.set(itemCategory.getCategoryName());
    }
        
        
    
    
}
