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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderBill")
public class OrderBillEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderBillId")
	private Integer orderBillId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountId")
	private AccountEntity account;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId")
	private CustomerEntity customer;
	
	@Column(name = "statusOrder")
	private int statusOrder;
	
	@Column(name="orderTime")
    private LocalDateTime orderTime;
	
	@Column(name = "orderPrice")
	private int orderPrice;
	
	@OneToMany(mappedBy = "orderBill", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private List<OrderBillDetailEntity> orderBillDetailList;
	
	@OneToOne()
	@JoinColumn(name = "feedbackId")
	private FeedbackEntity feedback;

	public OrderBillEntity() {
		super();
	}

	public OrderBillEntity(Integer orderBillId, int statusOrder, LocalDateTime orderTime, int orderPrice) {
		super();
		this.orderBillId = orderBillId;
		this.statusOrder = statusOrder;
		this.orderTime = orderTime;
		this.orderPrice = orderPrice;
	}

	public Integer getOrderBillId() {
		return orderBillId;
	}

	public void setOrderBillId(Integer orderBillId) {
		this.orderBillId = orderBillId;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public int getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(int statusOrder) {
		this.statusOrder = statusOrder;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public List<OrderBillDetailEntity> getOrderBillDetailList() {
		return orderBillDetailList;
	}

	public void setOrderBillDetailList(List<OrderBillDetailEntity> orderBillDetailList) {
		this.orderBillDetailList = orderBillDetailList;
	}

	public FeedbackEntity getFeedback() {
		return feedback;
	}

	public void setFeedback(FeedbackEntity feedback) {
		this.feedback = feedback;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
	
	
	

}
