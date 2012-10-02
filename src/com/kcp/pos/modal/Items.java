package com.kcp.pos.modal;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "items", catalog = "storedb")
@NamedQueries({
    @NamedQuery(name = "Items.findAll", query = "SELECT i FROM Items i"),
    @NamedQuery(name = "Items.findByName", query = "SELECT i FROM Items i where i.itemName=:name"),})
public class Items implements java.io.Serializable {

    private Integer idPk;
    private ItemCategory itemCategory;
    //private ItemDetails itemDetails;
    //private BillingPrice billingPrice;
    private Users users;
    private String itemName;
    private String barcode;
    //private double mrp;
    private double weight;
    private UOM uom;
    //private double actualPrice;
    //private double sellingPrice;
    private boolean hasfree;
    private Date modifiedDate;
    //private Set<Stocks> stockses = new HashSet<Stocks>(0);
    //private Set<PurchaseDetails> purchaseDetailses = new HashSet<PurchaseDetails>(0);
    //private Set<InvoiceDetails> invoiceDetailses = new HashSet<InvoiceDetails>(0);

    public Items() {
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
    @JoinColumn(name = "category_id_fk")
    public ItemCategory getItemCategory() {
        return this.itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by", nullable = false)
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Column(name = "item_name", nullable = false, length = 250)
    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Column(name = "barcode", nullable = false, length = 250)
    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /*@Column(name = "mrp", nullable = false, precision = 22, scale = 0)
     public double getMrp() {
     return this.mrp;
     }

     public void setMrp(double mrp) {
     this.mrp = mrp;
     }*/
    @Column(name = "weight", nullable = false, precision = 22, scale = 0)
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uom_id_fk")
    public UOM getUom() {
        return this.uom;
    }

    public void setUom(UOM uom) {
        this.uom = uom;
    }

    /*@Column(name = "actual_price", nullable = false, precision = 22, scale = 0)
     public double getActualPrice() {
     return this.actualPrice;
     }

     public void setActualPrice(double actualPrice) {
     this.actualPrice = actualPrice;
     }*/

    /*@Column(name = "selling_price", nullable = false, precision = 22, scale = 0)
     public double getSellingPrice() {
     return this.sellingPrice;
     }

     public void setSellingPrice(double sellingPrice) {
     this.sellingPrice = sellingPrice;
     }*/
    @Column(name = "hasfree", nullable = false)
    public boolean isHasfree() {
        return this.hasfree;
    }

    public void setHasfree(boolean hasfree) {
        this.hasfree = hasfree;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date", nullable = false, length = 19)
    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

   /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "items")
    public Set<Stocks> getStockses() {
        return this.stockses;
    }

    public void setStockses(Set<Stocks> stockses) {
        this.stockses = stockses;
    }*/

  /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "items")
    public Set<PurchaseDetails> getPurchaseDetailses() {
        return this.purchaseDetailses;
    }

    public void setPurchaseDetailses(Set<PurchaseDetails> purchaseDetailses) {
        this.purchaseDetailses = purchaseDetailses;
    }*/
    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "items")
    public Set<InvoiceDetails> getInvoiceDetailses() {
        return this.invoiceDetailses;
    }

    public void setInvoiceDetailses(Set<InvoiceDetails> invoiceDetailses) {
        this.invoiceDetailses = invoiceDetailses;
    }*/
}
