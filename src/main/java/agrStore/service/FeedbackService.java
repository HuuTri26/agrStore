package agrStore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.FeedbackEntity;
import agrStore.entity.ProductEntity;

@Transactional
@Service
public interface FeedbackService {
	public List<FeedbackEntity> getListFeedback();
	public FeedbackEntity getFeedbackById(Integer id);
	public Boolean deleteFeedback(FeedbackEntity feedback);
}
