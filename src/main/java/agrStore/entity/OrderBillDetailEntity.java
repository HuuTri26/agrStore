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
@Table(name = "OrderBillDetail")
public class OrderBillDetailEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderBillDetailId")
	private Integer orderBillDetailId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderBillId")
	private OrderBillEntity orderBill;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private ProductEntity product;
	
	@Column(name = "quantity")
	private int quantity;

	public OrderBillDetailEntity() {
		super();
	}

	public OrderBillDetailEntity(Integer orderBillDetailId, int quantity) {
		super();
		this.orderBillDetailId = orderBillDetailId;
		this.quantity = quantity;
	}

	public Integer getOrderBillDetailId() {
		return orderBillDetailId;
	}

	public void setOrderBillDetailId(Integer orderBillDetailId) {
		this.orderBillDetailId = orderBillDetailId;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
