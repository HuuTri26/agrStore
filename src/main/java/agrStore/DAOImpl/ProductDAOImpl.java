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
	public List<ProductEntity> getProductsByProviderId(Integer providerId) {
		// TODO Auto-generated method stub
		List<ProductEntity> products = null;
		Session session = this.factory.getCurrentSession();
		/* String hql = "FROM ProductEntity p WHERE p.provider.id = :id"; */
		String hql = "FROM ProductEntity p WHERE p.provider.id = :id AND p.status = true";
		Query query = session.createQuery(hql);
		query.setParameter("id", providerId);
		products = query.list();
		return products;
	}

	@Override
	public void updateProductQuantity(Integer productId, int quantity) {
		// TODO Auto-generated method stub
		Session session = this.factory.getCurrentSession();
		try {
			String hql = "UPDATE ProductEntity p SET p.quantity = p.quantity + :quantity WHERE p.productId = :id";
			Query query = session.createQuery(hql);
			query.setParameter("quantity", quantity);
			query.setParameter("id", productId);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}

	}

	@Override
	public List<Object[]> getTheMostPurchasedProduct() {
		Session session = this.factory.getCurrentSession();
		String hql = "SELECT p.productId, p.productName, SUM(d.quantity) AS totalQuantity " + "FROM OrderBillDetailEntity d "
				+ "JOIN d.product p " + "GROUP BY p.productId, p.productName " + "ORDER BY SUM(d.quantity) DESC";
		try {
			Query query = session.createQuery(hql);
			query.setFirstResult(0); // OFFSET
			query.setMaxResults(5); // FETCH NEXT 5 ROWS ONLY
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null; // Trả về null nếu có lỗi
		}
	}
	@Override
	public List<ProductEntity> searchProductByName(String productName) {
	    Session session = this.factory.getCurrentSession();
	    if (productName == null || productName.isEmpty()) {
	        return List.of(); // Trả về danh sách rỗng nếu tên sản phẩm không hợp lệ
	    }
	    String hql = "FROM ProductEntity p WHERE p.productName LIKE :productName";
	    Query query = session.createQuery(hql);
	    query.setParameter("productName", "%" + productName + "%");
	    return query.list();
	}


}
