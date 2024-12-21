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
@Table(name = "Product")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productId")
	private Integer productId;

	@Column(name = "productName")
	private String productName;

	@Column(name = "price")
	private double price;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "descript")
	private String descript;

	@Column(name = "image")
	private String image;

	@Column(name = "unit")
	private String unit;

	@Column(name = "status")
	private Boolean status;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "createAt")
	private Date createAt;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "updateAt")
	private Date updateAt;

//	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@ManyToOne()
	@JoinColumn(name = "categoryId")
	private CategoryEntity category;

	@ManyToOne()
	@JoinColumn(name = "providerId")
	private ProviderEntity provider;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<CartItemEntity> cartItems;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<OrderBillDetailEntity> orderBillDetailList;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<ImportBillDetailEntity> importBillDetailList;

	public ProductEntity() {
		super();
	}

	public ProductEntity(Integer productId, String productName, double price, int quantity, String descript,
			String image, String unit, Boolean status, Date createAt, Date updateAt, CategoryEntity category,
			ProviderEntity provider) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.descript = descript;
		this.image = image;
		this.unit = unit;
		this.status = status;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.category = category;
		this.provider = provider;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public ProviderEntity getProvider() {
		return provider;
	}

	public void setProvider(ProviderEntity provider) {
		this.provider = provider;
	}

	public List<CartItemEntity> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemEntity> cartItems) {
		this.cartItems = cartItems;
	}

	public List<OrderBillDetailEntity> getOrderBillDetailList() {
		return orderBillDetailList;
	}

	public void setOrderBillDetailList(List<OrderBillDetailEntity> orderBillDetailList) {
		this.orderBillDetailList = orderBillDetailList;
	}

	public List<ImportBillDetailEntity> getImportBillDetailList() {
		return importBillDetailList;
	}

	public void setImportBillDetailList(List<ImportBillDetailEntity> importBillDetailList) {
		this.importBillDetailList = importBillDetailList;
	}

}
