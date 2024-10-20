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


@Transactional
@Repository
public class CategoryDAOImpl implements CategoryDAO{
	
	@Autowired
	private SessionFactory factory;

	@Override
	public List<CategoryEntity> getAllCategory() {
		// TODO Auto-generated method stub
		Session session = this.factory.getCurrentSession();
		String hql = "FROM CategoryEntity WHERE status = :statusCategory";
		Query query = session.createQuery(hql);
		query.setParameter("statusCategory", true);
		List<CategoryEntity> categories = query.list();
		return categories;
	}

}
