package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.OrderBillDAO;
import agrStore.DAO.OrderBillDetailDAO;
import agrStore.entity.OrderBillDetailEntity;
import agrStore.entity.OrderBillEntity;
import agrStore.service.OrderBillService;

@Transactional
@Service
public class OrderBillServiceImpl implements OrderBillService {

    @Autowired
    private OrderBillDAO orderBillDAO;

    @Autowired
    OrderBillDetailDAO orderBillDetailDAO;

    @Override
    public void addOrderBill(OrderBillEntity orderBill) {
        orderBillDAO.addOrderBill(orderBill);
    }

    @Override
    public void updateOrderBill(OrderBillEntity orderBill) {
        orderBillDAO.updateOrderBill(orderBill);
    }

    @Override
    public List<OrderBillEntity> getAllOrderBill() {
        return this.orderBillDAO.getAllOrderBill();
    }

    @Override
    public OrderBillEntity getOrderBillById(Integer id) {
        return this.orderBillDAO.getOrderBillById(id);
    }

    @Override
    public int updateOrderBillStatus(Integer orderBillIdUpdate, int newOrderStatus) {
        return this.orderBillDAO.updateOrderStatus(orderBillIdUpdate, newOrderStatus);
    }

    @Override
    public long getNumberOrderBillForToday() {
        return this.orderBillDAO.getNumberOrderBillForToday();
    }

    @Override
    public long getTodayRevenue() {
        return this.orderBillDAO.getTodayRevenue();
    }

    @Override
    public List<OrderBillEntity> getOrderBillToday() {
        return this.orderBillDAO.getOrderBillForToday();
    }

    @Override
    public void deleteOrderBill(OrderBillEntity orderBill) {
        orderBillDAO.deleteOrderBill(orderBill);
    }

    @Override
    public void deleteListOrderBill(List<OrderBillEntity> orderBills) {
        System.out.println("==> Delete {0} orderBills".formatted(orderBills.size()));
        for (OrderBillEntity order : orderBills) {
            List<OrderBillDetailEntity> details = orderBillDetailDAO.getOrderBillDetailByOderBillId(order.getOrderBillId());
            System.out.println("==> Delete {0} orderBillDetails".formatted(details.size()));
            for (OrderBillDetailEntity detail : details) {
                orderBillDetailDAO.deleteOrderBillDetail(detail);
                System.out.println("==> Delete orderBillDetail successfully!");
            }
            deleteOrderBill(order);
            System.out.println("==> Delete orderBill successfully!");
        }
    }

    @Override
    public List<OrderBillEntity> getPendingOrderBillByAccountId(Integer aId, Integer status) {
        return orderBillDAO.getPendingOrderBillByAccountId(aId, status);
    }

    @Override
    public List<OrderBillEntity> getOrderBillsByAccountId(Integer aId) {
        return orderBillDAO.getOrderBillsByAccountId(aId);
    }

    // Bổ sung phương thức phân trang
    @Override
    public List<OrderBillEntity> getOrderBillsByAccountIdPaged(Integer aId, int page, int pageSize) {
        return orderBillDAO.getOrderBillsByAccountIdPaged(aId, page, pageSize);
    }

    @Override
    public int countOrdersByAccountId(Integer aId) {
        return orderBillDAO.countOrdersByAccountId(aId);
    }
}
