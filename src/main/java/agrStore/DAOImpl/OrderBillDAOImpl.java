package agrStore.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.OrderBillDAO;
import agrStore.entity.OrderBillEntity;

@Transactional
@Repository
public class OrderBillDAOImpl implements OrderBillDAO{
	
	@Autowired
	SessionFactory factory;

	@Override
	public List<OrderBillEntity> getAllOrderBill() {
		// TODO Auto-generated method stub
		Session session = this.factory.getCurrentSession();
		String hql = "FROM OrderBillEntity";
		Query query = session.createQuery(hql);
		List<OrderBillEntity> orderBills = query.list();
		return orderBills;
	}

	@Override
	public OrderBillEntity getOrderBillById(Integer id) {
		// TODO Auto-generated method stub
		OrderBillEntity orderBill = null;
		Session session = this.factory.getCurrentSession();
		String hql = "FROM OrderBillEntity WHERE orderBillId = :id";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			orderBill = (OrderBillEntity) query.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		return orderBill;
	}

}
