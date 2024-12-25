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
@Table(name = "Ward")
public class WardEntity {
	@Id
	@GeneratedValue
	@Column(name = "wardId")
	private Integer id;
	
	@Column(name = "wardName")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "districtId")
	private DistrictEntity district;
	
	@OneToMany(mappedBy = "ward", fetch = FetchType.LAZY)
	private List<AddressEntity> addresses;
	
	public WardEntity(Integer id, String name, DistrictEntity district, List<AddressEntity> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.district = district;
		this.addresses = addresses;
	}

	public WardEntity() {
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

	public DistrictEntity getDistrict() {
		return district;
	}

	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "WardEntity [id=" + id + ", name=" + name + "]";
	}
	
}
