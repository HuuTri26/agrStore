package agrStore.DAO;

import java.util.List;

import agrStore.entity.OrderBillEntity;

public interface OrderBillDAO {
	public void addOrderBill(OrderBillEntity orderBill);
	public void updateOrderBill(OrderBillEntity orderBill);
	public List<OrderBillEntity> getAllOrderBill();
	public OrderBillEntity getOrderBillById(Integer id);
	public int updateOrderStatus(Integer orderBillIdUpdate, int newOrderStatus);

}
