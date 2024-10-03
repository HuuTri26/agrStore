package agrStore.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agrStore.DAO.DistrictDAO;
import agrStore.entity.DistrictEntity;

@Repository
@Transactional
public class DistrictDAOImpl implements DistrictDAO{
	
	@Autowired
	SessionFactory factory;
	
	@Override
	public List<DistrictEntity> getListDistrictsByProvinceId(Integer pId) {
		List<DistrictEntity> districts = null;
		
		Session session = factory.getCurrentSession();
		String hql = "FROM DistrictEntity d WHERE d.province.id = :pId";
		Query query = session.createQuery(hql);
		query.setParameter("pId", pId);		
		districts = query.list();

		return districts;
	}

	@Override
	public DistrictEntity getDitrictById(Integer id) {
		DistrictEntity district = null;
		
		Session session = factory.getCurrentSession();
		String hql = "FROM DistrictEntity WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		district = (DistrictEntity) query.uniqueResult();
		
		return district;
	}

}
