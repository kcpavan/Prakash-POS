/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.data;

import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.InvoiceDetails;
import java.util.HashSet;
import java.util.Set;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 *
 * @author Prakash
 */
public class InvoiceDo {
    
    
	
	private SimpleIntegerProperty totalItems;
	private SimpleDoubleProperty totalAmount;
        private Set<InvoiceDetails> invoiceDetailses = new HashSet<InvoiceDetails>(0);

    public Integer getTotalItems() {
        return totalItems.get();
    }

    public void setTotalItems(SimpleIntegerProperty totalItems) {
        this.totalItems = totalItems;
    }

    public Double getTotalAmount() {
        return totalAmount.get();
    }

    public void setTotalAmount(SimpleDoubleProperty totalAmount) {
        this.totalAmount = totalAmount;
    }
        
        
	public InvoiceDo(Invoice invoice)
        {
            this.totalAmount.set(invoice.getTotalAmount());
            this.totalItems.set(invoice.getTotalItems());
            
        }
	
        
        
    
}
