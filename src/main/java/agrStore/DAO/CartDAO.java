package agrStore.DAO;

import agrStore.entity.CartEntity;

public interface CartDAO {
	public void addCart(CartEntity cart);
	public void updateCart(CartEntity cart);
	public CartEntity getCartByAccountId(Integer aId);
}
