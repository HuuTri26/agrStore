package agrStore.entity;

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
	private double price;
	
	@ManyToOne()
	@JoinColumn(name="orderBillId")
	private OrderBillEntity orderBill;
	
	@ManyToOne()
	@JoinColumn(name="productId")
	private ProductEntity product;
	
	@OneToMany(mappedBy = "orderBillDetail", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	private List<FeedbackEntity> feedbackList;

	public OrderBillDetailEntity() {
		super();
	}

	public OrderBillDetailEntity(Integer orderBillDetailId, int quantity, double price) {
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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

	public List<FeedbackEntity> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<FeedbackEntity> feedbackList) {
		this.feedbackList = feedbackList;
	}
	
	
}
