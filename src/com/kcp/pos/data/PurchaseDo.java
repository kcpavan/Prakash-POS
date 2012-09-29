/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.data;

import com.kcp.pos.modal.Distributor;
import com.kcp.pos.modal.Purchase;
import com.kcp.pos.modal.PurchaseDetails;
import com.kcp.pos.modal.Users;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Prakash
 */
public class PurchaseDo {
    
    private SimpleIntegerProperty idPk;
	private SimpleIntegerProperty distributor;
	private SimpleIntegerProperty users;
	private SimpleIntegerProperty purchaseNumber;
	private SimpleStringProperty receivedDate;
	private SimpleStringProperty modifiedDate;
	private Set<PurchaseDetails> purchaseDetailses = new HashSet<PurchaseDetails>(0);

    public Integer getIdPk() {
        return idPk.get();
    }

    public void setIdPk(SimpleIntegerProperty idPk) {
        this.idPk = idPk;
    }

    public Integer getDistributor() {
        return distributor.get();
    }

    public void setDistributor(SimpleIntegerProperty distributor) {
        this.distributor = distributor;
    }

    public Integer getUsers() {
        return users.get();
    }

    public void setUsers(SimpleIntegerProperty users) {
        this.users = users;
    }

    public Integer getPurchaseNumber() {
        return purchaseNumber.get();
    }

    public void setPurchaseNumber(SimpleIntegerProperty purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public String getReceivedDate() {
        return receivedDate.get();
    }

    public void setReceivedDate(SimpleStringProperty receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getModifiedDate() {
        return modifiedDate.get();
    }

    public void setModifiedDate(SimpleStringProperty modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<PurchaseDetails> getPurchaseDetailses() {
        return purchaseDetailses;
    }

    public void setPurchaseDetailses(Set<PurchaseDetails> purchaseDetailses) {
        this.purchaseDetailses = purchaseDetailses;
    }

    public PurchaseDo(Purchase purchase) {
        this.idPk.set(purchase.getIdPk());
        this.distributor.set(purchase.getDistributor().getIdPk());
        this.users.set(purchase.getUsers().getIdPk());
        this.purchaseNumber.set(purchase.getPurchaseNumber());
      //  this.receivedDate.set(purchase.getReceivedDate().toString());
        this.modifiedDate.set(purchase.getModifiedDate().toString());
    }
        
        
        
        
        
    
}
