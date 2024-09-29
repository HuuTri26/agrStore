package agrStore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Province")
public class ProvinceEntity {
	@Id
	@Column(name = "roleId")
	private Integer id;
	
	@Column(name = "provinceName")
	private String name;
	
	@OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
	private List<DistrictEntity> districts;
	
	@OneToOne()
	@JoinColumn(name = "AddressId")
	private AddressEntity address;
	
	public ProvinceEntity(Integer id, String name, List<DistrictEntity> districts, AddressEntity address) {
		super();
		this.id = id;
		this.name = name;
		this.districts = districts;
		this.address = address;
	}
	
	public ProvinceEntity() {
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

	public List<DistrictEntity> getDistricts() {
		return districts;
	}

	public void setDistricts(List<DistrictEntity> districts) {
		this.districts = districts;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}
	
}
