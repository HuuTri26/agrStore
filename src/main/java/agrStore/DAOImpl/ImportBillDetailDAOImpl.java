package agrStore.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.ImportBillDetailDAO;
import agrStore.entity.ImportBillDetailEntity;

@Transactional
@Repository
public class ImportBillDetailDAOImpl implements ImportBillDetailDAO{
	
	@Autowired
	SessionFactory factory;

	@Override
	public List<ImportBillDetailEntity> getImportBillDetailByImportBillId(Integer importBillID) {
		// TODO Auto-generated method stub
		Session session = this.factory.getCurrentSession();
		String hql = "FROM ImportBillDetailEntity  i WHERE i.importBill.importBillId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", importBillID);
		List<ImportBillDetailEntity> importBillDetailEntities = query.list();
		return importBillDetailEntities;
	}

	@Override
	public void addImportBillDetail(ImportBillDetailEntity importBillDetail) {
		// TODO Auto-generated method stub
		Session session = this.factory.getCurrentSession();
		try {
			session.save(importBillDetail);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		
	}

}
