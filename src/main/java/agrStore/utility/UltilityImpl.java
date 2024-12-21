package agrStore.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.owasp.encoder.Encode;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.stereotype.Service;

import agrStore.entity.CartItemEntity;

@Service
public class UltilityImpl implements Ultility {

	private static final String ALGORITHM = "AES";

	// Kiểm tra định dạng gmail
	public Boolean isValidGmail(String gmail) {
		if (gmail.isEmpty()) {
			return true;
		} else {
			String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(gmail.trim());
			return matcher.matches();
		}
	}

	// Chuẩn hóa chuỗi
	public String standardize(String str) {
		str = str.trim();
		str = str.replaceAll("\\s+", " ");
		return str;
	}

	// Chuẩn hóa họ tên
	public String standardizeName(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		str = standardize(str);
		String temp[] = str.split(" ");
		str = "";
		for (int i = 0; i < temp.length; i++) {
			str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
			if (i < temp.length - 1)
				str += " ";
		}
		return str;
	}

	// Kiểm tra định dạng số điện thoại
	public Boolean isValidPhoneNumber(String phoneNumber) {
		String regex = "^[0-9]{10}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

	// Hàm băm mật khẩu (SHA-256)
	public String getHashPassword(String password) {
		try {
			// Create MessageDigest instance for SHA-256
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			// Add password bytes to digest
			md.update(password.getBytes());

			// Get the hash's bytes
			byte[] bytes = md.digest();

			// Convert bytes to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (byte aByte : bytes) {
				sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
			}

			// Get complete hashed password in hexadecimal format
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// Handle NoSuchAlgorithmException
			e.printStackTrace();
			return null;
		}
	}

	// Hàm sinh OTP
	public String generateOTP() {
		String alphabelt = "0123456789qwertyuiopasdfghjkzxcvbnmQWERTYUOPLKJHGFDSAZXCVBNM";

		String otp = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			otp += alphabelt.charAt(random.nextInt(60));
		}

		return otp;
	}

	@Override
	public String standardizeStreetName(String name) {
		// Xóa các khoảng trắng thừa
		name = name.trim().replaceAll("\\s+", " ");

		// Chuẩn hóa dấu phẩy: xóa khoảng trắng trước dấu phẩy và đảm bảo một khoảng
		// trắng sau dấu phẩy
		name = name.replaceAll("\\s*,\\s*", ", ");

		return standardizeName(name);
	}

	@Override
	public Boolean isValidStreetName(String name) {
		String regex = "^Số\\s[0-9/]+,\\sĐường\\s[A-Za-zÀ-Ỵà-ỹ0-9\\s]+$";
		return name.matches(regex);
	}

	@Override
	public void encryptFile(String inFile, String outFile, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);

		try (FileInputStream fis = new FileInputStream(inFile); FileOutputStream fos = new FileOutputStream(outFile)) {

			byte[] inputBytes = fis.readAllBytes();
			byte[] encryptedBytes = cipher.doFinal(inputBytes);

			fos.write(encryptedBytes);
		}

	}

	@Override
	public void decryptFile(String inFile, String outFile, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);

		try (FileInputStream fis = new FileInputStream(inFile); FileOutputStream fos = new FileOutputStream(outFile)) {

			byte[] encryptedBytes = fis.readAllBytes();
			byte[] dencryptedBytes = cipher.doFinal(encryptedBytes);

			fos.write(dencryptedBytes);
		}

	}

	@Override
	public SecretKey generateKey() throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
		keyGen.init(128);
		return keyGen.generateKey();
	}

	@Override
	public void saveKeyToKeyStore(SecretKey key, String alias, char[] keyStorePwd, String keyStorePath)
			throws Exception {
		KeyStore keyStore = KeyStore.getInstance("JCEKS");

		// Nếu file chưa tồn tại load null
		File keyStoreFile = new File(keyStorePath);
		try (FileInputStream fis = keyStoreFile.exists() ? new FileInputStream(keyStoreFile) : null) {
			keyStore.load(fis, keyStorePwd);
		}
		
		//Tạo Entry bảo vệ bằng password

	}

	@Override
	public SecretKey loadKeyFromKeyStore(String alias, char[] keyStorePwd, String ketStorePath) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public static Boolean hasUnselectedItems(List<CartItemEntity> cartItems) {
		return cartItems.stream().anyMatch(item -> !item.getIsSelected());
	}

	@Override
	public String XSSSanitizeHTML(String input) {
		PolicyFactory policy =  Sanitizers.FORMATTING.and(Sanitizers.LINKS);
		return policy.sanitize(input);
	}
	
	public static String XSSEscape4HTML(String input) {
	    return input == null ? null : Encode.forHtml(input);
	}

	public static String XSSEscape4JS(String input) {
		return input == null ? null : Encode.forJavaScript(input);
		
	}

	public static String XSSEscape4Url(String input) {
		return input == null ? null : Encode.forUriComponent(input);
	}

	public static String XSSEscape4CSS(String input) {
		return input == null ? null :Encode.forCssString(input);
	}

	public static String XSSEscape4XML(String input) {
		return input == null ? null : Encode.forXml(input);
	}

}
