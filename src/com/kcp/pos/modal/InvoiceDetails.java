package com.kcp.pos.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_details", catalog = "storedb")
@NamedQueries({
    @NamedQuery(name = "InvoiceDetails.findAllById", query = "SELECT c FROM InvoiceDetails c "
        + "WHERE c.invoice.idPk = :id"),
    @NamedQuery(name = "InvoiceDetails.findByInvoiceItemId", 
        query = "SELECT c FROM InvoiceDetails c "
        + "WHERE c.itemDetails.item.idPk = :itemId and c.invoice.idPk=:invoiceId"),
    @NamedQuery(name = "InvoiceDetails.findByItemId", 
        query = "SELECT c FROM InvoiceDetails c "
        + "WHERE c.itemDetails.idPk = :itemId ")
    
})
public class InvoiceDetails implements java.io.Serializable {

    private Integer idPk;
    private Invoice invoice;
   // private Items items;
    private ItemDetails itemDetails;
    private BillingPrice billingPrice;
    private Double quantity;
    private double total;
    private Double margin;
    
    public InvoiceDetails() {
    }

    public InvoiceDetails(Integer idPk, Invoice invoice, ItemDetails itemDetails, BillingPrice billingPrice, Double quantity, double total, Double margin) {
        this.idPk = idPk;
        this.invoice = invoice;
        this.itemDetails = itemDetails;
        this.billingPrice = billingPrice;
        this.quantity = quantity;
        this.total = total;
        this.margin = margin;
    }

   

   

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_pk", unique = true, nullable = false)
    public Integer getIdPk() {
        return this.idPk;
    }

    public void setIdPk(Integer idPk) {
        this.idPk = idPk;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id_fk", nullable = false)
    public Invoice getInvoice() {
        return this.invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

   /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id_fk", nullable = false)
    public Items getItems() {
        return this.items;
    }

    public void setItems(Items items) {
        this.items = items;
    }*/

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itemdetails_id_fk", nullable = false)
    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }

    
    
    
    
    /*public Item getBillingPrice() {
        return billingPrice;
    }

    public void setBillingPrice(BillingPrice billingPrice) {
        this.billingPrice = billingPrice;
    }*/

    @Column(name = "quantity", nullable = false, precision = 22, scale = 0)
    public Double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Column(name = "total", nullable = false, precision = 22, scale = 0)
    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Column(name = "margin", nullable = false, precision = 22, scale = 0)
    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billingprice_id_fk", nullable = false)
    public BillingPrice getBillingPrice() {
        return billingPrice;
    }

    public void setBillingPrice(BillingPrice billingPrice) {
        this.billingPrice = billingPrice;
    }
    
    
    
}
