package agrStore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Ward")
public class WardEntity {
	@Id
	@Column(name = "wardId")
	private Integer id;
	
	@Column(name = "wardName")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "DistrictId")
	private DistrictEntity district;
	
	@OneToOne()
	@JoinColumn(name = "AddressId")
	private AddressEntity address;

	public WardEntity(Integer id, String name, DistrictEntity district, AddressEntity address) {
		super();
		this.id = id;
		this.name = name;
		this.district = district;
		this.address = address;
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

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}
	
}
