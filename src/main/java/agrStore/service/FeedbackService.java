package agrStore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.FeedbackEntity;

@Transactional
@Service
public interface FeedbackService {
	public void addFeedBack(FeedbackEntity feedback);
	public void updateFeedBack(FeedbackEntity feeback);
	public List<FeedbackEntity> getListFeedback();
	public FeedbackEntity getFeedbackById(Integer id);
	public Boolean deleteFeedback(FeedbackEntity feedback);
	public List<FeedbackEntity> getListFeedBackByProductId(Integer pId);
	public FeedbackEntity getFeedbackByOrderBillDetailId(Integer oDtId);
	public Boolean isValidFeeback(FeedbackEntity feedback);
	public Long countFeedbackByStar(List<FeedbackEntity> feedbacks, Integer star);
}
