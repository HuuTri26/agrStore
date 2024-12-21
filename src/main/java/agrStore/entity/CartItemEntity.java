package agrStore.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CartItem")
public class CartItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartItemId")
	private Integer cartItemId;

	@Column(name = "quantity")
	private Integer quantity;
	
	@Transient
	private Boolean isSelected;

	@ManyToOne()
	@JoinColumn(name = "cartId")
	private CartEntity cart;

	@ManyToOne()
	@JoinColumn(name = "productId")
	private ProductEntity product;

	public CartItemEntity() {
		super();
		this.isSelected = Boolean.FALSE;
	}

	public CartItemEntity(Integer cartItemId, Integer quantity, CartEntity cart, ProductEntity product,
			Boolean isSelected) {
		super();
		this.cartItemId = cartItemId;
		this.quantity = quantity;
		this.cart = cart;
		this.product = product;
	}

	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public CartEntity getCart() {
		return cart;
	}

	public void setCart(CartEntity cart) {
		this.cart = cart;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    CartItemEntity that = (CartItemEntity) obj;
	    return Objects.equals(cartItemId, that.cartItemId);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(cartItemId);
	}



}
