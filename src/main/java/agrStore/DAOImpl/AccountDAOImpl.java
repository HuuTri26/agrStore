package agrStore.DAOImpl;


import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.AccountDAO;
import agrStore.entity.AccountEntity;

@Transactional
@Repository
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	SessionFactory factory;

	@Override
	public void addAccount(AccountEntity acc) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.save(acc);
			t.commit();
		}catch (Exception e) {
			t.rollback();
			System.out.println("Error: " + e.toString() + "\nStacktrace:"); e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

	@Override
	public void updateAccount(AccountEntity acc) {
		Session session = factory.getCurrentSession();
	    try {
	        session.merge(acc);
	    } catch (Exception e) {
	        System.out.println("Error: " + e.toString() + "\nStacktrace:");
	        e.printStackTrace();
	    }
		
	}

	@Override
	public AccountEntity getAccountByGmail(String gmail) {
		AccountEntity account = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM AccountEntity WHERE gmail = :gmail";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("gmail", gmail);
			
			account = (AccountEntity) query.uniqueResult();
		}catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:"); e.printStackTrace();
		}
		return account;
	}

}
