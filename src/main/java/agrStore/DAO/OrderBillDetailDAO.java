package agrStore.DAO;

import java.util.List;

import agrStore.entity.OrderBillDetailEntity;

public interface OrderBillDetailDAO {
	public void addOrderBillDetail(OrderBillDetailEntity orderBilldt);
	public void updateOrderBillDetail(OrderBillDetailEntity orderBilldt);
	public List<OrderBillDetailEntity> getOrderBillDetailByOderBillId(Integer id);
}
