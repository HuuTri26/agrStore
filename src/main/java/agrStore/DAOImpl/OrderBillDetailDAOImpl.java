package agrStore.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.OrderBillDetailDAO;
import agrStore.entity.OrderBillDetailEntity;

@Transactional
@Repository
public class OrderBillDetailDAOImpl implements OrderBillDetailDAO {

	@Autowired
	SessionFactory factory;

	@Override
	public void addOrderBillDetail(OrderBillDetailEntity orderBilldt) {
		Session session = factory.getCurrentSession();
		try {
			session.save(orderBilldt);
			session.flush();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}

	}

	@Override
	public void updateOrderBillDetail(OrderBillDetailEntity orderBilldt) {
		Session session = factory.getCurrentSession();
		try {
			session.update(orderBilldt);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}

	}

	@Override
	public void deleteOrderBillDetail(OrderBillDetailEntity orderBilldt) {
		Session session = factory.getCurrentSession();
		try {
			session.delete(orderBilldt);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}

	}

	@Override
	public List<OrderBillDetailEntity> getOrderBillDetailByOderBillId(Integer id) {
		// TODO Auto-generated method stub
		Session session = this.factory.getCurrentSession();
		String hql = "FROM OrderBillDetailEntity o WHERE o.orderBill.orderBillId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<OrderBillDetailEntity> orderBillDetailEntities = query.list();
		return orderBillDetailEntities;
	}

	@Override
	public List<OrderBillDetailEntity> getOrderBillDetailByProductIdAndAccountId(Integer pId, Integer aId) {
		List<OrderBillDetailEntity> orderBillDetails = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderBillDetailEntity o WHERE o.product.productId = :pId AND o.orderBill.account.accountId = :aId";
		Query query = session.createQuery(hql);
		query.setParameter("pId", pId);
		query.setParameter("aId", aId);
		
		orderBillDetails = query.list();
		
		return orderBillDetails;
	}

	@Override
	public OrderBillDetailEntity getOrderBillDetailById(Integer id) {
		OrderBillDetailEntity orderBillDetail = null;
		
		Session session = this.factory.getCurrentSession();
		String hql = "FROM OrderBillDetailEntity WHERE orderBillDetailId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		
		orderBillDetail = (OrderBillDetailEntity) query.uniqueResult();
		return orderBillDetail;
	}
	
}
