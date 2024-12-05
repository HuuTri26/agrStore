package agrStore.DAOImpl;


import java.awt.desktop.QuitEvent;

import java.util.List;

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
		} catch (Exception e) {
			t.rollback();
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}

	}

	@Override
	public void updateAccount(AccountEntity acc) {
		Session session = factory.getCurrentSession();
		try {
			session.update(acc);
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
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public Long countAccountByAddressId(Integer id) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT COUNT(a) FROM AccountEntity a WHERE a.address.id = :id";
		Long count = 0L;
		try {
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			count = (Long) query.uniqueResult();
			if (count == null) {
				count = 0L;
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<AccountEntity> getAllCustomer() {
		// TODO Auto-generated method stub
		Session session = this.factory.getCurrentSession();
		String hql = "FROM AccountEntity a WHERE a.role.id = :roleId";
		Query query = session.createQuery(hql);
		query.setParameter("roleId", 3);
		List<AccountEntity> customers = query.list();
		return customers;
	}

	@Override
	public AccountEntity getAccountById(Integer id) {
		// TODO Auto-generated method stub
		AccountEntity account = null;
		Session session = this.factory.getCurrentSession();
		String hql = "FROM AccountEntity WHERE accountId = :id";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			account = (AccountEntity) query.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		return account;
	}

	@Override

	public int countAccountByRole(int role) {
		// TODO Auto-generated method stub
		Session session = this.factory.getCurrentSession();
		String hql = "SELECT COUNT(a) FROM AccountEntity a WHERE a.role.id = :roleId";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("roleId", role);
			Long count = (long) query.uniqueResult();
			return count != null ? count.intValue() : 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0; // Trả về 0 nếu xảy ra lỗi
		}

	public List<AccountEntity> getAllStaff() {
		// TODO Auto-generated method stub
		List<AccountEntity> staffs = null;
		Session session = this.factory.getCurrentSession();
		String hql = "FROM AccountEntity a WHERE a.role.id = :roleId";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("roleId", 2);
			staffs = query.list();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		return staffs;
	}
	
	@Override
	public boolean isExistAccount(String gmail, String password) {
		AccountEntity account = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM AccountEntity WHERE gmail = :gmail AND password = :password";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("gmail", gmail);
			query.setParameter("password", password);
			
			account = (AccountEntity) query.uniqueResult();
			
		}catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:"); e.printStackTrace();
		}
		return (account != null)? true : false;

	}

}
