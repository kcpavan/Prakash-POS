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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "stocks", catalog = "storedb")
public class Stocks implements java.io.Serializable {

	private Integer idPk;
	private ItemDetails itemDetails;
	private Users users;
	private Integer quantity;
	private String quantityUnit;
	private Date modifiedDate;

	public Stocks() {
	}

    public Stocks(Integer idPk, ItemDetails itemDetails, Users users, Integer quantity, String quantityUnit, Date modifiedDate) {
        this.idPk = idPk;
        this.itemDetails = itemDetails;
        this.users = users;
        this.quantity = quantity;
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

	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "quantity_unit", nullable = false, length = 50)
	public String getQuantityUnit() {
		return this.quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", nullable = false, length = 19)
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

        @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_details_id_fk", nullable = false)
	public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }
        
        

}
