package com.kcp.pos.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_details", catalog = "storedb")
@NamedQueries({
    @NamedQuery(name = "PurchaseDetails.findAllById", query = "SELECT c FROM PurchaseDetails c WHERE c.purchase.idPk = :id"),
    

})
public class PurchaseDetails implements java.io.Serializable {

    private Integer idPk;
    //private Items items;
    private ItemDetails itemDetails;
    private Purchase purchase;
    private Double mrp;
    private Integer caseQuantity;
    private Double unitsQuantity;
    private Integer unitsPerCase;
    private Integer freeUnits;
    private Double basicRate;
    private Double grossAmount;
    private Integer scheme;
    private Double cd;
    private Double taxPercentage;
    private Double tax;
    private Double netAmount;

    public PurchaseDetails() {
    }

    public PurchaseDetails(Integer idPk, ItemDetails itemDetails, Purchase purchase, Double mrp, Integer caseQuantity, Double unitsQuantity, Integer unitsPerCase, Integer freeUnits, Double basicRate, Double grossAmount, Integer scheme, Double cd, Double taxPercentage, Double tax, Double netAmount) {
        this.idPk = idPk;
        this.itemDetails = itemDetails;
        this.purchase = purchase;
        this.mrp = mrp;
        this.caseQuantity = caseQuantity;
        this.unitsQuantity = unitsQuantity;
        this.unitsPerCase = unitsPerCase;
        this.freeUnits = freeUnits;
        this.basicRate = basicRate;
        this.grossAmount = grossAmount;
        this.scheme = scheme;
        this.cd = cd;
        this.taxPercentage = taxPercentage;
        this.tax = tax;
        this.netAmount = netAmount;
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

    

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_id_fk", nullable = false)
    public Purchase getPurchase() {
        return this.purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Double getMrp() {
        return mrp;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    @Column(name = "case_quantity", nullable = false, length = 19)
    public Integer getCaseQuantity() {
        return caseQuantity;
    }

    public void setCaseQuantity(Integer caseQuantity) {
        this.caseQuantity = caseQuantity;
    }

    @Column(name = "units_quantity", nullable = false, length = 19)
    public Double getUnitsQuantity() {
        return unitsQuantity;
    }

    public void setUnitsQuantity(Double unitsQuantity) {
        this.unitsQuantity = unitsQuantity;
    }

    @Column(name = "free_quantity", nullable = false, length = 19)
    public Integer getFreeUnits() {
        return freeUnits;
    }

    public void setFreeUnits(Integer freeUnits) {
        this.freeUnits = freeUnits;
    }

    @Column(name = "basic_rate", nullable = false, length = 19)
    
    public Double getBasicRate() {
        return basicRate;
    }

    public void setBasicRate(Double basicRate) {
        this.basicRate = basicRate;
    }

    @Column(name = "gross_amount", nullable = false, length = 19)
    public Double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(Double grossAmount) {
        this.grossAmount = grossAmount;
    }

    @Column(name = "scheme", nullable = false, length = 19)
    public Integer getScheme() {
        return scheme;
    }

    public void setScheme(Integer scheme) {
        this.scheme = scheme;
    }

    @Column(name = "cd", nullable = false, length = 19)
    public Double getCd() {
        return cd;
    }

    public void setCd(Double cd) {
        this.cd = cd;
    }

    @Column(name = "tax_percentage", nullable = false, length = 19)
    public Double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    @Column(name = "tax", nullable = false, length = 19)
    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    @Column(name = "net_amount", nullable = false, length = 19)
    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_details_id_fk", nullable = false)
    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }

    @Column(name = "quantity_per_case", nullable = false, length = 19)
    public Integer getUnitsPerCase() {
        return unitsPerCase;
    }

    public void setUnitsPerCase(Integer unitsPerCase) {
        this.unitsPerCase = unitsPerCase;
    }

    
    

    
}
