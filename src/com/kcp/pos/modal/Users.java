package com.kcp.pos.modal;


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

/**
 */
@Entity
@Table(name = "users", catalog = "storedb")
public class Users implements java.io.Serializable {

	private Integer idPk;
	private UserGroup userGroup;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private int userState;
	private Set<Distributor> distributors = new HashSet<Distributor>(0);
	private Set<Items> itemses = new HashSet<Items>(0);
	private Set<Invoice> invoices = new HashSet<Invoice>(0);
	private Set<Stocks> stockses = new HashSet<Stocks>(0);
	private Set purchases = new HashSet(0);

	public Users() {
	}

	public Users(UserGroup userGroup, String firstName, String lastName,
			String username, String password, int userState) {
		this.userGroup = userGroup;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.userState = userState;
	}

	public Users(UserGroup userGroup, String firstName, String lastName,
			String username, String password, int userState, Set distributors,
			Set itemses, Set invoices, Set stockses, Set purchases) {
		this.userGroup = userGroup;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.userState = userState;
		this.distributors = distributors;
		this.itemses = itemses;
		this.invoices = invoices;
		this.stockses = stockses;
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
	@JoinColumn(name = "group_id_fk", nullable = false)
	public UserGroup getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	@Column(name = "first_name", nullable = false, length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "username", nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "user_state", nullable = false)
	public int getUserState() {
		return this.userState;
	}

	public void setUserState(int userState) {
		this.userState = userState;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Distributor> getDistributors() {
		return this.distributors;
	}

	public void setDistributors(Set<Distributor> distributors) {
		this.distributors = distributors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Items> getItemses() {
		return this.itemses;
	}

	public void setItemses(Set<Items> itemses) {
		this.itemses = itemses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Stocks> getStockses() {
		return this.stockses;
	}

	public void setStockses(Set<Stocks> stockses) {
		this.stockses = stockses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Purchase> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}

}
