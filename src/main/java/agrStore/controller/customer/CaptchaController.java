package agrStore.controller.customer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/captcha/")
public class CaptchaController {

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void showCaptchaImg(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// Thiết lập thông số cho ảnh captcha
		response.setContentType("image/jpg");
		int len = 6; // Số ký tự
		int height = 50; // Chiều cao ảnh
		int width = 160; // Chiều rộng ảnh

		// Thiết lập các phông chữ cho ký tự captcha
		Font[] fonts = { new Font("Arial", Font.ITALIC, 32), new Font("Courier", Font.BOLD, 32),
				new Font("Verdana", Font.ITALIC, 32), new Font("Georgia", Font.BOLD, 32),
				new Font("Times New Roman", Font.ITALIC, 32), new Font("Comic Sans MS", Font.BOLD, 32),
				new Font("Tahoma", Font.ITALIC, 32), new Font("Century Gothic", Font.BOLD, 32),
				new Font("Futura", Font.ITALIC, 32), new Font("Helvetica", Font.BOLD, 32),
				new Font("Impact", Font.ITALIC, 32) };

		Random randChar = new Random();
		String imageCode = (Long.toString(Math.abs(randChar.nextLong()), 36)).substring(0, len);

		// Tạo ảnh với BufferedImage
		BufferedImage biImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY); // Chuyển thành ảnh
																								// grayscale
		Graphics2D g2dImage = (Graphics2D) biImage.getGraphics();

		// Thiết lập chế độ anti-aliasing để ký tự rõ hơn
		g2dImage.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Tạo nền màu trắng
		g2dImage.setColor(Color.WHITE);
		g2dImage.fillRect(0, 0, width, height);
		
		// Thêm nhiễu với các đường thẳng màu xám
		for (int i = 0; i < 10; i++) {
			g2dImage.setColor(new Color(randChar.nextInt(200), randChar.nextInt(200), randChar.nextInt(200)));
			int x1 = randChar.nextInt(width);
			int y1 = randChar.nextInt(height);
			int x2 = randChar.nextInt(width);
			int y2 = randChar.nextInt(height);
			g2dImage.drawLine(x1, y1, x2, y2);
		}

		// Thêm các vòng tròn nhiễu màu xám
		for (int i = 0; i < 20; i++) {
			g2dImage.setColor(new Color(randChar.nextInt(200), randChar.nextInt(200), randChar.nextInt(200)));
			int x = randChar.nextInt(width);
			int y = randChar.nextInt(height);
			int radius = randChar.nextInt(15);
			g2dImage.drawOval(x, y, radius, radius);
		}

		// Vẽ các ký tự captcha lên ảnh
		for (int i = 0; i < len; i++) {
			// Random phông chữ và kích thước
			Font randomFont = fonts[randChar.nextInt(fonts.length)];
			int randomFontSize = 24 + randChar.nextInt(16); // Random size từ 24 đến 40
			g2dImage.setFont(randomFont.deriveFont((float) randomFontSize));

			// Chọn màu đen cho ký tự
			g2dImage.setColor(Color.BLACK);

			// Đặt vị trí cho ký tự, mỗi ký tự có độ lệch ngẫu nhiên
			int x = 25 * i + 20;
			int y = 30 + randChar.nextInt(10);

			// Làm méo mó các ký tự bằng cách xoay một góc nhỏ ngẫu nhiên
			int angle = randChar.nextInt(20) - 10; // Xoay trong khoảng từ -10 đến 10 độ
			g2dImage.rotate(Math.toRadians(angle), x, y);

			// Vẽ ký tự lên ảnh
			g2dImage.drawString(imageCode.substring(i, i + 1), x, y);

			// Reset lại gốc tọa độ sau khi xoay
			g2dImage.rotate(-Math.toRadians(angle), x, y);
		}

		// Xuất ảnh captcha ra output stream
		OutputStream osImage = response.getOutputStream();
		ImageIO.write(biImage, "jpeg", osImage);
		g2dImage.dispose(); // Giải phóng bộ nhớ

		// Lưu chuỗi captcha vào session để kiểm tra sau này
		HttpSession session = request.getSession();
		session.setAttribute("captchaSecurity", imageCode);
	}
}
