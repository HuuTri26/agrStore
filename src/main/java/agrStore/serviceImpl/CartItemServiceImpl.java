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
public class CartItemServiceImpl implements CartItemService{
	
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
	public void deleteSelectedCartItem(List<CartItemEntity> items) {
		for (CartItemEntity i : items) {
			if(i.getIsSelected()) {
				CartItemDAO.deleteCartItem(i);
			}
		}
			
	}

	@Override
	public CartItemEntity getCartItemByProductIdAndCartId(Integer pId, Integer cId) {
		return CartItemDAO.getCartItemByProductIdAndCartId(pId, cId);
	}

}
