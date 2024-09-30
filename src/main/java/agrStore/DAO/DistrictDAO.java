package agrStore.DAO;

import java.util.List;

import agrStore.entity.DistrictEntity;

public interface DistrictDAO {
	public List<DistrictEntity> getListDistrictsByProvinceId(Integer pId);
	public DistrictEntity getDitrictById(Integer id);
}
