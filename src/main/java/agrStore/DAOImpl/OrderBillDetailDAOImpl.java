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
public class OrderBillDetailDAOImpl implements OrderBillDetailDAO{
	
	@Autowired
	SessionFactory factory;

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

}
