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
@Table(name = "stocks", catalog = "storedb")
@NamedQueries({
    @NamedQuery(name = "Stocks.findByItemId", 
        query = "SELECT i FROM Stocks i where"
        + " i.itemDetails.item.idPk=:itemId"),
      @NamedQuery(name = "Stocks.findAll", query = "SELECT i FROM Stocks i")
        
        })
public class Stocks implements java.io.Serializable {

	private Integer idPk;
        private Items items;
	private ItemDetails itemDetails;
	private Users users;
	private Double unitQuantity;
        private Integer caseQuantity;
        private Integer unitsPerCase;
	private String quantityUnit;
	private Date modifiedDate;

	public Stocks() {
	}

    public Stocks(Integer idPk, Items items, ItemDetails itemDetails, Users users, Double unitQuantity, Integer caseQuantity, Integer unitsPerCase, String quantityUnit, Date modifiedDate) {
        this.idPk = idPk;
        this.items = items;
        this.itemDetails = itemDetails;
        this.users = users;
        this.unitQuantity = unitQuantity;
        this.caseQuantity = caseQuantity;
        this.unitsPerCase = unitsPerCase;
        this.quantityUnit = quantityUnit;
        this.modifiedDate = modifiedDate;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modified_by", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", nullable = false, length = 19)
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

        @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "itemdetails_id_fk", nullable = false)
	public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id_fk", nullable = false)
    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    @Column(name = "quantity", nullable = false)
    public Double getUnitQuantity() {
        return unitQuantity;
    }

    public void setUnitQuantity(Double unitQuantity) {
        this.unitQuantity = unitQuantity;
    }

    @Column(name = "case_quantity", nullable = false)
    public Integer getCaseQuantity() {
        return caseQuantity;
    }

    public void setCaseQuantity(Integer caseQuantity) {
        this.caseQuantity = caseQuantity;
    }
        
    @Column(name = "quantity_per_case", nullable = false)
    public Integer getUnitsPerCase() {
        return unitsPerCase;
    }

    public void setUnitsPerCase(Integer unitsPerCase) {
        this.unitsPerCase = unitsPerCase;
    }

    

}
