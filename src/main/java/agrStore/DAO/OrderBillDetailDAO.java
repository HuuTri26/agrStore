package agrStore.DAO;

import java.util.List;

import agrStore.entity.OrderBillDetailEntity;

public interface OrderBillDetailDAO {
	public void addOrderBillDetail(OrderBillDetailEntity orderBilldt);
	public void updateOrderBillDetail(OrderBillDetailEntity orderBilldt);
	public void deleteOrderBillDetail(OrderBillDetailEntity orderBilldt);
	public OrderBillDetailEntity getOrderBillDetailById(Integer id);
	public List<OrderBillDetailEntity> getOrderBillDetailByOderBillId(Integer id);
	public List<OrderBillDetailEntity> getOrderBillDetailByProductIdAndAccountId(Integer pId, Integer aId);
}
