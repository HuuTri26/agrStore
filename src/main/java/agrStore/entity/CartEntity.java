package agrStore.entity;

import java.util.List;

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
	private Integer id;
	
	@Column(name = "productQuantity")
	private Integer productQuantity;
	
	@OneToOne
	@JoinColumn(name = "customerId")
	private CustomerEntity customer;
	
	@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
	private List<CartItemEntity> cartItems;
	
	public CartEntity() {
		super();
	}

	public CartEntity(Integer id, Integer productQuantity, CustomerEntity customer, List<CartItemEntity> cartItems) {
		super();
		this.id = id;
		this.productQuantity = productQuantity;
		this.customer = customer;
		this.cartItems = cartItems;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public List<CartItemEntity> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemEntity> cartItems) {
		this.cartItems = cartItems;
	}
	
}
