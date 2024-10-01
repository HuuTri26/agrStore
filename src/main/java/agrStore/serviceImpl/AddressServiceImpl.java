package agrStore.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.AddressDAO;
import agrStore.entity.AddressEntity;
import agrStore.service.AddressService;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	AddressDAO AddressDAO;

	@Override
	public void addAddress(AddressEntity addr) {
		AddressDAO.addAddress(addr);
		
	}

	@Override
	public void updateAddress(AddressEntity addr) {
		AddressDAO.updateAddress(addr);
		
	}

}
