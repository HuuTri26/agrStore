package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.CartItemDAO;
import agrStore.entity.CartItemEntity;
import agrStore.service.CartItemService;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	CartItemDAO CartItemDAO;

	@Override
	public void addCartItem(CartItemEntity cartItem) {
		CartItemDAO.addCartItem(cartItem);

	}

	@Override
	public void updateCartItem(CartItemEntity cartItem) {
		CartItemDAO.updateCartItem(cartItem);

	}

	@Override
	public void deleteCartItem(CartItemEntity cartItem) {
		CartItemDAO.deleteCartItem(cartItem);

	}

	@Override
	public List<CartItemEntity> getListCartItemByCartId(Integer cId) {
		return CartItemDAO.getListCartItemByCartId(cId);
	}

	@Override
	public CartItemEntity getCartItemById(Integer Id) {
		return CartItemDAO.getCartItemById(Id);
	}

	@Override
	public void deleteCartItem(List<CartItemEntity> cartTtems) {
		for (CartItemEntity item : cartTtems) {
			CartItemDAO.deleteCartItem(item);
		}

	}

	@Override
	public CartItemEntity getCartItemByProductIdAndCartId(Integer pId, Integer cId) {
		return CartItemDAO.getCartItemByProductIdAndCartId(pId, cId);
	}

	@Override
	public Double getTotalPriceofCartItems(List<CartItemEntity> cartItems) {
		// Tính tổng tiền giỏ hàng (chỉ sản phẩm được chọn)
		Double totalPrice = cartItems.stream().mapToDouble(cItem -> cItem.getProduct().getPrice() * cItem.getQuantity())
				.sum();

		return totalPrice;
	}

	@Override
	public List<CartItemEntity> getSelectedCartItems(List<CartItemEntity> cartItems) {
		return cartItems.stream().filter(CartItemEntity::getIsSelected).toList();
	}

	@Override
	public Integer getTotalQuantityOfCartItems(List<CartItemEntity> cartItems) {
		return cartItems.stream().mapToInt(cItem -> cItem.getQuantity()).sum();
	}

}
