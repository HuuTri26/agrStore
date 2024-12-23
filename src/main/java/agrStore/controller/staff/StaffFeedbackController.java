package agrStore.controller.staff;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import agrStore.service.FeedbackService;
import agrStore.utility.UltilityImpl;
import agrStore.entity.FeedbackEntity;
import agrStore.entity.ProductEntity;
import agrStore.entity.ProviderEntity;

@Controller
@RequestMapping("/staff")
public class StaffFeedbackController {
	@Autowired
	FeedbackService feedbackService;

	@ModelAttribute("feedbacks")
	public List<FeedbackEntity> loadListFeedback() {
		return feedbackService.getListFeedback();
	}

	@RequestMapping("/feedbackManagement")
	public String feedbackManagement(HttpServletRequest request, HttpSession session, ModelMap model,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code
		model.addAttribute("currentPage", "feedback");
		return "staff/feedback/feedbackManagement";
	}

	@RequestMapping(value = "/feedbackManagement/feedback", method = RequestMethod.GET)
	public String handleFeedback(@RequestParam(value = "action", required = false) String action,
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
					System.out.println("==> View feedback mode");
					FeedbackEntity feedback = feedbackService.getFeedbackById(id);
					model.addAttribute("mode", "VIEW");
					feedback.getAccount().setFullName(UltilityImpl.XSSEscape4HTML(feedback.getAccount().getFullName()));
					feedback.getAccount().setPhoneNumber(UltilityImpl.XSSEscape4HTML(feedback.getAccount().getPhoneNumber()));
					
					feedback.setComment(UltilityImpl.XSSEscape4HTML(feedback.getComment()));
					model.addAttribute("feedback", feedback);
					// model.addAttribute("category", category);
				}
				break;

			case "edit":
				if (id != null) {
					// Category category = categoryService.getCategoryById(id);
					model.addAttribute("mode", "EDIT");
					// model.addAttribute("category", category);
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

		return "staff/feedback/feedbackForm"; // Trả về cùng một trang JSP
	}

	@RequestMapping(value = "/feedbackManagement/deleteFeedback", method = RequestMethod.GET)
	public String deleteFeedback(@RequestParam("id") Integer id) {

		FeedbackEntity feedback = feedbackService.getFeedbackById(id);
		if (feedback != null) {
			try {
				feedbackService.deleteFeedback(feedback);
				System.out.println("==> Delete feedback successfully!");
			} catch (Exception e) {
				System.out.println("Error: Delete feedback failed!");
				e.printStackTrace();
			}
		}

		return "redirect:/staff/feedbackManagement.htm";
	}

	@RequestMapping(value = "/feedbackManagement/feedback", method = RequestMethod.POST)
	public String processCategory(@ModelAttribute("feedback") FeedbackEntity feedback,
			@RequestParam("mode") String mode) {

		if ("ADD".equals(mode)) {
			// categoryService.addCategory(category);
		} else if ("DELETE".equals(mode)) {
			// categoryService.updateCategory(category);
			feedbackService.deleteFeedback(feedback);
			FeedbackEntity existingFeedback = feedbackService.getFeedbackById(feedback.getFeedbackId());

			if (existingFeedback != null) {
				feedbackService.deleteFeedback(existingFeedback);
			}
		}

		return "redirect:/staff/feedback/feedbackManagement.htm"; // Redirect sau khi xử lý
	}
}
