package agrStore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.AccountEntity;

@Service
@Transactional
public interface AccountService {
	public void addAccount(AccountEntity acc);
	public void updateAccount(AccountEntity acc);
	public AccountEntity getAccountByGmail(String gmail);
	public Long countAccontByAddressId(Integer id);
	public List<AccountEntity> getAllCustomer();
	public AccountEntity getAccountById(Integer id);
	public List<AccountEntity> getAllStaff();
	public boolean isExistAccount(String gmail, String password);
}
