package agrStore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.ProvinceEntity;

@Service
@Transactional
public interface ProvinceService {
	public List<ProvinceEntity> getListProvinces();
	public ProvinceEntity getProvinceById(Integer id);
}
