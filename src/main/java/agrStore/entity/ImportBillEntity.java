package agrStore.entity;

import java.util.Date;
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
	
	@Column(name = "totalPrice")
	private int totalPrice;
	
	@Column(name = "totalQuantity")
	private int totalQuantity;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "createAt")
	private Date createAt;
	
	@ManyToOne()
	@JoinColumn(name="accountId")
	private AccountEntity account;
	
	@OneToMany(mappedBy = "importBill", fetch = FetchType.LAZY)
	private List<ImportBillDetailEntity> importBillDetailList;

	public ImportBillEntity() {
		super();
	}

	public ImportBillEntity(Integer importBillId, int totalPrice, int totalQuantity, Date createAt) {
		super();
		this.importBillId = importBillId;
		this.totalPrice = totalPrice;
		this.totalQuantity = totalQuantity;
		this.createAt = createAt;
	}

	public ImportBillEntity(int totalPrice, int totalQuantity, Date createAt, AccountEntity account) {
		super();
		this.totalPrice = totalPrice;
		this.totalQuantity = totalQuantity;
		this.createAt = createAt;
		this.account = account;
	}

	public Integer getImportBillId() {
		return importBillId;
	}

	public void setImportBillId(Integer importBillId) {
		this.importBillId = importBillId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public List<ImportBillDetailEntity> getImportBillDetailList() {
		return importBillDetailList;
	}

	public void setImportBillDetailList(List<ImportBillDetailEntity> importBillDetailList) {
		this.importBillDetailList = importBillDetailList;
	}

	@Override
	public String toString() {
		return "ImportBillEntity [importBillId=" + importBillId + ", totalPrice=" + totalPrice + ", totalQuantity="
				+ totalQuantity + ", createAt=" + createAt + "]";
	}
	

}
