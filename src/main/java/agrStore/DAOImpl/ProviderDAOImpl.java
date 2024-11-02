package agrStore.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.ProviderDAO;
import agrStore.entity.AddressEntity;
import agrStore.entity.ProviderEntity;
import agrStore.entity.ProvinceEntity;


@Transactional
@Repository
public class ProviderDAOImpl implements ProviderDAO{

	@Autowired
	SessionFactory factory;
	
	@Override
	public void addProvider(ProviderEntity provider) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			
			session.save(provider);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		} finally {
			session.close();
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
	public ProviderEntity getInforById(int id) {
	    Session session = factory.getCurrentSession();
	    String hql = "FROM ProviderEntity WHERE id = :id";
	    Query query = session.createQuery(hql);
	    query.setParameter("id", id);
	    
	    ProviderEntity provider = (ProviderEntity) query.uniqueResult();
	    return provider;
	}

	
	@Override
	public List<ProviderEntity> getListProviders() {
		List<ProviderEntity> providers = null;
		
		Session session = factory.getCurrentSession();
		String hql = "FROM ProviderEntity";
		Query query = session.createQuery(hql);
		providers = query.list();
		
		System.out.println(providers);
		return providers;
	}
	
	@Override
	public Boolean deleteProvider(ProviderEntity provider) {
		Boolean isSucess = Boolean.FALSE;
		Session session = factory.getCurrentSession();
		try {
			session.delete(provider);
			isSucess = Boolean.TRUE;
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		return isSucess;
	}
	
	
	@Override
	public Boolean disableProvider(ProviderEntity provider) {
	    Boolean isSuccess = Boolean.FALSE;
	    Session session = factory.getCurrentSession();
	    try {
	        // Cập nhật trạng thái của provider thành 'disabled'
	        provider.setStatus(false);
	        session.update(provider);
	        isSuccess = Boolean.TRUE;
	    } catch (Exception e) {
	        System.out.println("Error: " + e.toString() + "\nStacktrace:");
	        e.printStackTrace();
	    }
	    return isSuccess;
	}
	
}
