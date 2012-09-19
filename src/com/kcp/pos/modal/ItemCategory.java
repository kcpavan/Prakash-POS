package com.kcp.pos.modal;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "item_category", catalog = "storedb")
public class ItemCategory implements java.io.Serializable {

	private Integer idPk;
	private String categoryName;
	private Set<Items> itemses = new HashSet<Items>(0);

	public ItemCategory() {
	}

	public ItemCategory(String categoryName) {
		this.categoryName = categoryName;
	}

	public ItemCategory(String categoryName, Set itemses) {
		this.categoryName = categoryName;
		this.itemses = itemses;
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

	@Column(name = "category_name", nullable = false, length = 250)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@OneToMany(fetch = FetchType.LAZY,targetEntity = Items.class, mappedBy = "itemCategory")
	public Set<Items> getItemses() {
		return this.itemses;
	}

	public void setItemses(Set<Items> itemses) {
		this.itemses = itemses;
	}

}
