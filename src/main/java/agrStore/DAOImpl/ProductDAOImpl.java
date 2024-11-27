package agrStore.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.ProductDAO;
import agrStore.entity.ProductEntity;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	SessionFactory factory;

	@Override
	public void addProduct(ProductEntity product) {
		Session session = factory.getCurrentSession();

		try {
			session.save(product);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
	}

	@Override
	public void updateProduct(ProductEntity product) {
		Session session = factory.getCurrentSession();
		try {
			session.update(product);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}

	}

	@Override
	public List<ProductEntity> getListProduct() {
		List<ProductEntity> products = null;

		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity WHERE status = 1";
		Query query = session.createQuery(hql);
		products = query.list();

		return products;
	}

	@Override
	public ProductEntity getProductById(Integer id) {
		ProductEntity product = null;

		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity WHERE productId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		product = (ProductEntity) query.uniqueResult();

		return product;
	}

	@Override
	public List<ProductEntity> getListProductByCategotyId(Integer cId) {
		List<ProductEntity> products = null;

		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p WHERE p.category.categoryId = :cId";
		Query query = session.createQuery(hql);
		query.setParameter("cId", cId);
		products = query.list();

		return products;
	}

	@Override
	public List<ProductEntity> getListProductByProviderId(Integer pId) {
		List<ProductEntity> products = null;

		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity p WHERE p.provider.id = :pId";
		Query query = session.createQuery(hql);
		query.setParameter("pId", pId);
		products = query.list();

		return products;
	}

}
