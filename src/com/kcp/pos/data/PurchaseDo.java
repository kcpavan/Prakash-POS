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
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Prakash
 */
public class PurchaseDo {
    
    private SimpleIntegerProperty idPk=new SimpleIntegerProperty(); ;
	private SimpleIntegerProperty distributor=new SimpleIntegerProperty();;
	private SimpleStringProperty users=new SimpleStringProperty();;
	private SimpleIntegerProperty purchaseNumber=new SimpleIntegerProperty();;
	private SimpleStringProperty receivedDate=new SimpleStringProperty();;
	private SimpleStringProperty modifiedDate=new SimpleStringProperty();
        private SimpleDoubleProperty netAmount=new SimpleDoubleProperty();
        private SimpleDoubleProperty cd=new SimpleDoubleProperty();
        private SimpleDoubleProperty cdAmount=new SimpleDoubleProperty();
        private SimpleDoubleProperty totalAmount=new SimpleDoubleProperty();
	private Set<PurchaseDetails> purchaseDetailses = new HashSet<PurchaseDetails>(0);

        public PurchaseDo(Purchase purchase) {
        this.idPk.set(purchase.getIdPk());
        this.distributor.set(purchase.getDistributor().getIdPk());
        this.users.set(purchase.getUsers().getFirstName()+" "+purchase.getUsers().getFirstName());
        this.purchaseNumber.set(purchase.getPurchaseNumber());
        this.modifiedDate.set(purchase.getModifiedDate().toString());
        this.netAmount.set(purchase.getNetAmount());
        this.cd.set(purchase.getCd());
        this.cdAmount.set(purchase.getCdAmount());
        this.totalAmount.set(purchase.getTotalAmount());
        
    }
        
        
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

    public String getUsers() {
        return users.get();
    }

    public void setUsers(String users) {
        this.users.set(users);
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


    public Double getNetAmount() {
        return netAmount.get();
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount.set(netAmount);
    }

    public Double getCd() {
        return cd.get();
    }

    public void setCd(Double cd) {
        this.cd.set(cd);
    }

    public Double getCdAmount() {
        return cdAmount.get();
    }

    public void setCdAmount(Double cdAmount) {
        this.cdAmount.set(cdAmount);
    }

    public Double getTotalAmount() {
        return totalAmount.get();
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount.set(totalAmount);
    }
}
