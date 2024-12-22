package agrStore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Provider")
public class ProviderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "providerId")
	private Integer id;
	
	@Column(name = "providerName")
	private String providerName;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "image")
	private String image;
	
	@OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
	private List<ProductEntity> productList;

	public ProviderEntity() {
		super();
	}

	public ProviderEntity(Integer id, String providerName, String phoneNumber, Boolean status, String image) {
		super();
		this.id = id;
		this.providerName = providerName;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ProductEntity> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductEntity> productList) {
		this.productList = productList;
	}
	
	
}
