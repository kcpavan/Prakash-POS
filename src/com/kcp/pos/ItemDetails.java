/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.modal.*;
import java.util.Date;
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
    @NamedQuery(name = "ItemDetails.findByName", query="SELECT c FROM ItemDetails c "
        + "WHERE c.idPk = :id"),
    @NamedQuery(name = "ItemDetails.findByItemId", query="SELECT c FROM ItemDetails c "
        + "WHERE c.item.idPk = :id"),
    @NamedQuery(name = "ItemDetails.findByItemIdQuantity", query="SELECT c FROM ItemDetails c "
        + "WHERE c.item.idPk = :itemId and startRange<=:quantity and endRange>=:quantity")
    
    
})
public class ItemDetails implements java.io.Serializable{

    /*
     *id_pk integer primary key not null auto_increment,
item_id_fk integer not null,
mrp double not null,
actual_price double not null,
start_range integer  null,
end_range integer  null,
billing_price double not null,
billing_type_id_fk int not null
modified_by int not null,
modified_date timestamp, 
     * 
     */
   
    private Integer idPk;
    private Users users;
    private Items item;
    private Double mrp;
    private Double actualPrice;
   
    private Integer startRange;
    private Integer endRange;
    private BillingType billingType;
    private Double billingPrice;
    private Date modifiedDate;
    
    

    public ItemDetails(Integer idPk, Users users, Items item, Double mrp, Double actualPrice, Integer startRange, Integer endRange, BillingType billingType, Double billingPrice, Date modifiedDate) {
        this.idPk = idPk;
        this.users = users;
        this.item = item;
        this.mrp = mrp;
        this.actualPrice = actualPrice;
        this.startRange = startRange;
        this.endRange = endRange;
        this.billingType = billingType;
        this.billingPrice = billingPrice;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id_fk", nullable = false)
    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    @Column(name = "start_range", nullable = false, length = 250)
    public Integer getStartRange() {
        return startRange;
    }

    public void setStartRange(Integer startRange) {
        this.startRange = startRange;
    }

    @Column(name = "end_range", nullable = false, length = 250)
    public Integer getEndRange() {
        return endRange;
    }

    public void setEndRange(Integer endRange) {
        this.endRange = endRange;
    }

    public Double getBillingPrice() {
        return billingPrice;
    }

    public void setBillingPrice(Double billingPrice) {
        this.billingPrice = billingPrice;
    }

   


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date", nullable = false, length = 19)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Double getMrp() {
        return mrp;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    public Double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
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
