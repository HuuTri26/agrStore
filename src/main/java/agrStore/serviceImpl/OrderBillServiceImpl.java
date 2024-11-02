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
public class OrderBillServiceImpl implements OrderBillService{
	
	@Autowired
	private OrderBillDAO orderBillDAO;

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

}
