package agrStore.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.ProductDAO;
import agrStore.entity.ProductEntity;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	public List<ProductEntity> getAllProduct() {
		// TODO Auto-generated method stub
		Session session = this.factory.getCurrentSession();
		String hql = "FROM ProductEntity WHERE status = :statusProduct";
		Query query = session.createQuery(hql);
		query.setParameter("statusProduct", true);
		List<ProductEntity> products = query.list();
		return products;
	}

	@Override
	public Integer addProduct(ProductEntity product) {
		// TODO Auto-generated method stub
		Session session = this.factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(product);
			transaction.commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			System.out.println("Error: " + e.toString());
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

}
