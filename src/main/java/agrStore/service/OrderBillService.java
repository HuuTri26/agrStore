package agrStore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.OrderBillEntity;

@Service
@Transactional
public interface OrderBillService {
	
	public List<OrderBillEntity> getAllOrderBill();
	public OrderBillEntity getOrderBillById(Integer id);
	public int updateOrderBillStatus(Integer orderBillIdUpdate, int newOrderStatus);

}
