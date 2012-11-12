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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "purchase", catalog = "storedb")
@NamedQueries({
@NamedQuery(name = "Purchase.findByAll", query = "SELECT c FROM Purchase c ")
})

public class Purchase implements java.io.Serializable {

	private Integer idPk;
	private Distributor distributor;
	private Users users;
	private int purchaseNumber;
	//private Date receivedDate;
	private Date modifiedDate;
        private Double netAmount;
        private Double cd;
        private Double cdAmount;
        private Double totalAmount;
        
	private Set<PurchaseDetails> purchaseDetailses = new HashSet<PurchaseDetails>(0);

	public Purchase() {
	}

    public Purchase(Integer idPk, Distributor distributor, Users users, int purchaseNumber, Date modifiedDate, Double netAmount, Double cd, Double cdAmount, Double totalAmount) {
        this.idPk = idPk;
        this.distributor = distributor;
        this.users = users;
        this.purchaseNumber = purchaseNumber;
        this.modifiedDate = modifiedDate;
        this.netAmount = netAmount;
        this.cd = cd;
        this.cdAmount = cdAmount;
        this.totalAmount = totalAmount;
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
	public Distributor getDistributor() {
		return this.distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	@ManyToOne(fetch = FetchType.EAGER)
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

	/*@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "received_date", nullable = false, length = 19)
	public Date getReceivedDate() {
		return this.receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}*/

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

        
    @Column(name = "net_amount", nullable = false)
    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    @Column(name = "cd", nullable = false)
    public Double getCd() {
        return cd;
    }

    public void setCd(Double cd) {
        this.cd = cd;
    }

    @Column(name = "cd_amount", nullable = false)
    public Double getCdAmount() {
        return cdAmount;
    }

    public void setCdAmount(Double cdAmount) {
        this.cdAmount = cdAmount;
    }

    @Column(name = "total_amount", nullable = false)
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
