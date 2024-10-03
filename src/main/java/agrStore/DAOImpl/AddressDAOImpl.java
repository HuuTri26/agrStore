package agrStore.DAOImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.AddressDAO;
import agrStore.entity.AddressEntity;

@Transactional
@Repository
public class AddressDAOImpl implements AddressDAO{
	
	@Autowired
	SessionFactory factory;

	@Override
	public void addAddress(AddressEntity addr) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.save(addr);
			t.commit();
		}catch (Exception e) {
			t.rollback();
			System.out.println("Error: " + e.toString() + "\nStacktrace:"); e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

	@Override
	public void updateAddress(AddressEntity addr) {
		Session session = factory.getCurrentSession();
	    try {
	        session.update(addr);
	    } catch (Exception e) {
	        System.out.println("Error: " + e.toString() + "\nStacktrace:");
	        e.printStackTrace();
	    }
		
	}

}
