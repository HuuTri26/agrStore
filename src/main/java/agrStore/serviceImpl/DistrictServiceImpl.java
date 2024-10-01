package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.DistrictDAO;
import agrStore.entity.DistrictEntity;
import agrStore.service.DistrictService;

@Transactional
@Service
public class DistrictServiceImpl implements DistrictService{
	
	@Autowired
	DistrictDAO DistrictDAO;
	
	@Override
	public List<DistrictEntity> getListDistrictsByProvinceId(Integer pId) {
		return DistrictDAO.getListDistrictsByProvinceId(pId);
	}

	@Override
	public DistrictEntity getDistrictById(Integer id) {
		return DistrictDAO.getDitrictById(id);
	}
	
}
