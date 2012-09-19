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
@Table(name = "purchase", catalog = "storedb")
public class Purchase implements java.io.Serializable {

	private Integer idPk;
	private ItemDistributor distributor;
	private Users users;
	private int purchaseNumber;
	private Date receivedDate;
	private Date modifiedDate;
	private Set<PurchaseDetails> purchaseDetailses = new HashSet<PurchaseDetails>(0);

	public Purchase() {
	}

	public Purchase(ItemDistributor distributor, Users users, int purchaseNumber,
			Date receivedDate, Date modifiedDate) {
		this.distributor = distributor;
		this.users = users;
		this.purchaseNumber = purchaseNumber;
		this.receivedDate = receivedDate;
		this.modifiedDate = modifiedDate;
	}

	public Purchase(ItemDistributor distributor, Users users, int purchaseNumber,
			Date receivedDate, Date modifiedDate, Set purchaseDetailses) {
		this.distributor = distributor;
		this.users = users;
		this.purchaseNumber = purchaseNumber;
		this.receivedDate = receivedDate;
		this.modifiedDate = modifiedDate;
		this.purchaseDetailses = purchaseDetailses;
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
	@JoinColumn(name = "distibutor_id_fk", nullable = false)
	public ItemDistributor getDistributor() {
		return this.distributor;
	}

	public void setDistributor(ItemDistributor distributor) {
		this.distributor = distributor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modified_by", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "purchase_number", nullable = false)
	public int getPurchaseNumber() {
		return this.purchaseNumber;
	}

	public void setPurchaseNumber(int purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "received_date", nullable = false, length = 19)
	public Date getReceivedDate() {
		return this.receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", nullable = false, length = 19)
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchase")
	public Set<PurchaseDetails> getPurchaseDetailses() {
		return this.purchaseDetailses;
	}

	public void setPurchaseDetailses(Set<PurchaseDetails> purchaseDetailses) {
		this.purchaseDetailses = purchaseDetailses;
	}

}
