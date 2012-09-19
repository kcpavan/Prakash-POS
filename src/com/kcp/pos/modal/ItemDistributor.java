package com.kcp.pos.modal;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "distributor", catalog = "storedb")
public class ItemDistributor implements java.io.Serializable {

	private Integer idPk;
	private Users users;
	private int name;  
	private String address;
	private int phoneNumber;
	private Date modifiedDate;
	private Set<Purchase> purchases = new HashSet<Purchase>(0);

	public ItemDistributor() {
	}

	public ItemDistributor(Users users, int name, String address, int phoneNumber,
			Date modifiedDate) {
		this.users = users;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.modifiedDate = modifiedDate;
	}

	public ItemDistributor(Users users, int name, String address, int phoneNumber,
			Date modifiedDate, Set purchases) {
		this.users = users;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.modifiedDate = modifiedDate;
		this.purchases = purchases;
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

	@Column(name = "name", nullable = false)
	public int getName() {
		return this.name;
	}

	public void setName(int name) {
		this.name = name;
	}

	@Column(name = "address", nullable = false, length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "phone_number", nullable = false)
	public int getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", nullable = false, length = 19)
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "distributor")
	public Set<Purchase> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}

}
