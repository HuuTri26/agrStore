package agrStore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.ProviderEntity;

@Transactional
@Service
public interface ProviderService {
	public void addProvdier(ProviderEntity provider);
	public void updateProvider(ProviderEntity provider);
	public List<ProviderEntity> getListProvider();
	public ProviderEntity getProviderById(Integer id);
}