package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.ProvinceDAO;
import agrStore.entity.ProvinceEntity;
import agrStore.service.ProvinceService;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {
	
	@Autowired
	ProvinceDAO ProvinceDAO;

	@Override
	public List<ProvinceEntity> getListProvinces() {
		return ProvinceDAO.getListProvinces();
	}

	@Override
	public ProvinceEntity getProvinceById(Integer id) {
		return ProvinceDAO.getProvinceById(id);
	}

}
