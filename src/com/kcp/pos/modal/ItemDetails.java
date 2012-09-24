/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.modal;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Prakash
 */
public class ItemDetails {

    private Integer idPk;
    private Users users;
    private Items items;
    private double mrp;
    private double actualPrice;
    private double billingPrice;
    private double billingType;
    
    private Date modifiedDate;

    public ItemDetails(Integer idPk, Users users, Items items, double mrp, double actualPrice, Date modifiedDate) {
        this.idPk = idPk;
        this.users = users;
        this.items = items;
        this.mrp = mrp;
        this.actualPrice = actualPrice;
        this.modifiedDate = modifiedDate;
    }

   

    
    
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
    
    
}
