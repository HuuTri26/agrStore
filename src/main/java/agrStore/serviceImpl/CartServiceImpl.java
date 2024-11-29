package agrStore.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.CartDAO;
import agrStore.entity.CartEntity;
import agrStore.service.CartService;

@Service
@Transactional
public class CartServiceImpl implements CartService{
	
	@Autowired
	CartDAO CartDAO;

	@Override
	public void addCart(CartEntity cart) {
		CartDAO.addCart(cart);
		
	}

	@Override
	public void updateCart(CartEntity cart) {
		CartDAO.updateCart(cart);
		
	}

	@Override
	public CartEntity getCartByAccountId(Integer aId) {
		return CartDAO.getCartByAccountId(aId);
	}
	
}
