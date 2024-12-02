package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.OrderBillDAO;
import agrStore.entity.OrderBillEntity;
import agrStore.service.OrderBillService;

@Transactional
@Service
public class OrderBillServiceImpl implements OrderBillService {

	@Autowired
	private OrderBillDAO orderBillDAO;

	@Override
	public void addOrderBill(OrderBillEntity orderBill) {
		orderBillDAO.addOrderBill(orderBill);

	}

	@Override
	public void updateOrderBill(OrderBillEntity orderBill) {
		orderBillDAO.updateOrderBill(orderBill);

	}

	@Override
	public List<OrderBillEntity> getAllOrderBill() {
		// TODO Auto-generated method stub
		List<OrderBillEntity> orderBillEntities = this.orderBillDAO.getAllOrderBill();
		return orderBillEntities;
	}

	@Override
	public OrderBillEntity getOrderBillById(Integer id) {
		// TODO Auto-generated method stub
		OrderBillEntity orderBill = this.orderBillDAO.getOrderBillById(id);
		return orderBill;
	}

	@Override
	public int updateOrderBillStatus(Integer orderBillIdUpdate, int newOrderStatus) {
		// TODO Auto-generated method stub
		return this.orderBillDAO.updateOrderStatus(orderBillIdUpdate, newOrderStatus);
	}

	@Override
	public long getNumberOrderBillForToday() {
		// TODO Auto-generated method stub
		return this.orderBillDAO.getNumberOrderBillForToday();
	}

}
