package agrStore.controller.admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import agrStore.DAO.DistrictDAO;
import agrStore.DAO.ProvinceDAO;
import agrStore.DAO.WardDAO;
import agrStore.bean.UploadFile;
import agrStore.entity.AccountEntity;
import agrStore.entity.AddressEntity;
import agrStore.entity.CategoryEntity;
import agrStore.entity.DistrictEntity;
import agrStore.entity.ProductEntity;
import agrStore.entity.ProviderEntity;
import agrStore.entity.ProvinceEntity;
import agrStore.entity.RoleEntity;
import agrStore.entity.WardEntity;
import agrStore.service.AccountService;
import agrStore.service.AddressService;
import agrStore.service.DistrictService;
import agrStore.service.ProvinceService;
import agrStore.service.RoleService;
import agrStore.service.WardService;
import agrStore.utility.Ultility;

@Controller
public class AdminStaffController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private WardDAO wardDAO;
	@Autowired
	private DistrictDAO districtDAO;
	@Autowired
	private ProvinceDAO provinceDAO;
	@Autowired 
	private ProvinceService provinceService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private WardService wardService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AddressService addressService;
	@Autowired
	Ultility accountUltility;
	
	@Autowired
	@Qualifier("user")
	UploadFile baseUploadFile;
	@ModelAttribute("sysDate")
	public Date getSystemDate() {
		return new Date();
	}
	

	
	Ultility ultility;
	
	@RequestMapping("/staffManagement")
	public String staffManagement(HttpServletRequest request, HttpSession session,ModelMap model,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code
		  model.addAttribute("currentPage", "staff");
		  List<AccountEntity> stafflist=this.accountService.getAllStaff();
		  model.addAttribute("staffs", stafflist);
		return "admin/staff/staffManagement";
	}

	@RequestMapping(value = "/staffManagement/staff", method = RequestMethod.GET)
	public String handleStaff(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "id", required = false) Integer id, Model model) {

		if (action != null) {
			switch (action) {
			
			case "view":
				if (id != null) {
					System.out.println("==> View staff mode");
					AccountEntity staff = accountService.getAccountById(id);
					model.addAttribute("mode", "VIEW");
					model.addAttribute("staff", staff);
					model.addAttribute("pdImg", staff.getAvatar());
				}
				break;
			

			case "edit":
				if (id != null) {
					System.out.println("==> Edit product mode");
					AccountEntity staff=accountService.getAccountById(id);
					model.addAttribute("mode", "EDIT");
					model.addAttribute("staff",staff);
				}
				break;
			}
		}

		return "admin/staff/staffForm"; 
	}

	@RequestMapping(value = "/staffManagement/staff", method = RequestMethod.POST)
	public String processStaff(
			// @ModelAttribute("staff") StaffEntity category,
			@RequestParam("mode") String mode) {

		if ("ADD".equals(mode)) {
			// categoryService.addCategory(category);
		} else if ("EDIT".equals(mode)) {
			// categoryService.updateCategory(category);
		}

		return "redirect:/admin/staff/staffManagement.htm"; // Redirect sau khi xử lý
	}
	
	@RequestMapping ("/staffManagement/staffAdd")
	public String loadArressSelection(@RequestParam(value = "provinceId", required = false) Integer provinceId,
			@RequestParam(value = "districtId", required = false) Integer districtId,
			@RequestParam(value = "wardId", required = false) Integer wardId, Model model, HttpServletRequest request) {
		

		HttpSession session = request.getSession();

		// Lấy ds các tỉnh
		List<ProvinceEntity> provinces = provinceService.getListProvinces();
		model.addAttribute("provinces", provinces);

		// Nếu người dùng đã chọn tỉnh, lấy danh sách quận load ra view
		if (provinceId != null) {
			System.out.println("==> ProvinceId: " + provinceId);
			List<DistrictEntity> districts = districtService.getListDistrictsByProvinceId(provinceId);
			model.addAttribute("districts", districts);
			model.addAttribute("selectedProvinceId", provinceId);

			// Cập nhật tên tỉnh đã chọn
			ProvinceEntity selectedProvince = provinceService.getProvinceById(provinceId);

			// Lưu tỉnh vừa chọn vào session
			session.setAttribute("selectedProvince", selectedProvince);
		}

		// Nếu ng dùng đã chọn quận, lấy danh sách phường load ra view
		if (districtId != null) {
			System.out.println("==> DistrictId: " + districtId);
			List<WardEntity> wards = wardService.getListWardByDistrictId(districtId);
			model.addAttribute("wards", wards);
			model.addAttribute("selectedDistrictId", districtId);

			// Cập nhật tên quận đã chọn
			DistrictEntity selectedDistrict = districtService.getDistrictById(districtId);

			// Lưu quận vừa chọn vào session
			session.setAttribute("selectedDistrict", selectedDistrict);
		}

		// Nếu người dùng đã chọn phường, cập nhật tên phường
		if (wardId != null) {
			System.out.println("==> WardId: " + wardId);
			WardEntity selectedWard = wardService.getWardById(wardId);

			// Lưu phường vừa chọn vào session
			session.setAttribute("selectedWard", selectedWard);
		}

		return "admin/staff/staffAdd";
	}
	@RequestMapping(value = "/staffManagement/staffAdd", params = "add", method = RequestMethod.POST)
	public String Staffadd(HttpServletRequest request,@ModelAttribute("account") AccountEntity account, Model model, SessionStatus sessionStatus) {
	    HttpSession session = request.getSession();

	    // Lấy các dữ liệu trong session
	  
	    WardEntity selectedWard = (WardEntity) session.getAttribute("selectedWard");

	    // Lấy các thông tin đăng ký từ form
	    String gmail = request.getParameter("fid-gmail");
	    String password = request.getParameter("password");
	    String reEnterPassword = request.getParameter("re-enter-password");
	    String fullName = request.getParameter("full-name");
	    String phoneNumber = request.getParameter("phone-number");
	    String streetName = request.getParameter("streetName");

	    Boolean isValid = Boolean.TRUE;

	    // Kiểm tra các trường thông tin
	    if (gmail.isEmpty()) {
	        model.addAttribute("gmErr", "Vui lòng nhập Gmail!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Gmail field empty!");
	    } else if (password.isEmpty()) {
	        model.addAttribute("passErr", "Vui lòng nhập mật khẩu!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Password field empty!");
	    } else if (reEnterPassword.isEmpty()) {
	        model.addAttribute("rePassErr", "Vui lòng nhập lại mật khẩu!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Re-enter password field empty!");
	    } else if (!password.equals(reEnterPassword)) {
	        model.addAttribute("rePassErr", "Mật khẩu không khớp, vui lòng nhập lại!");
	        isValid = Boolean.FALSE;
	    } else if (fullName.isEmpty()) {
	        model.addAttribute("nameErr", "Vui lòng nhập họ và tên!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Full name field empty!");
	    } else if (phoneNumber.isEmpty()) {
	        model.addAttribute("phoneErr", "Vui lòng nhập số điện thoại!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Phone number field empty!");
	    } else if (!accountUltility.isValidPhoneNumber(phoneNumber)) {
	        model.addAttribute("phoneErr", "Số điện thoại không hợp lệ!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Invalid phone number!");
	    } else if (streetName.isEmpty()) {
	        model.addAttribute("streetErr", "Tên đường không được phép để trống!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Street name field empty!");
	    } else if (!accountUltility.isValidStreetName(streetName)) {
	        model.addAttribute("streetErr", "Tên đường không hợp lệ!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Invalid street name!");
	    }

	    // Tiến hành xử lý nếu thông tin hợp lệ
	    if (isValid) {
	        // Kiểm tra và chuẩn bị địa chỉ
	        AddressEntity address = addressService.getAddressByStreetAndWard(accountUltility.standardizeStreetName(streetName), selectedWard.getId());

	        // Kiểm tra xem địa chỉ đã tồn tại chưa
	        if (address != null) {
	            account.setAddress(address); // Gán địa chỉ cho tài khoản
	            System.out.println("==> Address exists, assign to account!");
	        } else {
	            try {
	                AddressEntity newAddress = new AddressEntity();
	                newAddress.setWard(selectedWard);
	                newAddress.setStreetName(accountUltility.standardizeStreetName(streetName));

	                addressService.addAddress(newAddress);
	                account.setAddress(newAddress); // Gán địa chỉ mới cho tài khoản
	                System.out.println("==> New address created successfully!");
	            } catch (Exception e) {
	                System.out.println("Error: Failed to create new address!");
	            }
	        }

	        // Tạo tài khoản với vai trò customer
	        System.out.println("==> Create new user account");
	        RoleEntity staffRole = roleService.getRoleById(2); // Lấy role là staff
	        if (staffRole != null) {
	            try {
	            	
	            	account.setGmail(gmail);
	                account.setPassword(accountUltility.getHashPassword(reEnterPassword));
	                account.setFullName(accountUltility.standardizeName(fullName));
	                account.setPhoneNumber(phoneNumber);
	                account.setStatus(Boolean.TRUE);
	                account.setCreateAt(new Date());
	                account.setUpdateAt(new Date());
	                account.setRole(staffRole);

	                accountService.addAccount(account);
	                System.out.println("==> User account created successfully!");

	                // Redirect sau khi tạo tài khoản thành công
	                return "redirect:/";
	            } catch (Exception e) {
	                System.out.println("Error: User account creation failed!");
	            } finally {
	                // Giải phóng dữ liệu trong session
	                request.getSession().invalidate();
	                System.out.println("==> Session invalidated");

	                // Giải phóng model attributes
	                sessionStatus.setComplete();
	                System.out.println("==> Model attributes cleared");
	            }
	        } else {
	            System.out.println("Error: Role 2 (Staff) not found!");
	        }
	    }

	    return "redirect:/staffManagement.htm";
	}

	@RequestMapping(value = "/staffManagement/staff", params = "EDIT", method = RequestMethod.POST)
	public String editStaff(Model model, @ModelAttribute("staff") AccountEntity staff, BindingResult errors) {
	    Boolean isValid = Boolean.TRUE;

	    // Kiểm tra đầu vào
	    if (staff.getFullName() == null || staff.getFullName().trim().isEmpty()) {
	        errors.rejectValue("fullName", "staff", "Tên sản phẩm không được để trống!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Product Name field empty!");
	    }
	    if (staff.getPhoneNumber() == null) {
	        errors.rejectValue("phoneNumber", "staff", "Số điện thoại không được để trống!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Phone Number field empty!");
	    }
	    if (staff.getGmail() == null) {
	        errors.rejectValue("gmail", "staff", "Email không được để trống!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Email field empty!");
	    }

	    if (!isValid) {
	        System.out.println("Error: Product update failed!");
	        model.addAttribute("mode", "EDIT");
	        return "admin/staff/staffForm";
	    } else {
	        try {
	            // Tải đối tượng hiện tại từ cơ sở dữ liệu
	            AccountEntity existingStaff = accountService.getAccountById(staff.getAccountId());
	            if (existingStaff == null) {
	                throw new IllegalArgumentException("Staff not found for ID: " + staff.getAccountId());
	            }

	            // Chỉ cập nhật các thuộc tính được thay đổi
	            existingStaff.setFullName(staff.getFullName());
	            existingStaff.setPhoneNumber(staff.getPhoneNumber());
	            existingStaff.setGmail(staff.getGmail());
	            existingStaff.setUpdateAt(new Date());

	            // Gọi service để lưu đối tượng
	            accountService.updateAccount(existingStaff);

	            System.out.println("==> Staff updated successfully!");
	            return "redirect:/staffManagement.htm";
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Error: Product update failed!");
	            model.addAttribute("mode", "EDIT");
	            return "admin/staff/staffForm";
	        }
	    }
	}

	@RequestMapping(value = "/staffManagement/deleteStaff", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Integer id) {
		
		
		AccountEntity staff=accountService.getAccountById(id);
			try {
				staff.setStatus(Boolean.FALSE);
				accountService.updateAccount(staff);
				System.out.println("==> Set product status to 'False' successfully!");
			}catch (Exception e) {
				System.out.println("Error: Set product status to 'False' failed!");
				e.printStackTrace();
			}
		
			return "redirect:/staffManagement.htm";
	}
	@RequestMapping(value = "/staff/uploadImg", method = RequestMethod.POST)
	public String uploadProductImage(HttpServletRequest request, Model model,
			@RequestParam("image") MultipartFile image) {
		if (image.isEmpty()) {
			model.addAttribute("imgError", "Vui lòng chọn file để upload!");
			System.out.println("Error: Image file not found!");
		} else {
			HttpSession session = request.getSession();
			ProductEntity product = (ProductEntity) session.getAttribute("product");
			if(product.getProductId() != null) {
				model.addAttribute("mode", "EDIT");
			}
			
			try {
				
				String timestamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
				String fileName = image.getOriginalFilename();
				String newFileName = timestamp + '-' + fileName;
				String photoPath = baseUploadFile.getBasePath() + File.separator + newFileName;
				image.transferTo(new File(photoPath));
				System.out.println("==> Upload file successfully!");

				Thread.sleep(2500);
				product.setImage(newFileName);
				model.addAttribute("imgError", "Upload file thành công!");
				return "admin/staff/staffForm";
			} catch (Exception e) {
				e.printStackTrace();

				model.addAttribute("imgError", "Upload file không thành công!");
				System.out.println("Error: Upload file failed!");
			}
		}
		
		return "admin/staff/staffForm";
	}

	
}
