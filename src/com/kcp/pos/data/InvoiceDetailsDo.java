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
    private SimpleIntegerProperty invoiceDetailsId = new SimpleIntegerProperty();
    private SimpleIntegerProperty invoiceId = new SimpleIntegerProperty();
    private SimpleStringProperty itemName = new SimpleStringProperty();
    private SimpleStringProperty barcode = new SimpleStringProperty();
    private SimpleDoubleProperty mrp = new SimpleDoubleProperty();
    //private BillingPriceDo billingPrice;
    private SimpleDoubleProperty billingPrice=new SimpleDoubleProperty();
    private SimpleDoubleProperty quantity = new SimpleDoubleProperty();
    private SimpleDoubleProperty total = new SimpleDoubleProperty();
    private SimpleDoubleProperty margin = new SimpleDoubleProperty();
    
     public InvoiceDetailsDo(InvoiceDetails invoiceDetails) {
       //this.itemName.set(invoiceDetails.getItems().getItemName());
         this.invoiceDetailsId.set(invoiceDetails.getIdPk());
         this.invoiceId.set(invoiceDetails.getInvoice().getIdPk());
         this.itemName.set(invoiceDetails.getItemDetails().getItem().getItemName());
       this.barcode.set(invoiceDetails.getItemDetails().getItem().getBarcode());
       //this.mrp.set(invoiceDetails.getItems().getMrp());
       this.billingPrice.set(invoiceDetails.getItemDetails().getRetailBillingPrice());
       this.quantity.set(invoiceDetails.getQuantity());
       this.total.set(invoiceDetails.getTotal());
       this.margin.set(invoiceDetails.getMargin());
    }
     
    public InvoiceDetailsDo getInvoceDo(InvoiceDetails invoiceDetails)
     {
       this.itemName.set(invoiceDetails.getItemDetails().getItem().getItemName());
       this.barcode.set(invoiceDetails.getItemDetails().getItem().getBarcode());
//       this.mrp.set(invoiceDetails.getItems().getMrp());
       this.billingPrice.set(invoiceDetails.getItemDetails().getRetailBillingPrice());
       this.quantity.set(invoiceDetails.getQuantity());
       this.total.set(invoiceDetails.getTotal());
       this.margin.set(invoiceDetails.getMargin());
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
    public Double getQuantity() {
        return quantity.get();
    }

    public void setQuantity(Double quantity) {
        this.quantity.set(quantity);
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

    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
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

    public Integer getInvoiceDetailsId() {
        return invoiceDetailsId.get();
    }

    public void setInvoiceDetailsId(SimpleIntegerProperty invoiceDetailsId) {
        this.invoiceDetailsId = invoiceDetailsId;
    }

    public Integer getInvoiceId() {
        return invoiceId.get();
    }

    public void setInvoiceId(SimpleIntegerProperty invoiceId) {
        this.invoiceId = invoiceId;
    }

   

    public void setBarcode(SimpleStringProperty barcode) {
        this.barcode = barcode;
    }

   

    public void setQuantity(SimpleDoubleProperty quantity) {
        this.quantity = quantity;
    }

    public Double getMargin() {
        return margin.get();
    }

    public void setMargin(Double margin) {
        this.margin.set(margin);
    }

    
    
  
}
