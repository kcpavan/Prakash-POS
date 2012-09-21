/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.modal;

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
@Table(name = "billing_price", catalog = "storedb")
@NamedQueries({
    @NamedQuery(name = "BillingPrice.findByName", query="SELECT c FROM BillingPrice c "
        + "WHERE c.idPk = :id"),
    @NamedQuery(name = "BillingPrice.findByItemId", query="SELECT c FROM BillingPrice c "
        + "WHERE c.item.idPk = :id"),
    @NamedQuery(name = "BillingPrice.findByItemIdQuantity", query="SELECT c FROM BillingPrice c "
        + "WHERE c.item.idPk = :itemId and startRange<=:quantity and endRange>=:quantity")
    
    
})
public class BillingPrice implements java.io.Serializable{

    /*
     * id_pk integer primary key not null auto_increment,
item_id_fk integer not null,
start_range1 integer not null,
end_range1 integer not null,
range1_billingprice double not null,
start_range1 integer not null,
end_range1 integer not null,
range2_billingprice double not null,
start_range1 integer not null,
end_range1 integer not null,
range3_billingprice double not null,
billing_price integer not null,
modified_by int not null,
modified_date timestamp,
     */
   
    private Integer idPk;
    private Users users;
    private Items item;
   /* private Integer startRange1;
    private Integer endRange1;
    private Double billingPrice1;
    private Integer startRange2;
    private Integer endRange2;
    private Double billingPrice2;
    private Integer startRange3;
    private Integer endRange3;
    private Double billingPrice3;*/
    private Integer startRange;
    private Integer endRange;
    private Double billingPrice;
    private Date modifiedDate;

    public BillingPrice(Integer idPk, Users users, Items item, Integer startRange, Integer endRange, Double billingPrice, Date modifiedDate) {
        this.idPk = idPk;
        this.users = users;
        this.item = item;
        this.startRange = startRange;
        this.endRange = endRange;
        this.billingPrice = billingPrice;
        this.modifiedDate = modifiedDate;
    }

   

    public BillingPrice() {
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
    
    
    
}
