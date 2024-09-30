package agrStore.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.WardDAO;
import agrStore.entity.WardEntity;

@Repository
@Transactional
public class WardDAOImpl implements WardDAO{
	
	@Autowired
	SessionFactory factory;

	@Override
	public List<WardEntity> getListWardbyDistrictId(Integer dId) {
		List<WardEntity> wards = null;
		
		Session session = factory.getCurrentSession();
		String hql = "FROM WardEntity w WHERE w.district.id = :dId";
		Query query = session.createQuery(hql);
		query.setParameter("dId", dId);
		wards = query.list();
		
		return wards;
	}

	@Override
	public WardEntity getWardById(Integer id) {
		WardEntity ward = null;
		
		Session session = factory.getCurrentSession();
		String hql = "FROM WardEntity WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		ward = (WardEntity) query.uniqueResult();
		
		return ward;
	}

}
