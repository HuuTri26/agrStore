package agrStore.DAO;

import java.util.List;

import agrStore.entity.CartItemEntity;

public interface CartItemDAO {
	public void addCartItem(CartItemEntity cartItem);
	public void updateCartItem(CartItemEntity cartItem);
	public void deleteCartItem(CartItemEntity cartItem);
	public List<CartItemEntity> getListCartItemByCartId(Integer cId);
	public CartItemEntity getCartItemById(Integer Id);
	public CartItemEntity getCartItemByProductIdAndCartId(Integer pId, Integer cId);
}	
