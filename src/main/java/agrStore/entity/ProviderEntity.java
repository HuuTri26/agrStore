package agrStore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Provider")
public class ProviderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "providerId")
	private Integer providerId;

	@Column(name = "providerName")
	private String providerName;

	@Column(name = "descript")
	private String descript;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "status")
	private Boolean status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="productId")
	private ProductEntity product;

	public ProviderEntity() {
		super();
	}

	public ProviderEntity(Integer providerId, String providerName, String descript, String phoneNumber,
			Boolean status) {
		super();
		this.providerId = providerId;
		this.providerName = providerName;
		this.descript = descript;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}

	public ProviderEntity(String providerName, String descript, String phoneNumber, Boolean status) {
		super();
		this.providerName = providerName;
		this.descript = descript;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
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

}
