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
@Table(name = "OrderBill")
public class OrderBillEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderBillId")
	private Integer orderBillId;
	
	@Column(name = "statusOrder")
	private int statusOrder;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "orderTime")
	private Date orderTime;
	
	@Column(name = "totalQuantity")
	private int totalQuantity;
	
	@Column(name = "totalPrice")
	private int totalPrice;
	
	@ManyToOne()
	@JoinColumn(name="accountId")
	private AccountEntity account;
	
	@OneToMany(mappedBy = "orderBill", fetch = FetchType.LAZY, 
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<OrderBillDetailEntity> orderBillDetailList;

	public OrderBillEntity() {
		super();
	}

	public OrderBillEntity(Integer orderBillId, int statusOrder, Date orderTime, int totalQuantity, int totalPrice) {
		super();
		this.orderBillId = orderBillId;
		this.statusOrder = statusOrder;
		this.orderTime = orderTime;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
	}

	public Integer getOrderBillId() {
		return orderBillId;
	}

	public void setOrderBillId(Integer orderBillId) {
		this.orderBillId = orderBillId;
	}

	public int getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(int statusOrder) {
		this.statusOrder = statusOrder;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public List<OrderBillDetailEntity> getOrderBillDetailList() {
		return orderBillDetailList;
	}

	public void setOrderBillDetailList(List<OrderBillDetailEntity> orderBillDetailList) {
		this.orderBillDetailList = orderBillDetailList;
	}
	
	
	

}
