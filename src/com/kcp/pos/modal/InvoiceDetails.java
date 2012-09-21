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
    @NamedQuery(name = "InvoiceDetails.findAllById", query = "SELECT c FROM InvoiceDetails c WHERE c.invoice.idPk = :id")
})
public class InvoiceDetails implements java.io.Serializable {

    private Integer idPk;
    private Invoice invoice;
    private Items items;
    private BillingPrice billingPrice;
    private Integer quantity;
    private double total;

    public InvoiceDetails() {
    }

    public InvoiceDetails(Invoice invoice, Items items, Integer quantity,
            double total) {
        this.invoice = invoice;
        this.items = items;
        this.quantity = quantity;
        this.total = total;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id_fk", nullable = false)
    public Items getItems() {
        return this.items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billingprice_id_fk", nullable = false)
    public BillingPrice getBillingPrice() {
        return billingPrice;
    }

    public void setBillingPrice(BillingPrice billingPrice) {
        this.billingPrice = billingPrice;
    }

    @Column(name = "quantity", nullable = false, precision = 22, scale = 0)
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "total", nullable = false, precision = 22, scale = 0)
    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
