package agrStore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class AddressEntity {
	@Id
	@Column(name = "addressId")
	private Integer id;
	
	@OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
	private ProvinceEntity province;
	
	@OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
	private DistrictEntity district;
	
	@OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
	private WardEntity ward;
	
	public AddressEntity(Integer id, ProvinceEntity province, DistrictEntity district, WardEntity ward) {
		super();
		this.id = id;
		this.province = province;
		this.district = district;
		this.ward = ward;
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
	
	
}
