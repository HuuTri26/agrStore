package agrStore.DAO;

import agrStore.entity.AddressEntity;

public interface AddressDAO {
	public void addAddress(AddressEntity addr);
	public void updateAddress(AddressEntity addr);
	public AddressEntity getAddressById(Integer id);

}
