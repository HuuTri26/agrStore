package agrStore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


import agrStore.entity.AddressEntity;
import agrStore.entity.ProviderEntity;


@Service
@Transactional
public interface ProviderService {
	public void addProvider(ProviderEntity provider);
	public void updateProvider(ProviderEntity provider);
	public List<ProviderEntity> getListProvider();
	public ProviderEntity getProviderById(Integer id);
	public Boolean deleteProvider(ProviderEntity provider);
	public Boolean disableProvider(ProviderEntity provider);
}

