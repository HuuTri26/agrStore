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
import javax.persistence.Transient;

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
	private Double totalPrice;
	
	private enum Status {
		PENDING(1, "Pending"), CONFIRMED(2, "Confirmed"), NOT_SHIPPED(3, "Not Shipped"), SHIPPED(4, "Shipped"),
		COMPLETED(5, "Completed"), CANCELED(6, "Canceled");

		private final int code;
		private final String description;

		Status(int code, String description) {
			this.code = code;
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

		public static String getDescriptionByCode(int code) {
			for (Status status : Status.values()) {
				if (status.code == code) {
					return status.description;
				}
			}
			return "Unknown Status";
		}
	}

	/*
	 * @Column(name = "employeeId", nullable = true) private Integer employeeId;
	 */

	@ManyToOne()
	@JoinColumn(name = "accountId")
	private AccountEntity account;

	@OneToMany(mappedBy = "orderBill", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	private List<OrderBillDetailEntity> orderBillDetailList;

	public OrderBillEntity() {
		super();
	}

	public OrderBillEntity(Integer orderBillId, int statusOrder, Date orderTime, int totalQuantity, double totalPrice) {
		// Integer employeeId

		super();
		this.orderBillId = orderBillId;
		this.statusOrder = statusOrder;
		this.orderTime = orderTime;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		// this.employeeId = employeeId;
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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/*
	 * public Integer getEmployeeId() { return employeeId; }
	 * 
	 * public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId;
	 * }
	 */

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

	public String getStatus() {
        return Status.getDescriptionByCode(this.statusOrder);
    }

}
