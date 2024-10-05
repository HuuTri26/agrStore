package agrStore.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ImportBill")
public class ImportBillEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "importBillId")
	private Integer importBillId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adminId")
	private AdminEntity admin;

	@Column(name = "totalPrice")
	private Float totalPrice;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "createAt")
	private LocalDateTime createAt;

	@OneToMany(mappedBy = "importBill", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	List<ImportBillDetailEntity> importBillDetailList;

	public ImportBillEntity() {
		super();
	}

	public ImportBillEntity(Integer importBillId, Float totalPrice, LocalDateTime createAt) {
		super();
		this.importBillId = importBillId;
		this.totalPrice = totalPrice;
		this.createAt = createAt;
	}

	public Integer getImportBillId() {
		return importBillId;
	}

	public void setImportBillId(Integer importBillId) {
		this.importBillId = importBillId;
	}

	public AdminEntity getAdmin() {
		return admin;
	}

	public void setAdmin(AdminEntity admin) {
		this.admin = admin;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public List<ImportBillDetailEntity> getImportBillDetailList() {
		return importBillDetailList;
	}

	public void setImportBillDetailList(List<ImportBillDetailEntity> importBillDetailList) {
		this.importBillDetailList = importBillDetailList;
	}
	
	

}
