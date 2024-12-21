package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.AccountDAO;
import agrStore.entity.AccountEntity;
import agrStore.service.AccountService;
import agrStore.utility.Ultility;

@Transactional
@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountDAO AccountDAO;
	
	@Autowired
	Ultility ultility;
	
	@Override
	public void addAccount(AccountEntity acc) {
		acc.setFullName(ultility.XSSSanitizeHTML(acc.getFullName()));
		
		AccountDAO.addAccount(acc);
		
	}

	@Override
	public void updateAccount(AccountEntity acc) {
		acc.setFullName(ultility.XSSSanitizeHTML(acc.getFullName()));
		
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

	@Override
	public List<AccountEntity> getAllCustomer() {
		// TODO Auto-generated method stub
		return this.AccountDAO.getAllCustomer();
	}

	@Override
	public AccountEntity getAccountById(Integer id) {
		// TODO Auto-generated method stub
		return this.AccountDAO.getAccountById(id);
	}

	@Override

	public int countAccountByRole(int role) {
		// TODO Auto-generated method stub
		return this.AccountDAO.countAccountByRole(role);
	}

	public List<AccountEntity> getAllStaff() {
		// TODO Auto-generated method stub
		return this.AccountDAO.getAllStaff();
	}
	
	@Override
	public boolean isExistAccount(String gmail, String password) {
		// TODO Auto-generated method stub
		return this.AccountDAO.isExistAccount(gmail, password);
	}
	


}
