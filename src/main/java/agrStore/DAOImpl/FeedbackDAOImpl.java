package agrStore.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.FeedbackDAO;
import agrStore.entity.FeedbackEntity;
import agrStore.entity.ProductEntity;

@Repository
@Transactional
public class FeedbackDAOImpl implements FeedbackDAO {
	@Autowired
	SessionFactory factory;

	@Override
	public List<FeedbackEntity> getListFeedback() {
		List<FeedbackEntity> feedbacks = null;

		Session session = factory.getCurrentSession();
		String hql = "FROM FeedbackEntity";
		Query query = session.createQuery(hql);
		feedbacks = query.list();

		return feedbacks;
	}
	
	@Override
	public FeedbackEntity  getFeedbackById(Integer id) {
		FeedbackEntity feedback = null;

		Session session = factory.getCurrentSession();
		String hql = "FROM FeedbackEntity f WHERE f.feedbackId = :feedbackId";
		Query query = session.createQuery(hql);
		query.setParameter("feedbackId", id);
		feedback =(FeedbackEntity) query.uniqueResult();
		return feedback;
	}
	
	@Override
	public Boolean deleteFeedback(FeedbackEntity feedback) {
		Boolean isSucess = Boolean.FALSE;
		Session session = factory.getCurrentSession();
		try {
			session.delete(feedback);
			isSucess = Boolean.TRUE;
		} catch (Exception e) {
			System.out.println("Error: " + e.toString() + "\nStacktrace:");
			e.printStackTrace();
		}
		return isSucess;
	}

}
