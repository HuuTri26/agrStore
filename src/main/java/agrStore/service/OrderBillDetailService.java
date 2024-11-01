package agrStore.service;

import java.util.List;

import agrStore.entity.OrderBillDetailEntity;

public interface OrderBillDetailService {
	public List<OrderBillDetailEntity> getAllOrderBillDetailByOrderBillID(Integer id);
}
