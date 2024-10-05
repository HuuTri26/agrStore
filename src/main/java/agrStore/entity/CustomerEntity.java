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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerId")
	private Integer customerId;

	@OneToOne()
	@JoinColumn(name = "accountId")
	private AccountEntity account;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cartId")
	private CartEntity cart;

	@OneToMany(mappedBy = "customer", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private List<OrderBillEntity> orderBillList;

	public CustomerEntity() {
		super();
	}

	public CustomerEntity(Integer customerId, AccountEntity account, CartEntity cart,
			List<OrderBillEntity> orderBillList) {
		super();
		this.customerId = customerId;
		this.account = account;
		this.cart = cart;
		this.orderBillList = orderBillList;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public CartEntity getCart() {
		return cart;
	}

	public void setCart(CartEntity cart) {
		this.cart = cart;
	}

	public List<OrderBillEntity> getOrderBillList() {
		return orderBillList;
	}

	public void setOrderBillList(List<OrderBillEntity> orderBillList) {
		this.orderBillList = orderBillList;
	}

}
