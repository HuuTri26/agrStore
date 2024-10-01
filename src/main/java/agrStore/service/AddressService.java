package agrStore.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.AddressEntity;

@Service
@Transactional
public interface AddressService {
	public void addAddress(AddressEntity addr);
	public void updateAddress(AddressEntity addr);
}
