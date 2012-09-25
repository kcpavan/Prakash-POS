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
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Prakash
 */
public class InvoiceDetailsDo {

    //private ItemDo items;
    private SimpleStringProperty itemName = new SimpleStringProperty();
    private SimpleStringProperty barcode = new SimpleStringProperty();
    private SimpleDoubleProperty mrp = new SimpleDoubleProperty();
    //private BillingPriceDo billingPrice;
    private SimpleDoubleProperty billingPrice=new SimpleDoubleProperty();
    private SimpleIntegerProperty quantity = new SimpleIntegerProperty();
    private SimpleDoubleProperty total = new SimpleDoubleProperty();
    
     public InvoiceDetailsDo(InvoiceDetails invoiceDetails) {
       //this.itemName.set(invoiceDetails.getItems().getItemName());
         this.itemName.set(invoiceDetails.getItemDetails().getItem().getItemName());
       this.barcode.set(invoiceDetails.getItemDetails().getItem().getBarcode());
       //this.mrp.set(invoiceDetails.getItems().getMrp());
       this.billingPrice.set(invoiceDetails.getItemDetails().getBillingPrice());
       this.quantity.set(invoiceDetails.getQuantity());
       this.total.set(invoiceDetails.getTotal());
       
    }
     
    public InvoiceDetailsDo getInvoceDo(InvoiceDetails invoiceDetails)
     {
       this.itemName.set(invoiceDetails.getItemDetails().getItem().getItemName());
       this.barcode.set(invoiceDetails.getItemDetails().getItem().getBarcode());
//       this.mrp.set(invoiceDetails.getItems().getMrp());
       this.billingPrice.set(invoiceDetails.getItemDetails().getBillingPrice());
       this.quantity.set(invoiceDetails.getQuantity());
       this.total.set(invoiceDetails.getTotal());
       return this;
     }

    /*
    public ItemDo getItems() {
        return items;
    }

    public void setItems(ItemDo items) {
        this.items = items;
    }
*/
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

    public InvoiceDetailsDo() {
    }

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(SimpleStringProperty itemName) {
        this.itemName = itemName;
    }

    public String getBarcode() {
        return barcode.get();
    }

    public void setBarcode(SimpleStringProperty barcode) {
        this.barcode = barcode;
    }

    public Double getMrp() {
        return mrp.get();
    }

    public void setMrp(SimpleDoubleProperty mrp) {
        this.mrp = mrp;
    }

    public Double getBillingPrice() {
        return billingPrice.get();
    }

    public void setBillingPrice(SimpleDoubleProperty billingPrice) {
        this.billingPrice = billingPrice;
    }

   

   

    

    /*public InvoiceDetailsDo(InvoiceDetails invoiceDetails) {
        Items item = invoiceDetails.getItems();
        this.setItems(new ItemDo(item));
        //this.setItems(new ItemDo());
        //this.setBillingPrice(new BillingPriceDo(invoiceDetails.getBillingPrice()));
        this.setBillingPrice(new BillingPriceDo());

        this.quantity.set(invoiceDetails.getQuantity());
        this.total.set(invoiceDetails.getTotal());
    }*/

  
}
