package agrStore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.CartItemEntity;

@Service
@Transactional
public interface CartItemService {
	public void addCartItem(CartItemEntity cartItem);
	public void updateCartItem(CartItemEntity cartItem);
	public void deleteCartItem(CartItemEntity cartItem);
	public List<CartItemEntity> getListCartItemByCartId(Integer cId);
	public CartItemEntity getCartItemById(Integer Id);
	public void deleteCartItem(List<CartItemEntity> items);
	public CartItemEntity getCartItemByProductIdAndCartId(Integer pId, Integer cId);
	public Double getTotalPriceofCartItems(List<CartItemEntity> cartItems);
	public List<CartItemEntity> getSelectedCartItems(List<CartItemEntity> cartItems);
	public Integer getTotalQuantityOfCartItems(List<CartItemEntity> cartItems);
}
