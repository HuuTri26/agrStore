package agrStore.utility;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import javax.crypto.SecretKey;

public class FileEncryptRunner {
	
	private static UltilityImpl util = new UltilityImpl();
	
	public static void main(String[] args) {
        try {
            
            // Đường dẫn file
            String inputFile = "C:\\Users\\Windows\\OneDrive\\Desktop\\DeAnChoCa3Mon\\agrStore\\src\\main\\webapp\\WEB-INF\\configs\\database.properties"; // File gốc
            String encryptedFile = "C:\\Users\\Windows\\OneDrive\\Desktop\\DeAnChoCa3Mon\\agrStore\\src\\main\\webapp\\WEB-INF\\configs\\database-encrypted.properties"; // File mã hóa
            String keyFile = "C:\\Users\\Windows\\OneDrive\\Desktop\\DeAnChoCa3Mon\\agrStore\\src\\main\\webapp\\WEB-INF\\configs\\secret.key"; // File lưu khóa

//            // 1. Tạo khóa AES và lưu vào file
//            SecretKey secretKey = util.generateKey();
//            util.saveKeyToFile(secretKey, keyFile);
//            System.out.println("Khóa AES đã được tạo và lưu vào file: " + keyFile);
//
//            // 2. Mã hóa file
//            util.encryptFile(inputFile, encryptedFile, secretKey);
//            System.out.println("File đã được mã hóa và lưu tại: " + encryptedFile);
            
            // Đọc file nhị phân
            byte[] keyBytes = Files.readAllBytes(Paths.get(keyFile));

            // In nội dung nhị phân (byte) ra màn hình
            System.out.println("Dữ liệu nhị phân của SecretKey:");
            for (byte b : keyBytes) {
                System.out.printf("%02X ", b); // In theo dạng Hexadecimal
            }
            System.out.println();

            // Chuyển sang dạng Base64 (dễ đọc hơn)
            String keyAsBase64 = Base64.getEncoder().encodeToString(keyBytes);
            System.out.println("SecretKey dưới dạng Base64: " + keyAsBase64);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
