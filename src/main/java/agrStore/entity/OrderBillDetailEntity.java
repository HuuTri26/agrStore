package agrStore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderBillDetail")
public class OrderBillDetailEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderBillDetailId")
	private Integer orderBillDetailId;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
	private int price;
	
	@ManyToOne()
	@JoinColumn(name="orderBillId")
	private OrderBillEntity orderBill;
	
	@ManyToOne()
	@JoinColumn(name="productId")
	private ProductEntity product;

	public OrderBillDetailEntity() {
		super();
	}

	public OrderBillDetailEntity(Integer orderBillDetailId, int quantity, int price) {
		super();
		this.orderBillDetailId = orderBillDetailId;
		this.quantity = quantity;
		this.price = price;
	}

	public Integer getOrderBillDetailId() {
		return orderBillDetailId;
	}

	public void setOrderBillDetailId(Integer orderBillDetailId) {
		this.orderBillDetailId = orderBillDetailId;
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

	public OrderBillEntity getOrderBill() {
		return orderBill;
	}

	public void setOrderBill(OrderBillEntity orderBill) {
		this.orderBill = orderBill;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	
	
}
