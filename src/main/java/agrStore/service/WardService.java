package agrStore.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.WardEntity;

@Service
@Transactional
public interface WardService {
	public List<WardEntity> getListWardByDistrictId(Integer dId);
	public WardEntity getWardById(Integer id);
}
