package agrStore.service;

import java.util.List;

import agrStore.entity.OrderBillDetailEntity;

public interface OrderBillDetailService {
	public void addOrderBillDetail(OrderBillDetailEntity orderBilldt);
	public void updateOrderBillDetail(OrderBillDetailEntity orderBilldt);
	public List<OrderBillDetailEntity> getAllOrderBillDetailByOrderBillID(Integer id);
	public Double geTotalPriceOfOrderBillDetails(List<OrderBillDetailEntity> orderBillDetails);
}
