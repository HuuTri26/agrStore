package agrStore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@Column(name = "price")
	private int price;
	
	@ManyToOne()
	@JoinColumn(name="importBillId")
	private ImportBillEntity importBill;
	
	@ManyToOne()
	@JoinColumn(name="productId")
	private ProductEntity product;

	public ImportBillDetailEntity() {
		super();
	}

	public ImportBillDetailEntity(Integer importBillDetailId, int quantity, int price) {
		super();
		this.importBillDetailId = importBillDetailId;
		this.quantity = quantity;
		this.price = price;
	}

	public ImportBillDetailEntity(int quantity, int price, ProductEntity product) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.product = product;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
