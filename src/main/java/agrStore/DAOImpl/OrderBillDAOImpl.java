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
	public void deleteOrderBill(OrderBillEntity orderBill) {
		Session session = factory.getCurrentSession();
		try {
			if (!session.contains(orderBill)) {
				orderBill = (OrderBillEntity) session.merge(orderBill);
			}
			session.delete(orderBill);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}

	}

	@Override
	public List<OrderBillEntity> getAllOrderBill() {
		// TODO Auto-generated method stub
//		Session session = this.factory.getCurrentSession();
//		String hql = "FROM OrderBillEntity";
//		Query query = session.createQuery(hql);
//		List<OrderBillEntity> orderBills = query.list();
//		return orderBills;
		Session session = this.factory.getCurrentSession();
		String hql = "FROM OrderBillEntity ob WHERE ob.statusOrder != 6"; // Thêm điều kiện lọc
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

	@Override
	public long getNumberOrderBillForToday() {
		// TODO Auto-generated method stub
		Session session = this.factory.getCurrentSession();
		String hql = "SELECT COUNT(*) " + "FROM OrderBillEntity o "
				+ "WHERE CONVERT(DATE, o.orderTime) = CONVERT(DATE, GETDATE())";
		Query query = session.createQuery(hql);
		long result = (long) query.uniqueResult();
		return result;
	}

	@Override
	public long getTodayRevenue() {
		// TODO Auto-generated method stub
		Session session = this.factory.getCurrentSession();

//		String hql = "SELECT SUM(o.totalPrice) " + "FROM OrderBillEntity o "
//				+ "WHERE CONVERT(date, o.orderTime) = CONVERT(date, GETDATE())";
		String hql = "SELECT SUM(o.totalPrice) " + "FROM OrderBillEntity o "
				+ "WHERE CONVERT(date, o.orderTime) = CONVERT(date, GETDATE()) " + "AND o.statusOrder = 5";
		try {
			Query query = session.createQuery(hql);
			Double result = (Double) query.uniqueResult();
			return result != null ? result.longValue() : 0L; // Chuyển Double về long
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0L; // Trả về 0 nếu có lỗi
		}

	}

	@Override
	public List<OrderBillEntity> getOrderBillForToday() {
		// TODO Auto-generated method stub
		Session session = this.factory.getCurrentSession();
		String hql = "FROM OrderBillEntity o " + "WHERE CONVERT(date, o.orderTime) = CONVERT(date, GETDATE())";
		try {
			Query query = session.createQuery(hql);
			List<OrderBillEntity> result = query.list();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null; // Trả về danh sách rỗng nếu xảy ra lỗi
		}
	}

	public List<OrderBillEntity> getPendingOrderBillByAccountId(Integer aId, Integer status) {
		List<OrderBillEntity> orderBills = null;
		Session session = this.factory.getCurrentSession();
		String hql = "FROM OrderBillEntity o WHERE o.account.accountId = :aId AND o.statusOrder = :status";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("aId", aId);
			query.setParameter("status", status);
			orderBills = (List<OrderBillEntity>) query.list();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		return orderBills;

	}

	@Override

	public int deleteOrderBillUnConfirmedById(Integer orderBillId) {
		// hàm này ta sẽ set OrderBill status thành 6 -> tức đã xóa
		// TODO Auto-generated method stub
		Session session = this.factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			OrderBillEntity orderBill = (OrderBillEntity) session.get(OrderBillEntity.class, orderBillId);

			if (orderBill != null) {
				// Kiểm tra nếu statusOrder == 1
				if (orderBill.getStatusOrder() == 1) {
					orderBill.setStatusOrder(6); // Đặt trạng thái thành 6 (đã xóa)
					session.update(orderBill);
					transaction.commit();
					return 1; // Trả về 1 nếu cập nhật thành công
				} else {
					System.out.println("OrderBillID: " + orderBillId + " không thể sửa vì statusOrder != 1");
					return -1; // Trả về -1 nếu không thỏa điều kiện
				}
			} else {
				System.out.println("OrderBillID: " + orderBillId + " không tồn tại");
				return 0; // Trả về 0 nếu OrderBill không tồn tại
			}
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("Error: " + e.toString());
			return 0; // Trả về 0 nếu xảy ra lỗi
		} finally {
			session.close();
		}
	}

	public List<OrderBillEntity> getOrderBillsByAccountId(Integer aId) {
		List<OrderBillEntity> orderBills = null;
		Session session = this.factory.getCurrentSession();
		String hql = "FROM OrderBillEntity o WHERE o.account.accountId = :aId";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("aId", aId);
			orderBills = (List<OrderBillEntity>) query.list();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		return orderBills;
	}

}
