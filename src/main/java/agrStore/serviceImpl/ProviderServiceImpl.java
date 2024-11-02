package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.ProviderDAO;
import agrStore.entity.ProviderEntity;
import agrStore.service.ProviderService;

@Service
@Transactional
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    ProviderDAO providerDao; // đặt tên biến viết thường

    @Override
    public void addProvider(ProviderEntity provider) {
        providerDao.addProvider(provider); // gọi phương thức từ instance
    }

    @Override 
    public void updateProvider(ProviderEntity provider) {
        providerDao.updateProvider(provider);
    }
    
    @Override 
    public ProviderEntity getInforById(int id) {
        return providerDao.getInforById(id);
    }
    
    @Override 
    public List<ProviderEntity> getListProviders() {
        return providerDao.getListProviders();
    }

    @Override
    public Boolean deleteProvider(ProviderEntity provider) {
        return providerDao.deleteProvider(provider);
    }
    
    public Boolean disableProvider(ProviderEntity provider) {
    	return providerDao.disableProvider(provider);
    }
}
