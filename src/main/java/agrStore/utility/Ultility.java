package agrStore.utility;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

@Service
public interface Ultility {
	public Boolean isValidGmail(String gmail);
	public String standardize(String str);
	public String standardizeName(String str);
	public Boolean isValidPhoneNumber(String phoneNumber);
	public String getHashPassword(String password);
	public String generateOTP();
	public String standardizeStreetName(String name);
	public Boolean isValidStreetName(String name);
	
	public void encryptFile(String inFile, String outFile, SecretKey key) throws Exception;
	public void decryptFile(String inFile, String outFile, SecretKey key) throws Exception;
	public SecretKey generateKey() throws Exception;
	public void saveKeyToKeyStore(SecretKey key, String alias, char[] keyStorePwd, String keyStorePath) throws Exception;
	public SecretKey loadKeyFromKeyStore(String alias, char[] keyStorePwd, String ketStorePath) throws Exception;
	
	public String XSSSanitizeHTML(String input);
	
	
	public Boolean isPasswordValid(String password);
	
	public String generateRandomPassword();
}
