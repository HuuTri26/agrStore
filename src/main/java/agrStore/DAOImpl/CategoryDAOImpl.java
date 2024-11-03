package agrStore.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.CategoryDAO;
import agrStore.entity.CategoryEntity;

@Repository
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	SessionFactory factory;

	@Override
	public void addCategory(CategoryEntity category) {
		Session session = factory.getCurrentSession();

		try {
			session.save(category);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}

	}

	@Override
	public void updateCategory(CategoryEntity category) {
		Session session = factory.getCurrentSession();
		try {
			session.update(category);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}

	}

	@Override
	public List<CategoryEntity> getListCategory() {
		List<CategoryEntity> categories = null;

		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity WHERE status = 1";
		Query query = session.createQuery(hql);
		categories = query.list();

		return categories;
	}

	@Override
	public CategoryEntity getCategotyById(Integer id) {
		CategoryEntity category = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity WHERE categoryId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		
		category = (CategoryEntity) query.uniqueResult();
		
		return category;
	}

}
