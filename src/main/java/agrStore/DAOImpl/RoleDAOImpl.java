package agrStore.DAOImpl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.RoleDAO;
import agrStore.entity.RoleEntity;

@Transactional
@Repository
public class RoleDAOImpl implements RoleDAO{
	
	@Autowired
	SessionFactory factory;

	@Override
	public void addRole(RoleEntity role) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.save(role);
			t.commit();
		}catch (Exception e) {
			t.rollback();
			System.out.println("Error: " + e.toString() + "\nStacktrace:"); e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

	@Override
	public void updateRole(RoleEntity role) {
		Session session = factory.getCurrentSession();
	    try {
	        session.update(role);
	    } catch (Exception e) {
	        System.out.println("Error: " + e.toString() + "\nStacktrace:");
	        e.printStackTrace();
	    }
		
	}

	@Override
	public RoleEntity getRoleById(Integer id) {
		RoleEntity role = null;
		
		Session session = factory.getCurrentSession();
		String hql = "FROM RoleEntity WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		role = (RoleEntity) query.uniqueResult();
		
		return role;
	}

}
