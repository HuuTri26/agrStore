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
@Table(name = "Cart")
public class CartEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartId")
	private Integer cartId;

	@Column(name = "totalQuantity")
	private Integer totalQuantity;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountId")
	private AccountEntity account;

	@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
	private List<CartItemEntity> cartItemList;

	public CartEntity() {
		super();
	}

	public CartEntity(Integer cartId, Integer totalQuantity, AccountEntity account) {
		super();
		this.cartId = cartId;
		this.totalQuantity = totalQuantity;
		this.account = account;
	}
	
	public CartEntity(AccountEntity account) {
		super();
		this.account = account;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public List<CartItemEntity> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItemEntity> cartItemList) {
		this.cartItemList = cartItemList;
	}

	@Override
	public String toString() {
		return "CartEntity [cartId=" + cartId + ", totalQuantity=" + totalQuantity + "]";
	}
	
}
