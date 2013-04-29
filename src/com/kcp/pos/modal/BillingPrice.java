package com.kcp.pos.modal;

import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "billing_price", catalog = "storedb")
@NamedQueries({
  
    @NamedQuery(name = "BillingPrice.findById", query = "SELECT c FROM BillingPrice c "
        + "WHERE c.idPk = :id"),
    
    @NamedQuery(name = "BillingPrice.findByItemDetailsId", 
        query = "SELECT c FROM BillingPrice c "
        + "WHERE c.itemDetails.idPk = :itemDetailsId "),
    @NamedQuery(name = "BillingPrice.findByItemQtyId ", 
        query = "SELECT c FROM BillingPrice c "
        + " WHERE c.itemDetails.idPk = :idPk "
        + " and c.startRange<=:qty and c.endRange>=:qty "),
    
    
    
})
public class BillingPrice implements java.io.Serializable {

    private Integer idPk;
  //  private Invoice invoice;
   // private Items items;
    private ItemDetails itemDetails;
    //private BillingPrice billingPrice;
    private Double startRange;
    private Double endRange;
    private Double billingPrice;
    private Users modifiedUser;
    private Date modifiedDate;

    public BillingPrice(Integer idPk, ItemDetails itemDetails, Double startRange, Double endRange, Double billingPrice,
            Users modifiedUser, Date modifiedDate) {
        this.idPk = idPk;
        this.itemDetails = itemDetails;
        this.startRange = startRange;
        this.endRange = endRange;
        this.billingPrice = billingPrice;
        this.modifiedUser = modifiedUser;
        this.modifiedDate = modifiedDate;
    }

   

    public BillingPrice() {
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
    @JoinColumn(name = "item_details_id_fk", nullable = false)
    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }

    @Column(name = "billing_price", nullable = false, precision = 22, scale = 0)
    public Double getBillingPrice() {
        return billingPrice;
    }

    @Column(name = "start_range", nullable = false, precision = 22, scale = 0)
    public Double getStartRange() {
        return startRange;
    }

    public void setStartRange(Double startRange) {
        this.startRange = startRange;
    }

    @Column(name = "end_range", nullable = false, precision = 22, scale = 0)
    public Double getEndRange() {
        return endRange;
    }

    public void setEndRange(Double endRange) {
        this.endRange = endRange;
    }

    public void setBillingPrice(Double billingPrice) {
        this.billingPrice = billingPrice;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modified_by", nullable = false)
    public Users getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(Users modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date", nullable = false, length = 19)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    
    
}
