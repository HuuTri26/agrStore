package agrStore.DAO;

import java.util.List;

import agrStore.entity.ProvinceEntity;

public interface ProvinceDAO {
	public List<ProvinceEntity> getListProvinces();
	public ProvinceEntity getProvinceById(Integer id);
}
