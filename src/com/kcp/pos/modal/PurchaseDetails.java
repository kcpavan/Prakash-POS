package com.kcp.pos.modal;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_details", catalog = "storedb")
public class PurchaseDetails implements java.io.Serializable {

	private Integer idPk;
	private Items items;
	private Purchase purchase;
	private int itemQuantity;
	private Integer freeItem;

	public PurchaseDetails() {
	}

	public PurchaseDetails(Items items, Purchase purchase, int itemQuantity) {
		this.items = items;
		this.purchase = purchase;
		this.itemQuantity = itemQuantity;
	}

	public PurchaseDetails(Items items, Purchase purchase, int itemQuantity,
			Integer freeItem) {
		this.items = items;
		this.purchase = purchase;
		this.itemQuantity = itemQuantity;
		this.freeItem = freeItem;
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
	@JoinColumn(name = "item_id_fk", nullable = false)
	public Items getItems() {
		return this.items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purchase_id_fk", nullable = false)
	public Purchase getPurchase() {
		return this.purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	@Column(name = "item_quantity", nullable = false)
	public int getItemQuantity() {
		return this.itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	@Column(name = "free_item")
	public Integer getFreeItem() {
		return this.freeItem;
	}

	public void setFreeItem(Integer freeItem) {
		this.freeItem = freeItem;
	}

}
