package agrStore.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import agrStore.entity.FeedbackEntity;
import agrStore.entity.ProductEntity;


public interface FeedbackDAO {
	public void addFeedback(FeedbackEntity feedback);
	public void updateFeedback(FeedbackEntity feedback);
	public void mergeFeedback(FeedbackEntity feedback);
	public List<FeedbackEntity> getListFeedback();
	public FeedbackEntity getFeedbackById(Integer id);
	public Boolean deleteFeedback(FeedbackEntity feedback);
	public List<FeedbackEntity> getListFeedBackByProductId(Integer pId);
	public FeedbackEntity getFeedbackByOrderBillDetailId(Integer oDtId);
}
