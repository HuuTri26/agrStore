package agrStore.DAO;

import java.util.List;

import agrStore.entity.OrderBillEntity;

public interface OrderBillDAO {
	public void addOrderBill(OrderBillEntity orderBill);
	public void updateOrderBill(OrderBillEntity orderBill);
	public void deleteOrderBill(OrderBillEntity orderBill);
	public List<OrderBillEntity> getAllOrderBill();
	public OrderBillEntity getOrderBillById(Integer id);
	public int updateOrderStatus(Integer orderBillIdUpdate, int newOrderStatus);
	public long getNumberOrderBillForToday();

	public long getTodayRevenue();
	public List<OrderBillEntity> getOrderBillForToday();

	public List<OrderBillEntity> getOrderBillsByAccountId(Integer aId);
	public List<OrderBillEntity> getPendingOrderBillByAccountId(Integer aId, Integer status);
	
	public int deleteOrderBillUnConfirmedById(Integer ordeBillId);

}
