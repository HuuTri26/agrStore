package agrStore.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.ImportBillDAO;
import agrStore.entity.ImportBillEntity;

@Transactional
@Repository
public class ImportBillDAOImpl implements ImportBillDAO{
	
	@Autowired
	SessionFactory factory;

	@Override
	public List<ImportBillEntity> getAllImportBill() {
		// TODO Auto-generated method stub
		Session session = this.factory.getCurrentSession();
		String hql = "FROM ImportBillEntity";
		Query query = session.createQuery(hql);
		List<ImportBillEntity> impoBillEntities = query.list();
		return impoBillEntities;
	}

	@Override
	public ImportBillEntity getImportBillById(Integer id) {
		// TODO Auto-generated method stub
		ImportBillEntity importBillEntity = null;
		Session session = this.factory.getCurrentSession();
		String hql = "FROM ImportBillEntity WHERE importBillId = :id";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			importBillEntity = (ImportBillEntity) query.uniqueResult();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		return importBillEntity;
	}

	@Override
	public int addImportBill(ImportBillEntity importBill) {
		// TODO Auto-generated method stub
		Session session = this.factory.getCurrentSession();
		// Transaction t = session.beginTransaction();
		try {
			session.save(importBill);
			// t.commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			// t.rollback();
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
			return 0;
		}
		
	}

}
