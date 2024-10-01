package agrStore.DAO;

import java.util.List;

import agrStore.entity.WardEntity;

public interface WardDAO {
	public List<WardEntity> getListWardbyDistrictId(Integer dId);
	public WardEntity getWardById(Integer id);
}
