/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.modal;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
    @NamedQuery(name = "ItemDetails.findById", query = "SELECT i FROM ItemDetails i where i.idPk=:id"),
    
    @NamedQuery(name = "ItemDetails.findByItemIdBillingType", query = "SELECT i FROM ItemDetails i "
        + "where i.item.idPk=:id and i.billingType.idPk=:billingType")
})
    

public class ItemDetails implements Serializable{

    private Integer idPk;
    private Users users;
    private Items item;
    private double mrp;
    private double actualPrice;
    private double billingPrice;
    private BillingType billingType;
    private Date modifiedDate;

    public ItemDetails(Integer idPk, Users users, Items items, double mrp, double actualPrice, double billingPrice, BillingType billingType, Date modifiedDate) {
        this.idPk = idPk;
        this.users = users;
        this.item = items;
        this.mrp = mrp;
        this.actualPrice = actualPrice;
        this.billingPrice = billingPrice;
        this.billingType = billingType;
        this.modifiedDate = modifiedDate;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id_fk", nullable = false)

    public Items getItem() {
        return item;
    }

    public void setItem(Items items) {
        this.item = items;
    }

    @Column(name = "billing_price", nullable = false, length = 19)
    public double getBillingPrice() {
        return billingPrice;
    }

    public void setBillingPrice(double billingPrice) {
        this.billingPrice = billingPrice;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_type_id_fk", nullable = false)
    public BillingType getBillingType() {
        return billingType;
    }

    public void setBillingType(BillingType billingType) {
        this.billingType = billingType;
    }
    
    
    
    
}
