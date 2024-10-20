package agrStore.serviceImpl;

import java.util.List;

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
	public List<AccountEntity> getAllCustomer() {
		// TODO Auto-generated method stub
		List<AccountEntity> customers = this.AccountDAO.getAllCustomer();
		return customers;
	}

	@Override
	public AccountEntity getAccountById(Integer id) {
		// TODO Auto-generated method stub
		AccountEntity account = this.AccountDAO.getAccountById(id);
		return account;
	}

}
