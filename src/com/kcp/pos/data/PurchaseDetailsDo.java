/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.data;

import com.kcp.pos.modal.PurchaseDetails;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Prakash
 */
public class PurchaseDetailsDo {

    private SimpleIntegerProperty idPk = new SimpleIntegerProperty();
    
    private SimpleIntegerProperty itemId = new SimpleIntegerProperty();
    private SimpleIntegerProperty itemDetailsId = new SimpleIntegerProperty();
    private SimpleStringProperty itemName = new SimpleStringProperty();
    private SimpleStringProperty barcode = new SimpleStringProperty();
    
    private SimpleIntegerProperty purchase = new SimpleIntegerProperty();
    //private BillingPriceDo billingPrice;
    private SimpleDoubleProperty mrp = new SimpleDoubleProperty();
    private SimpleIntegerProperty caseQuantity = new SimpleIntegerProperty();
    private SimpleIntegerProperty unitsPerCase = new SimpleIntegerProperty();
    private SimpleDoubleProperty unitsQuantity = new SimpleDoubleProperty();
    private SimpleIntegerProperty freeUnits = new SimpleIntegerProperty();
    private SimpleDoubleProperty basicRate = new SimpleDoubleProperty();
    private SimpleDoubleProperty grossAmount = new SimpleDoubleProperty();
    private SimpleDoubleProperty scheme = new SimpleDoubleProperty();
    private SimpleDoubleProperty cd = new SimpleDoubleProperty();
    private SimpleDoubleProperty taxPercentage = new SimpleDoubleProperty();
    private SimpleDoubleProperty tax = new SimpleDoubleProperty();
    private SimpleDoubleProperty netAmount = new SimpleDoubleProperty();

    public PurchaseDetailsDo() {
    }
  
    public PurchaseDetailsDo(PurchaseDetails purchaseDetails) {
        this.idPk.set(purchaseDetails.getIdPk());
        this.itemId.set(purchaseDetails.getItemDetails().getItem().getIdPk());
        this.itemDetailsId.set(purchaseDetails.getItemDetails().getIdPk());
        this.itemName.set(purchaseDetails.getItemDetails().getItem().getItemName());
        this.barcode.set(purchaseDetails.getItemDetails().getItem().getBarcode());
        this.purchase.set(purchaseDetails.getPurchase().getPurchaseNumber());
        this.mrp.set(purchaseDetails.getMrp());
        this.caseQuantity.set(purchaseDetails.getCaseQuantity());
        this.unitsPerCase.set(purchaseDetails.getUnitsPerCase());
        this.unitsQuantity.set(purchaseDetails.getUnitsQuantity());
        this.freeUnits.set(purchaseDetails.getFreeUnits());
        this.basicRate.set(purchaseDetails.getBasicRate());
        this.grossAmount.set(purchaseDetails.getGrossAmount());
        this.scheme.set(purchaseDetails.getScheme());
        this.cd.set(purchaseDetails.getCd());
        this.taxPercentage.set(purchaseDetails.getTaxPercentage());
        this.tax.set(purchaseDetails.getTax());
        this.netAmount.set(purchaseDetails.getNetAmount());
    }
    public Integer getIdPk() {
        return idPk.get();
    }

    public void setIdPk(SimpleIntegerProperty idPk) {
        this.idPk = idPk;
    }

    public Integer getItemId() {
        return itemId.get();
    }

    public void setItemId(SimpleIntegerProperty items) {
        this.itemId = itemId;
    }

    public Integer getPurchase() {
        return purchase.get();
    }

    public void setPurchase(SimpleIntegerProperty purchase) {
        this.purchase = purchase;
    }

    public Double getMrp() {
        return mrp.get();
    }

    public void setMrp(Double mrp) {
        this.mrp.set(mrp);
    }

    public Integer getCaseQuantity() {
        return caseQuantity.get();
    }

    public void setCaseQuantity(Integer caseQuantity) {
        this.caseQuantity.set(caseQuantity);
    }

    public Double getUnitsQuantity() {
        return unitsQuantity.get();
    }

    public void setUnitsQuantity(Double unitsQuantity) {
        this.unitsQuantity.set(unitsQuantity);
    }

    public Integer getFreeUnits() {
        return freeUnits.get();
    }

    public void setFreeUnits(Integer freeUnits) {
        this.freeUnits.set(freeUnits);
    }

    public Double getBasicRate() {
        return basicRate.get();
    }

    public void setBasicRate(Double basicRate) {
        this.basicRate.set(basicRate);
    }

    public Double getGrossAmount() {
        return grossAmount.get();
    }

    public void setGrossAmount(SimpleDoubleProperty grossAmount) {
        this.grossAmount = grossAmount;
    }

    public Double getScheme() {
        return scheme.get();
    }

    public void setScheme(SimpleDoubleProperty scheme) {
        this.scheme = scheme;
    }

    public Double getCd() {
        return cd.get();
    }

    public void setCd(SimpleDoubleProperty cd) {
        this.cd = cd;
    }

    public Double getTaxPercentage() {
        return taxPercentage.get();
    }

    public void setTaxPercentage(SimpleDoubleProperty taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public Double getTax() {
        return tax.get();
    }

    public void setTax(SimpleDoubleProperty tax) {
        this.tax = tax;
    }

    public Double getNetAmount() {
        return netAmount.get();
    }

    public void setNetAmount(SimpleDoubleProperty netAmount) {
        this.netAmount = netAmount;
    }

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(SimpleStringProperty itemName) {
        this.itemName = itemName;
    }

    public String getBarcode() {
        return barcode.get();
    }

    public void setBarcode(SimpleStringProperty barcode) {
        this.barcode = barcode;
    }

    public SimpleIntegerProperty getItemDetailsId() {
        return itemDetailsId;
    }

    public void setItemDetailsId(SimpleIntegerProperty itemDetailsId) {
        this.itemDetailsId = itemDetailsId;
    }

    public Integer getUnitsPerCase() {
        return unitsPerCase.get();
    }

    public void setUnitsPerCase(Integer unitsPerCase) {
        this.unitsPerCase.set(unitsPerCase);
    }

    
    
    
 
    
}
