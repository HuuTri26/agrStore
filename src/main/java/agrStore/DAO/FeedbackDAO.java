package agrStore.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import agrStore.entity.FeedbackEntity;
import agrStore.entity.ProductEntity;


public interface FeedbackDAO {
	public List<FeedbackEntity> getListFeedback();
	public FeedbackEntity getFeedbackById(Integer id);
	public Boolean deleteFeedback(FeedbackEntity feedback);
}
