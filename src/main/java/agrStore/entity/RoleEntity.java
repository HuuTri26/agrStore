package agrStore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Table(name = "Role")
public class RoleEntity {
	@Id
	@GeneratedValue
	@Column(name = "roleId")
	private Integer id;
	
	@Column(name = "roleName")
	private String name;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<AccountEntity> accounts;
	
	public RoleEntity(Integer id, String name, List<AccountEntity> accounts) {
		super();
		this.id = id;
		this.name = name;
		this.accounts = accounts;
	}
	
	public RoleEntity(String name) {
		super();
		this.name = name;
	}
	
	public RoleEntity() {
		super();
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	

	public void setRoleName(String name) {
		this.name = name;
	}


	public List<AccountEntity> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<AccountEntity> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", name=" + name + "]";
	}
	
	
}
