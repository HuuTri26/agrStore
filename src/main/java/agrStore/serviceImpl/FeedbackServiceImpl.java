package agrStore.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agrStore.DAO.FeedbackDAO;
import agrStore.entity.FeedbackEntity;
import agrStore.service.FeedbackService;
import agrStore.utility.Ultility;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	FeedbackDAO feedbackDAO;
	
	@Autowired
	Ultility ultility;

	@Override
	public void addFeedBack(FeedbackEntity feedback) {
		feedback.setComment(ultility.XSSSanitizeHTML(feedback.getComment()));
		feedbackDAO.addFeedback(feedback);

	}

	@Override
	public void updateFeedBack(FeedbackEntity feeback) {
		if (feeback != null && !isValidFeeback(feeback)) {
			feeback.setComment(ultility.XSSSanitizeHTML(feeback.getComment()));
			
			feedbackDAO.updateFeedback(feeback);
		}
		feedbackDAO.mergeFeedback(feeback);

	}

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

	@Override
	public List<FeedbackEntity> getListFeedBackByProductId(Integer pId) {
		return feedbackDAO.getListFeedBackByProductId(pId);
	}

	@Override
	public FeedbackEntity getFeedbackByOrderBillDetailId(Integer oDtId) {
		return feedbackDAO.getFeedbackByOrderBillDetailId(oDtId);
	}

	@Override
	public Boolean isValidFeeback(FeedbackEntity feedback) {
		return feedback.getOrderBillDetail() != null && feedback.getAccount() != null && feedback.getStar() >= 0
				&& feedback.getStar() <= 5 && (feedback.getComment() == null || feedback.getComment().length() <= 500);
	}

	@Override
	public Long countFeedbackByStar(List<FeedbackEntity> feedbacks, Integer star) {
		return feedbacks.stream().filter(fb -> fb.getStar().equals(star)).count();
	}

	
}
