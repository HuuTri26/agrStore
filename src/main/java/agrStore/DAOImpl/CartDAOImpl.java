package agrStore.DAOImpl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.CartDAO;
import agrStore.entity.CartEntity;

@Repository
@Transactional
public class CartDAOImpl implements CartDAO{
	
	@Autowired
	SessionFactory factory;
	
	@Override
	public void addCart(CartEntity cart) {
		Session session = factory.getCurrentSession();
		try {
			session.save(cart);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateCart(CartEntity cart) {
		Session session = factory.getCurrentSession();
		try {
			session.update(cart);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		
	}

	@Override
	public CartEntity getCartByAccountId(Integer aId) {
		CartEntity cart = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM CartEntity c WHERE c.account.accountId = :aId";
		
		try {
			Query query = session.createQuery(hql);
			query.setParameter("aId", aId);
			
			cart = (CartEntity) query.uniqueResult();
		}catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:"); e.printStackTrace();
		}
		
		return cart;
	}

}
