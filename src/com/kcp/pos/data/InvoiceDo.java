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
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author Prakash
 */
public class InvoiceDo {
    
    
	private SimpleIntegerProperty invoiceNumber=new SimpleIntegerProperty();
	private SimpleIntegerProperty totalQuantity=new  SimpleIntegerProperty();
	private SimpleDoubleProperty totalAmount=new SimpleDoubleProperty();
        private SimpleStringProperty modifiedBy=new SimpleStringProperty();
        private SimpleStringProperty modifiedDate=new SimpleStringProperty();
        private Set<InvoiceDetails> invoiceDetailses = new HashSet<InvoiceDetails>(0);

    public Integer getTotalQuantity() {
        return totalQuantity.get();
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity.set(totalQuantity);
    }

    public Double getTotalAmount() {
        return totalAmount.get();
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount.set(totalAmount);
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber.get();
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber.set(invoiceNumber);
    }

    public String getModifiedBy() {
        return modifiedBy.get();
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy.set(modifiedBy);
    }

   
        
    
    
        
	public InvoiceDo(Invoice invoice)
        {
            this.totalAmount.set(invoice.getTotalAmount());
            this.totalQuantity.set(invoice.getTotalItems());
            this.invoiceNumber.set(invoice.getIdPk());
            this.modifiedBy.set(invoice.getUsers().getFirstName()+" "+invoice.getUsers().getLastName());
            this.modifiedDate.set(invoice.getModifiedDate().toString());
            
        }

    public String getModifiedDate() {
        return modifiedDate.get();
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate.set(modifiedDate);
    }
	
        
        
    
}
