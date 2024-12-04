package agrStore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.OrderBillEntity;

@Service
@Transactional
public interface OrderBillService {
	public void addOrderBill(OrderBillEntity orderBill);
	public void updateOrderBill(OrderBillEntity orderBill);
	public void deleteOrderBill(OrderBillEntity orderBill);
	public void deleteListOrderBill(List<OrderBillEntity> orderBills);
	public List<OrderBillEntity> getAllOrderBill();
	public OrderBillEntity getOrderBillById(Integer id);
	public int updateOrderBillStatus(Integer orderBillIdUpdate, int newOrderStatus);
	public long getNumberOrderBillForToday();
	public List<OrderBillEntity> getPendingOrderBillByAccountId(Integer aId, Integer status);

}
