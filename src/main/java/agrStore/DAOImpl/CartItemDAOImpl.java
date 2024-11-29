package agrStore.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.CartItemDAO;
import agrStore.entity.CartItemEntity;

@Repository
@Transactional
public class CartItemDAOImpl implements CartItemDAO{
	
	@Autowired
	SessionFactory factory;

	@Override
	public void addCartItem(CartItemEntity cartItem) {
		Session session = factory.getCurrentSession();
		try {
			session.save(cartItem);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateCartItem(CartItemEntity cartItem) {
		Session session = factory.getCurrentSession();
		try {
			session.update(cartItem);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteCartItem(CartItemEntity cartItem) {
		Session session = factory.getCurrentSession();
		try {
			session.delete(cartItem);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<CartItemEntity> getListCartItemByCartId(Integer cId) {
		List<CartItemEntity> cartItems = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM CartItemEntity i WHERE i.cart.cartId = :cId";
		Query query = session.createQuery(hql);
		query.setParameter("cId", cId);
		cartItems = query.list();
		
		return cartItems;
	}

	@Override
	public CartItemEntity getCartItemById(Integer Id) {
		CartItemEntity cartItem = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM CartItemEntity WHERE cartItemId = :Id";
		Query query = session.createQuery(hql);
		query.setParameter("Id", Id);
		cartItem = (CartItemEntity) query.uniqueResult();
		
		return cartItem;
	}

	@Override
	public CartItemEntity getCartItemByProductIdAndCartId(Integer pId, Integer cId) {
		CartItemEntity cartItem = null;
		
		Session session = factory.getCurrentSession();
		String hql = "FROM CartItemEntity i WHERE i.product.productId = :pId AND i.cart.cartId = :cId";
		Query query = session.createQuery(hql);
		query.setParameter("pId", pId);
		query.setParameter("cId", cId);
		cartItem = (CartItemEntity) query.uniqueResult();
		
		return cartItem;
	}
	
}
