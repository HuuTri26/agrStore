package agrStore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Province")
public class ProvinceEntity {
	@Id
	@GeneratedValue
	@Column(name = "provinceId")
	private Integer id;
	
	@Column(name = "provinceName")
	private String name;
	
	@OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
	private List<DistrictEntity> districts;
	
	public ProvinceEntity(Integer id, String name, List<DistrictEntity> districts) {
		super();
		this.id = id;
		this.name = name;
		this.districts = districts;
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

	@Override
	public String toString() {
		return "ProvinceEntity [id=" + id + ", name=" + name + "]";
	}
	
	
}
