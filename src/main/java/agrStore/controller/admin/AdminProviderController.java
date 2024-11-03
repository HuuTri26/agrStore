package agrStore.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import agrStore.entity.ProviderEntity;
import agrStore.service.ProviderService;
@Controller
public class AdminProviderController {
	
		@Autowired
		ProviderService providerService;
		
		
		@RequestMapping("/providerManagement")
		public String providerManagement(HttpServletRequest request, HttpSession session,ModelMap model,
				@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
			  model.addAttribute("currentPage", "Provider");
			  model.addAttribute("Providers", providerService.getListProviders());
			return "admin/provider/providerManagement";
		}
		
		
		@RequestMapping(value = "/providerManagement/provider", method = RequestMethod.GET)
		public String handleProvider(@RequestParam(value = "action", required = false) String action,
				@RequestParam(value = "id", required = false) Integer id, Model model) {

			if (action != null) {
				switch (action) {
				case "add":
					model.addAttribute("mode", "ADD");
					// model.addAttribute("category", new Category());
					break;

				case "view":
					if (id != null) {
						// Category category = categoryService.getCategoryById(id);
						model.addAttribute("mode", "VIEW");
						// model.addAttribute("category", category);
					}
					break;

				case "edit":
					if (id != null) {
						model.addAttribute("mode", "EDIT");
						ProviderEntity provider = providerService.getInforById(id); // Lấy dữ liệu provider theo id
	                    model.addAttribute("provider", provider);
					}
					break;
					
				case "delete":
					if (id != null) {
						// Category category = categoryService.getCategoryById(id);
						model.addAttribute("mode", "DELETE");
						// model.addAttribute("category", category);
					}
					break;	
				}
			}

			return "admin/provider/providerForm"; // Trả về cùng một trang JSP
		}

		@RequestMapping(value = "/providerManagement/provider", method = RequestMethod.POST)
		public String processCategory(@ModelAttribute("provider") ProviderEntity provider,  @RequestParam("mode") String mode) {

			if ("ADD".equals(mode)) {
				provider.setStatus(true);
				providerService.addProvider(provider);
			} else if ("EDIT".equals(mode)) {
				System.out.println(provider.getId());
		        ProviderEntity existingProvider = providerService.getInforById(provider.getId());
		        
		        if (existingProvider != null) {
		            // Cập nhật các thuộc tính của existingProvider
		            existingProvider.setProviderName(provider.getProviderName());
		            existingProvider.setPhoneNumber(provider.getPhoneNumber());
		            providerService.updateProvider(existingProvider);
		        }
		    } else if ("DELETE".equals(mode)) {
//				providerService.deleteProvider(provider);
		    	ProviderEntity existingProvider = providerService.getInforById(provider.getId());
		        
		        if (existingProvider != null) {
		            providerService.disableProvider(existingProvider);
		        }
			}

			return "redirect:/providerManagement.htm"; // Redirect sau khi xử lý

		}
}
