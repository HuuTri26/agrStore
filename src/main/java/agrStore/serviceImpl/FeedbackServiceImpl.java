package agrStore.serviceImpl;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agrStore.DAO.FeedbackDAO;
import agrStore.DAO.ProductDAO;
import agrStore.entity.FeedbackEntity;
import agrStore.entity.ProductEntity;
import agrStore.service.FeedbackService;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	FeedbackDAO feedbackDAO;


	@Override
	public List<FeedbackEntity> getListFeedback() {
		return feedbackDAO.getListFeedback();
	}
	

	@Override
	public FeedbackEntity getFeedbackById(Integer id) {
		return feedbackDAO.getFeedbackById(id);
	}
	
	@Override
    public Boolean deleteFeedback(FeedbackEntity feedback) {
        return feedbackDAO.deleteFeedback(feedback);
    }
}
