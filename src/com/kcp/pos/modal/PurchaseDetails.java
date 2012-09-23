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
    @NamedQuery(name = "PurchaseDetails.findAllById", query = "SELECT c FROM PurchaseDetails c WHERE c.purchase.idPk = :id")
   
    
})
public class PurchaseDetails implements java.io.Serializable {

    private Integer idPk;
    private Items items;
    private Purchase purchase;
    private Double mrp;
    private Integer caseQuantity;
    private Integer unitsQuantity;
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

    public PurchaseDetails(Integer idPk, Items items, Purchase purchase, Double mrp, Integer caseQuantity, Integer unitsQuantity, Integer freeUnits, Double basicRate, Double grossAmount, Integer scheme, Double cd, Double taxPercentage, Double tax, Double netAmount) {
        this.idPk = idPk;
        this.items = items;
        this.purchase = purchase;
        this.mrp = mrp;
        this.caseQuantity = caseQuantity;
        this.unitsQuantity = unitsQuantity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id_fk", nullable = false)
    public Items getItems() {
        return this.items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    @ManyToOne(fetch = FetchType.LAZY)
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

    public Integer getCaseQuantity() {
        return caseQuantity;
    }

    public void setCaseQuantity(Integer caseQuantity) {
        this.caseQuantity = caseQuantity;
    }

    public Integer getUnitsQuantity() {
        return unitsQuantity;
    }

    public void setUnitsQuantity(Integer unitsQuantity) {
        this.unitsQuantity = unitsQuantity;
    }

    public Integer getFreeUnits() {
        return freeUnits;
    }

    public void setFreeUnits(Integer freeUnits) {
        this.freeUnits = freeUnits;
    }

    public Double getBasicRate() {
        return basicRate;
    }

    public void setBasicRate(Double basicRate) {
        this.basicRate = basicRate;
    }

    public Double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(Double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public Integer getScheme() {
        return scheme;
    }

    public void setScheme(Integer scheme) {
        this.scheme = scheme;
    }

    public Double getCd() {
        return cd;
    }

    public void setCd(Double cd) {
        this.cd = cd;
    }

    public Double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }


    
}
