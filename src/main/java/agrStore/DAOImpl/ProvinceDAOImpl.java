package agrStore.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import agrStore.DAO.ProvinceDAO;
import agrStore.entity.ProvinceEntity;

@Transactional
@Repository
public class ProvinceDAOImpl implements ProvinceDAO{
	
	@Autowired
	SessionFactory factory;

	@Override
	public List<ProvinceEntity> getListProvinces() {
		List<ProvinceEntity> provinces = null;
		
		Session session = factory.getCurrentSession();
		String hql = "FROM ProvinceEntity";
		Query query = session.createQuery(hql);
		provinces = query.list();
		
		return provinces;
	}

	@Override
	public ProvinceEntity getProvinceById(Integer id) {
		ProvinceEntity province = null;
		
		Session session = factory.getCurrentSession();
		String hql = "FROM ProvinceEntity WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		province = (ProvinceEntity) query.uniqueResult();
		
		return province;
	}
	
}
