package agrStore.DAO;

import agrStore.entity.AccountEntity;

public interface AccountDAO {
	public void addAccount(AccountEntity acc);
	public void updateAccount(AccountEntity acc);
	public AccountEntity getAccountByGmail(String gmail);
}
