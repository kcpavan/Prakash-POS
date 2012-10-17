/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.modal;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Prakash
 */
@Entity
@Table(name = "item_details", catalog = "storedb")
@NamedQueries({
    @NamedQuery(name = "ItemDetails.findAll", query = "SELECT i FROM ItemDetails i"),
    @NamedQuery(name = "ItemDetails.findById", 
        query = "SELECT i FROM ItemDetails i where i.idPk=:id"),
    @NamedQuery(name = "ItemDetails.findAllById", query = "SELECT i FROM ItemDetails i where i.idPk=:id"),
    @NamedQuery(name = "ItemDetails.findByItemId", query = "SELECT i FROM ItemDetails i where i.item.idPk=:id"
        + " and i.enabled=true"),
    
    
    @NamedQuery(name = "ItemDetails.findByItemIdBillingType", query = "SELECT i FROM ItemDetails i "
        + "where i.item.idPk=:id and i.billingType.idPk=:type"),
    @NamedQuery(name = "ItemDetails.setDisabled", query = "update ItemDetails i "
        + " set enabled=false "
        + " where i.item.idPk=:id ")
})
    

public class ItemDetails implements Serializable{

    private Integer idPk;
    private Users users;
    private Items item;
    private double weight;
    private UOM uom;

    private double mrp;
    private double actualPrice;
    
    private double retailBillingPrice;
    private double wholesaleBillingPrice;
    private double tax;
    private double margin;
    
    private BillingType billingType;
   // private UOM uom;
    //private double actualPrice;
    //private double sellingPrice;
    private boolean hasfree;
    private Date modifiedDate;
    private Boolean enabled;
    private static final Logger LOG = Logger.getLogger(ItemDetails.class.getName());

    public ItemDetails(Integer idPk, Users users, Items item, double weight, UOM uom, double mrp, double actualPrice, double retailBillingPrice, double wholesaleBillingPrice, double tax, double margin, BillingType billingType, boolean hasfree, Date modifiedDate, Boolean enabled) {
        this.idPk = idPk;
        this.users = users;
        this.item = item;
        this.weight = weight;
        this.uom = uom;
        this.mrp = mrp;
        this.actualPrice = actualPrice;
        this.retailBillingPrice = retailBillingPrice;
        this.wholesaleBillingPrice = wholesaleBillingPrice;
        this.tax = tax;
        this.margin = margin;
        this.billingType = billingType;
        this.hasfree = hasfree;
        this.modifiedDate = modifiedDate;
        this.enabled = enabled;
    }

  
   
    
     public ItemDetails(ItemDetails itemDetails)
     {
       //  this.idPk = itemDetails.idPk;
        this.users = itemDetails.users;
        this.item = itemDetails.item;
        this.mrp = itemDetails.mrp;
        this.actualPrice = itemDetails.actualPrice;
        this.retailBillingPrice = itemDetails.retailBillingPrice;
        this.wholesaleBillingPrice = itemDetails.wholesaleBillingPrice;
        this.tax = itemDetails.tax;
        this.billingType = itemDetails.billingType;
        this.hasfree = itemDetails.hasfree;
        this.modifiedDate = itemDetails.modifiedDate;
        this.enabled = itemDetails.enabled;
        this.uom=itemDetails.uom;
        this.weight=itemDetails.weight;
        this.margin=itemDetails.margin;
     }

    @Column(name = "tax", nullable = true, length = 19)
    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

   
    
    

    

    

    public ItemDetails() {
    }

    
   

    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_pk", unique = true, nullable = false)
    public Integer getIdPk() {
        return idPk;
    }

    public void setIdPk(Integer idPk) {
        this.idPk = idPk;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by", nullable = false)
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    @Column(name = "actual_price", nullable = false, length = 19)
    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date", nullable = false, length = 19)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id_fk", nullable = false)

    public Items getItem() {
        return item;
    }

    public void setItem(Items items) {
        this.item = items;
    }

    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_type_id_fk", nullable = false)
    public BillingType getBillingType() {
        return billingType;
    }

    public void setBillingType(BillingType billingType) {
        this.billingType = billingType;
    }

    
    @Column(name = "enabled", nullable = false)
    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "retail_billing_price", nullable = false, length = 19)
    public double getRetailBillingPrice() {
        return retailBillingPrice;
    }

    public void setRetailBillingPrice(double retailBillingPrice) {
        this.retailBillingPrice = retailBillingPrice;
    }

    @Column(name = "wholesale_billing_price", nullable = false, length = 19)
    public double getWholesaleBillingPrice() {
        return wholesaleBillingPrice;
    }

    public void setWholesaleBillingPrice(double wholesaleBillingPrice) {
        this.wholesaleBillingPrice = wholesaleBillingPrice;
    }
    
   /* @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uom_id_fk")
    public UOM getUom() {
        return this.uom;
    }

    public void setUom(UOM uom) {
        this.uom = uom;
    }*/
    
     @Column(name = "hasfree", nullable = true)
    public boolean isHasfree() {
        return this.hasfree;
    }

    public void setHasfree(boolean hasfree) {
        this.hasfree = hasfree;
    }

     @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uom_id_fk")
    public UOM getUom() {
        return this.uom;
    }

    public void setUom(UOM uom) {
        this.uom = uom;
    }
    
    @Column(name = "weight", nullable = false, precision = 22, scale = 0)
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    @Column(name = "margin", nullable = false, precision = 22, scale = 0)
    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }
   
    
}
