package agrStore.DAO;

import java.util.List;

import agrStore.entity.AccountEntity;

public interface AccountDAO {
	public void addAccount(AccountEntity acc);
	public void updateAccount(AccountEntity acc);
	public AccountEntity getAccountByGmail(String gmail);
	public Long countAccountByAddressId(Integer id);
	public List<AccountEntity> getAllCustomer();
	public AccountEntity getAccountById(Integer id);

	public int countAccountByRole(int role);


    public List<AccountEntity> getAllStaff();
    public boolean isExistAccount(String gmail, String password);

}
