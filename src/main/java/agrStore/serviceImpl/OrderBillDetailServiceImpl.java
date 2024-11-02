package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.OrderBillDetailDAO;
import agrStore.entity.OrderBillDetailEntity;
import agrStore.service.OrderBillDetailService;

@Transactional
@Service
public class OrderBillDetailServiceImpl implements OrderBillDetailService{
	
	@Autowired
	private OrderBillDetailDAO orderBillDetailDAO;

	@Override
	public List<OrderBillDetailEntity> getAllOrderBillDetailByOrderBillID(Integer id) {
		// TODO Auto-generated method stub
		List<OrderBillDetailEntity> orderBillDetailEntities = this.orderBillDetailDAO.getOrderBillDetailByOderBillId(id);
		return orderBillDetailEntities;
	}
	
	

}
