package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.ProviderDAO;
import agrStore.entity.ProviderEntity;
import agrStore.service.ProviderService;

@Transactional
@Service
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	ProviderDAO ProviderDAO;

	@Override
	public void addProvider(ProviderEntity provider) {
		ProviderDAO.addProvider(provider); // gọi phương thức từ instance
	}

	@Override
	public void updateProvider(ProviderEntity provider) {
		ProviderDAO.updateProvider(provider);
	}

	@Override
	public List<ProviderEntity> getListProvider() {
		return ProviderDAO.getListProvider();
	}

	@Override
	public ProviderEntity getProviderById(Integer id) {
		return ProviderDAO.getProviderById(id);
	}

	@Override
	public Boolean deleteProvider(ProviderEntity provider) {
		return ProviderDAO.deleteProvider(provider);
	}

	public Boolean disableProvider(ProviderEntity provider) {
		return ProviderDAO.disableProvider(provider);
	}

}
