package agrStore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.DistrictEntity;

@Service
@Transactional
public interface DistrictService {
	public List<DistrictEntity> getListDistrictsByProvinceId(Integer pId);
	public DistrictEntity getDistrictById(Integer id);
}
