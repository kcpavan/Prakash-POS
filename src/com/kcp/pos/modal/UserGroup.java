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

/**
 */
@Entity
@Table(name = "user_group", catalog = "storedb")
public class UserGroup implements java.io.Serializable {

	private Integer idPk;
	private String groupName;
	private Set<Users> userses = new HashSet<Users>(0);

	public UserGroup() {
	}

	public UserGroup(String groupName) {
		this.groupName = groupName;
	}

	public UserGroup(String groupName, Set userses) {
		this.groupName = groupName;
		this.userses = userses;
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

	@Column(name = "group_name", nullable = false, length = 20)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userGroup")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

}
