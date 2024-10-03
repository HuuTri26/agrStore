package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.WardDAO;
import agrStore.entity.WardEntity;
import agrStore.service.WardService;

@Service
@Transactional
public class WardServiceImpl implements WardService{
	
	@Autowired
	WardDAO WardDAO;

	@Override
	public List<WardEntity> getListWardByDistrictId(Integer dId) {
		return WardDAO.getListWardbyDistrictId(dId);
	}

	@Override
	public WardEntity getWardById(Integer id) {
		return WardDAO.getWardById(id);
	}
	
}
