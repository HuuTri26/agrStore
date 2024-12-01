package agrStore.DAO;

import java.util.List;

import agrStore.entity.OrderBillEntity;

public interface OrderBillDAO {
	
	public List<OrderBillEntity> getAllOrderBill();
	public OrderBillEntity getOrderBillById(Integer id);
	public int updateOrderStatus(Integer orderBillIdUpdate, int newOrderStatus);
	public long getNumberOrderBillForToday();

}
