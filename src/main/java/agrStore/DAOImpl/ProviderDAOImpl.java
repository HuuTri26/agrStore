package agrStore.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.ProviderDAO;
import agrStore.entity.ProviderEntity;

@Repository
@Transactional
public class ProviderDAOImpl implements ProviderDAO{
	
	@Autowired
	SessionFactory factory;

	@Override
	public void addProvider(ProviderEntity provider) {
		Session session = factory.getCurrentSession();

		try {
			session.save(provider);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateProvider(ProviderEntity provider) {
		Session session = factory.getCurrentSession();
		try {
			session.update(provider);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<ProviderEntity> getListProvider() {
		List<ProviderEntity> providers = null;
		
		Session session = factory.getCurrentSession();
		String hql = "FROM ProviderEntity WHERE status = 1";
		Query query = session.createQuery(hql);
		providers = query.list();
		
		return providers;
	}

	@Override
	public ProviderEntity getProviderById(Integer id) {
		ProviderEntity provider = null;
		
		Session session = factory.getCurrentSession();
		String hql = "FROM ProviderEntity WHERE providerId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		provider = (ProviderEntity) query.uniqueResult();
		
		return provider;
	}
	
}
