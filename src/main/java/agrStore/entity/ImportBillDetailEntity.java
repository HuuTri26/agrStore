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
@Table(name = "ImportBillDetail")
public class ImportBillDetailEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "importBillDetailId")
	private Integer importBillDetailId;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "importPrice")
	private int importPrice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="importBillId")
	private ImportBillEntity importBill;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private ProductEntity product;

	public ImportBillDetailEntity() {
		super();
	}

	public ImportBillDetailEntity(Integer importBillDetailId, int quantity, int importPrice) {
		super();
		this.importBillDetailId = importBillDetailId;
		this.quantity = quantity;
		this.importPrice = importPrice;
	}

	public Integer getImportBillDetailId() {
		return importBillDetailId;
	}

	public void setImportBillDetailId(Integer importBillDetailId) {
		this.importBillDetailId = importBillDetailId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(int importPrice) {
		this.importPrice = importPrice;
	}

	public ImportBillEntity getImportBill() {
		return importBill;
	}

	public void setImportBill(ImportBillEntity importBill) {
		this.importBill = importBill;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	
	

}
