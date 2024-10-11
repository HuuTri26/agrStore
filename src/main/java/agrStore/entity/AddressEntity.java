package agrStore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class AddressEntity {
	@Id
	@GeneratedValue
	@Column(name = "addressId")
	private Integer id;
	
	@ManyToOne()
	@JoinColumn(name = "provinceId")
	private ProvinceEntity province;
	
	@ManyToOne()
	@JoinColumn(name = "districtId")
	private DistrictEntity district;
	
	@ManyToOne()
	@JoinColumn(name = "wardId")
	private WardEntity ward;
	
	@OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
	private AccountEntity account;
	
//	@OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
//	private CustomerEntity customer;
	
	public AddressEntity(Integer id, ProvinceEntity province, DistrictEntity district, WardEntity ward,
			AccountEntity account) {
		super();
		this.id = id;
		this.province = province;
		this.district = district;
		this.ward = ward;
		this.account = account;
	}

	public AddressEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProvinceEntity getProvince() {
		return province;
	}

	public void setProvince(ProvinceEntity province) {
		this.province = province;
	}

	public DistrictEntity getDistrict() {
		return district;
	}

	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}

	public WardEntity getWard() {
		return ward;
	}

	public void setWard(WardEntity ward) {
		this.ward = ward;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	
	
	
	
}
