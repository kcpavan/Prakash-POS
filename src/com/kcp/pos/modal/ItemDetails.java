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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Prakash
 */
@Entity
@Table(name = "items", catalog = "storedb")
@NamedQueries({
    @NamedQuery(name = "ItemDetails.findAll", query = "SELECT i FROM ItemDetails i"),
    @NamedQuery(name = "ItemDetails.findById", query = "SELECT i FROM ItemDetails i where i.itemName=:name"),
    @NamedQuery(name = "ItemDetails.findByItemId", query = "SELECT i FROM ItemDetails i "
        + "where i.items.idPk=:id"),
})
    

public class ItemDetails implements Serializable{

    private Integer idPk;
    private Users users;
    private Items items;
    private double mrp;
    private double actualPrice;
    private double billingPrice;
    private double billingType;
    private Date modifiedDate;

    public ItemDetails(Integer idPk, Users users, Items items, double mrp, double actualPrice, double billingPrice, double billingType, Date modifiedDate) {
        this.idPk = idPk;
        this.users = users;
        this.items = items;
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

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public double getBillingPrice() {
        return billingPrice;
    }

    public void setBillingPrice(double billingPrice) {
        this.billingPrice = billingPrice;
    }

    public double getBillingType() {
        return billingType;
    }

    public void setBillingType(double billingType) {
        this.billingType = billingType;
    }
    
    
    
    
}
