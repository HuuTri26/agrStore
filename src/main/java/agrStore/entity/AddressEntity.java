package agrStore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class AddressEntity {
	@Id
	@GeneratedValue
	@Column(name = "addressId")
	private Integer id;

	@Column(name = "streetName")
	private String streetName;

	@ManyToOne()
	@JoinColumn(name = "wardId")
	private WardEntity ward;

	@OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
	private List<AccountEntity> account;

	public AddressEntity() {
		super();
	}

	public AddressEntity(Integer id, String streetName, WardEntity ward) {
		super();
		this.id = id;
		this.streetName = streetName;
		this.ward = ward;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public WardEntity getWard() {
		return ward;
	}

	public void setWard(WardEntity ward) {
		this.ward = ward;
	}

	public List<AccountEntity> getAccount() {
		return account;
	}

	public void setAccount(List<AccountEntity> account) {
		this.account = account;
	}

}
