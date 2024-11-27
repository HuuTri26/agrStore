package agrStore.DAO;

import java.util.List;

import agrStore.entity.ProviderEntity;

public interface ProviderDAO {
	public void addProvider(ProviderEntity provider);
	public void updateProvider(ProviderEntity provider);
	public List<ProviderEntity> getListProvider();
	public ProviderEntity getProviderById(Integer id);
	
	public Boolean deleteProvider(ProviderEntity provider);
	public Boolean disableProvider(ProviderEntity provider);
}
