package agrStore.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class AccountUltilityImpl implements AccountUltility{

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

		//Hàm băm mật khẩu (SHA-256)
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

		//Hàm sinh OTP
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
		    
		    // Chuẩn hóa dấu phẩy: xóa khoảng trắng trước dấu phẩy và đảm bảo một khoảng trắng sau dấu phẩy
			name = name.replaceAll("\\s*,\\s*", ", ");
		    
		    return standardizeName(name);
		}

		@Override
		public Boolean isValidStreetName(String name) {
			String regex = "^Số\\s[0-9/]+,\\sĐường\\s[A-Za-zÀ-Ỵà-ỹ0-9\\s]+$";
		    return name.matches(regex);
		}
}
