package agrStore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "District")
public class DistrictEntity {
	@Id
	@Column(name = "districtId")
	private Integer id;
	
	@Column(name = "districtName")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "ProvinceId")
	private ProvinceEntity province;
	
	@OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
	private List<WardEntity> wards;
	
	@OneToOne
	@JoinColumn(name = "AddressId")
	private AddressEntity address;
	
	public DistrictEntity(Integer id, String name, ProvinceEntity province, List<WardEntity> wards,
			AddressEntity address) {
		super();
		this.id = id;
		this.name = name;
		this.province = province;
		this.wards = wards;
		this.address = address;
	}
	
	public DistrictEntity() {
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

	public void setName(String name) {
		this.name = name;
	}

	public ProvinceEntity getProvince() {
		return province;
	}

	public void setProvince(ProvinceEntity province) {
		this.province = province;
	}

	public List<WardEntity> getWards() {
		return wards;
	}

	public void setWards(List<WardEntity> wards) {
		this.wards = wards;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}
	
}
