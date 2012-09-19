/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.data;

import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.Items;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Prakash
 */
public class InvoiceDetailsDo {
    
    
	private ItemDo items;
	private SimpleIntegerProperty quantity;
	private SimpleDoubleProperty total;

    public ItemDo getItems() {
        return items;
    }

    public void setItems(ItemDo items) {
        this.items = items;
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public void setQuantity(SimpleIntegerProperty quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total.get();
    }

    public void setTotal(SimpleDoubleProperty total) {
        this.total = total;
    }

    public InvoiceDetailsDo(Items item, SimpleIntegerProperty quantity, SimpleDoubleProperty total) {
        this.setItems(new ItemDo(item));
        this.items = items;
        this.quantity = quantity;
        this.total = total;
    }
    
    
    public InvoiceDetailsDo(InvoiceDetails invoiceDetails) {
        Items item=invoiceDetails.getItems();
        
        
        this.setItems(new ItemDo(item));
        
        this.quantity.set(invoiceDetails.getQuantity());
        this.total.set(invoiceDetails.getTotal());
    }
    
        
        
        
        
        
        
        
    
}
