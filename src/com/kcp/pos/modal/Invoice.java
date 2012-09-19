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
@Table(name = "invoice", catalog = "storedb")
public class Invoice implements java.io.Serializable {

	private Integer idPk;
	private Users users;
	private int totalItems;
	private double totalAmount;
	private Date modifiedDate;
	private Set<InvoiceDetails> invoiceDetailses = new HashSet<InvoiceDetails>(0);

	public Invoice() {
	}

	public Invoice(Users users, int totalItems, double totalAmount,
			Date modifiedDate) {
		this.users = users;
		this.totalItems = totalItems;
		this.totalAmount = totalAmount;
		this.modifiedDate = modifiedDate;
	}

	public Invoice(Users users, int totalItems, double totalAmount,
			Date modifiedDate, Set invoiceDetailses) {
		this.users = users;
		this.totalItems = totalItems;
		this.totalAmount = totalAmount;
		this.modifiedDate = modifiedDate;
		this.invoiceDetailses = invoiceDetailses;
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

	@Column(name = "total_items", nullable = false)
	public int getTotalItems() {
		return this.totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	@Column(name = "total_amount", nullable = false, precision = 22, scale = 0)
	public double getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", nullable = false, length = 19)
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice")
	public Set<InvoiceDetails> getInvoiceDetailses() {
		return this.invoiceDetailses;
	}

	public void setInvoiceDetailses(Set<InvoiceDetails> invoiceDetailses) {
		this.invoiceDetailses = invoiceDetailses;
	}

}
