package agrStore.DAO;

import java.util.List;

import agrStore.entity.ProviderEntity;

public interface ProviderDAO {
	public void addProvider(ProviderEntity provider);
	public void updateProvider(ProviderEntity provider);
	public ProviderEntity getInforById(int id);
	public List<ProviderEntity> getListProviders();
	public Boolean deleteProvider(ProviderEntity provider);
	public Boolean disableProvider(ProviderEntity provider);
}
