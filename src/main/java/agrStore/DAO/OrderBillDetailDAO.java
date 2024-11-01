package agrStore.DAO;

import java.util.List;

import agrStore.entity.OrderBillDetailEntity;

public interface OrderBillDetailDAO {
	public List<OrderBillDetailEntity> getOrderBillDetailByOderBillId(Integer id);
}
