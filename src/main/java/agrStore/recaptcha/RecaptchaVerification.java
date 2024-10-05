package agrStore.recaptcha;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RecaptchaVerification {
	public static final String verificationUrl = "https://www.google.com/recaptcha/api/siteverify";
	public static final String secretKey = "6LcQQlcqAAAAAHA_Oynjd0XzFaYNxy47Z35VIDcl";
	public static final String userAgent = "Mozilla/5.0";

	public static Boolean verify(String gRecaptchaResponse) throws IOException {
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			return Boolean.FALSE;
		}

		try {
			URL obj = new URL(verificationUrl);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			
			//Thêm request header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", userAgent);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			String postParams = "secret=" + secretKey + "&response=" + gRecaptchaResponse;
			
			//Gửi post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			
			Integer reponseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
	        
	        return jsonObject.get("success").getAsBoolean();
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
		
	}
}
