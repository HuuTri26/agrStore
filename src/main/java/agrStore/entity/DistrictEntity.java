package agrStore.entity;

import java.util.List;

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
@Table(name = "District")
public class DistrictEntity {
	@Id
	@GeneratedValue
	@Column(name = "districtId")
	private Integer id;
	
	@Column(name = "districtName")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "provinceId")
	private ProvinceEntity province;
	
	@OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
	private List<WardEntity> wards;
	
	@OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
	private List<AddressEntity> addresses;
	
	public DistrictEntity(Integer id, String name, ProvinceEntity province, List<WardEntity> wards,
			List<AddressEntity> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.province = province;
		this.wards = wards;
		this.addresses = addresses;
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

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}
	
}
