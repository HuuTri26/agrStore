package agrStore.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.AccountDAO;
import agrStore.entity.AccountEntity;
import agrStore.service.AccountService;

@Transactional
@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountDAO AccountDAO;

	@Override
	public void addAccount(AccountEntity acc) {
		AccountDAO.addAccount(acc);
		
	}

	@Override
	public void updateAccount(AccountEntity acc) {
		AccountDAO.updateAccount(acc);
		
	}

	@Override
	public AccountEntity getAccountByGmail(String gmail) {
		return AccountDAO.getAccountByGmail(gmail);
	}

	@Override
	public Long countAccontByAddressId(Integer id) {
		return AccountDAO.countAccountByAddressId(id);
	}

}
