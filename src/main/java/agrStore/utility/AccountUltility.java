package agrStore.utility;

import org.springframework.stereotype.Service;

@Service
public interface AccountUltility {
	public Boolean isValidGmail(String gmail);
	public String standardize(String str);
	public String standardizeName(String str);
	public Boolean isValidPhoneNumber(String phoneNumber);
	public String getHashPassword(String password);
	public String generateOTP();
}
