package agrStore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Provider")
public class ProvidertEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "providerId")
	private Integer id;
	
	@Column(name = "providerName")
	private String providerName;

	@Column(name = "descript")
	private String descript;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "status")
	private Boolean status;
	
//	private ProductEntity product;
}
