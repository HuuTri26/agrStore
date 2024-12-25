package agrStore.utility;

import org.springframework.stereotype.Service;

import agrStore.entity.AccountEntity;

@Service
public interface LogginAttempManager {
	public void incrementLoginAttempts(String gmail);
	public void resetLoginAttempts(String gmail);
	public Boolean isAccountLocked(String gmail);
	public void checkAndLockAccount(String gmail, AccountEntity account) ;
}
