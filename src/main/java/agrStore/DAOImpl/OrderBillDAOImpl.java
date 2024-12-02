package agrStore.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.OrderBillDAO;
import agrStore.entity.OrderBillEntity;

@Transactional
@Repository
public class OrderBillDAOImpl implements OrderBillDAO {

	@Autowired
	SessionFactory factory;

	@Override
	public void addOrderBill(OrderBillEntity orderBill) {
		Session session = factory.getCurrentSession();
		try {
			session.save(orderBill);
			 session.flush();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}

	}

	@Override
	public void updateOrderBill(OrderBillEntity orderBill) {
		Session session = factory.getCurrentSession();
		try {
			session.update(orderBill);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}

	}

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

	@Override
	public int updateOrderStatus(Integer orderBillIdUpdate, int newOrderStatus) {
		// TODO Auto-generated method stub
		Session session = this.factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			OrderBillEntity orderBill = (OrderBillEntity) session.get(OrderBillEntity.class, orderBillIdUpdate);
			if (orderBill != null) {
				orderBill.setStatusOrder(newOrderStatus);
				session.update(orderBill);
				transaction.commit();
			} else {
				System.out.println("OrderBillID: " + orderBillIdUpdate + " không tồn tại");
				return 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			System.out.println("Error: " + e.toString());
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

}
