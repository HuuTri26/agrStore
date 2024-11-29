package agrStore.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.CartEntity;

@Service
@Transactional
public interface CartService {
	public void addCart(CartEntity cart);
	public void updateCart(CartEntity cart);
	public CartEntity getCartByAccountId(Integer aId);
}
