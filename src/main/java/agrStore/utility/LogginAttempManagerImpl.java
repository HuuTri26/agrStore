package agrStore.utility;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import agrStore.entity.AccountEntity;

@Service
public class LogginAttempManagerImpl implements LogginAttempManager {
	private static final int MAX_ATTEMPTS = 5;
	private static final long LOCK_TIME_MINUTES = 5;

	// Store login attempts in memory - will reset when server restarts
	private Map<String, Integer> loginAttempts = new ConcurrentHashMap<>();
	private Map<String, LocalDateTime> lockTimers = new ConcurrentHashMap<>();

	public void incrementLoginAttempts(String gmail) {
		loginAttempts.merge(gmail, 1, Integer::sum);
	}

	public void resetLoginAttempts(String gmail) {
		loginAttempts.remove(gmail);
		lockTimers.remove(gmail);
	}

	public Boolean isAccountLocked(String gmail) {
		LocalDateTime lockTime = lockTimers.get(gmail);
		if (lockTime != null) {
			if (LocalDateTime.now().isAfter(lockTime)) {
				// Lock period has expired, reset everything
				resetLoginAttempts(gmail);
				return false;
			}
			return true;
		}
		return false;
	}

	public void checkAndLockAccount(String gmail, AccountEntity account) {
		int attempts = loginAttempts.getOrDefault(gmail, 0);
		if (attempts >= MAX_ATTEMPTS) {
			account.setStatus(false);
			lockTimers.put(gmail, LocalDateTime.now().plusMinutes(LOCK_TIME_MINUTES));

			// Schedule unlock task
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					account.setStatus(true);
					resetLoginAttempts(gmail);
				}
			}, LOCK_TIME_MINUTES * 60 * 1000);
		}
	}
}
