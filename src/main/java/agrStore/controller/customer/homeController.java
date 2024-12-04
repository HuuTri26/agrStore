
package agrStore.controller.customer;

import java.util.Date;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import agrStore.entity.AccountEntity;
import agrStore.entity.CategoryEntity;
import agrStore.entity.FeedbackEntity;
import agrStore.entity.OrderBillDetailEntity;
import agrStore.entity.ProductEntity;
import agrStore.entity.ProviderEntity;
import agrStore.service.CategoryService;
import agrStore.service.FeedbackService;
import agrStore.service.OrderBillDetailService;
import agrStore.service.ProductService;
import agrStore.service.ProviderService;

@Controller
public class homeController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProviderService providerService;

	@Autowired
	FeedbackService feedbackService;

	@Autowired
	OrderBillDetailService orderBillDetailService;

	@ModelAttribute("categories")
	public List<CategoryEntity> loadListCategory() {
		return categoryService.getListCategory();
	}

	@ModelAttribute("providers")
	public List<ProviderEntity> loadListProvider() {
		return providerService.getListProvider();
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexShow(HttpServletRequest request, Model model) {

		// Load dữ liệu mặc định với id = 1
		List<ProductEntity> products = productService.getListProductByCategoryId(1);
		List<ProductEntity> randProducts = productService.getRandomListProductByLimit(products, 12);
		model.addAttribute("randProducts", randProducts);

		// Thêm categoryId vào model để đánh dấu category đang được chọn
		model.addAttribute("selectedCategoryId", 1);

		return "customer/index";
	}

	@RequestMapping(value = "/showProductsBycId", method = RequestMethod.GET)
	public String showProductByCategoryId(Model model, @RequestParam(value = "cId") Integer cId) {

		List<ProductEntity> products = productService.getListProductByCategoryId(cId);
		List<ProductEntity> randProducts = productService.getRandomListProductByLimit(products, 12);

		model.addAttribute("randProducts", randProducts);
		model.addAttribute("selectedCategoryId", cId);
		return "customer/index";
	}

	@RequestMapping(value = "/showProductsBypId", method = RequestMethod.GET)
	public String showProductByProviderId(Model model, @RequestParam(value = "pId") Integer pId) {

		List<ProductEntity> products = productService.getListProductByProviderId(pId);
		List<ProductEntity> randProducts = productService.getRandomListProductByLimit(products, 12);

		model.addAttribute("randProducts", randProducts);
		model.addAttribute("selectedProviderId", pId);

		return "customer/index";
	}

	private void prepareFeedbackModel(ModelMap model, Integer productId, AccountEntity loggedInUser) {
		// Default mode set
		model.addAttribute("feedback", new FeedbackEntity());
		model.addAttribute("btnMode", "add");
		model.addAttribute("star", 0);

		// If user doesn't exist, return early
		if (loggedInUser == null) {
			return;
		}

		// Try to find orderBillDetails by product and account
		List<OrderBillDetailEntity> orderBillDts = orderBillDetailService
				.getOrderBillDetailByProductIdAndAccountId(productId, loggedInUser.getAccountId());

		// If no orderBillDetails found, return
		if (orderBillDts == null || orderBillDts.isEmpty()) {
			return;
		}

		// Get the most recent OrderBillDetailEntity
		OrderBillDetailEntity newestOrderBillDt = orderBillDts.stream()
				.max(Comparator.comparing(oDt -> oDt.getOrderBill().getOrderTime())).orElse(null);

		// If no newest order bill detail found, return
		if (newestOrderBillDt == null) {
			return;
		}

		// Try to find feedback by orderBillDetail
		FeedbackEntity existingFeedback = feedbackService
				.getFeedbackByOrderBillDetailId(newestOrderBillDt.getOrderBillDetailId());

		// If existing feedback found, update model for update mode
		if (existingFeedback != null) {
			model.addAttribute("feedback", existingFeedback);
			model.addAttribute("orderBillDtId", existingFeedback.getOrderBillDetail().getOrderBillDetailId());
			model.addAttribute("btnMode", "update");
			model.addAttribute("star", existingFeedback.getStar());
		} else {
			// If no existing feedback, add orderBillDetail ID for new feedback
			model.addAttribute("orderBillDtId", newestOrderBillDt.getOrderBillDetailId());
		}
	}

	@RequestMapping(value = "/productDetail", method = RequestMethod.GET)
	public String showProductDetail(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "productId") Integer productId) {
		ProductEntity product = productService.getProductById(productId);
		model.addAttribute("product", product);

		List<FeedbackEntity> feedbacks = feedbackService.getListFeedBackByProductId(productId);
		model.addAttribute("feedbacks", feedbacks);

		AccountEntity loggedInUser = (AccountEntity) request.getSession().getAttribute("loggedInUser");

		prepareFeedbackModel(model, productId, loggedInUser);

		Integer feedbackCount = feedbacks.size();
		if (feedbackCount != 0) {
			model.addAttribute("feedbackCount", feedbackCount);
			Integer totalStar = 0;
			for (int i = 1; i <= 5; i++) {
				Long star_i = feedbackService.countFeedbackByStar(feedbacks, i);
				Integer percent_i = (int) ((star_i / feedbackCount) * 100);
				totalStar += (int) (star_i * i);
				model.addAttribute("star_" + i, star_i);
				model.addAttribute("percent_" + i, percent_i);
			}

			Double avgStar = (totalStar * 1.0) / feedbackCount;
			model.addAttribute("avgStar", avgStar);
			model.addAttribute("avgPercent", (int) ((avgStar / 5.0) * 100));
		}

		List<ProductEntity> relatedProucts = productService
				.getListProductByCategoryId(product.getCategory().getCategoryId());
		relatedProucts.remove(product);
		model.addAttribute("relatedProucts", productService.getRandomListProductByLimit(relatedProucts, 10));

		return "customer/product/productDetail";
	}

	@RequestMapping(value = "customer/feedbackForm", params = "add", method = RequestMethod.POST)
	public String addFeedback(HttpServletRequest request, @ModelAttribute("feedback") FeedbackEntity feedback,
			@RequestParam("orderBillDtId") Integer orderBillDtId, @RequestParam("star") Integer star) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");

		System.out.println("==> Add new feedback for this product!");
		try {
			OrderBillDetailEntity orderBillDt = orderBillDetailService.getOrderBillDetailById(orderBillDtId);
			feedback.setOrderBillDetail(orderBillDt);
			feedback.setAccount(loggedInUser);
			feedback.setCreateAt(new Date());
			feedback.setStar(star);

			feedbackService.addFeedBack(feedback);
			System.out.println("==> Feedback created successfully!");

		} catch (Exception e) {
			System.out.println("Error: Feedback created failed!");
			e.printStackTrace();
		}

		return "redirect:/productDetail.htm?productId=" + feedback.getOrderBillDetail().getProduct().getProductId();
	}

	@RequestMapping(value = "customer/feedbackForm", params = "update", method = RequestMethod.POST)
	public String updateFeedback(HttpServletRequest request, @ModelAttribute("feedback") FeedbackEntity feedback,
			@RequestParam("star") Integer star, @RequestParam("orderBillDtId") Integer orderBillDtId) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		System.out.println(orderBillDtId);
		System.out.println("==> Update feedback for this product!");
		try {
			OrderBillDetailEntity orderBillDt = orderBillDetailService.getOrderBillDetailById(orderBillDtId);
			feedback.setOrderBillDetail(orderBillDt);
			feedback.setAccount(loggedInUser);
			feedback.setOrderBillDetail(orderBillDt);
			feedback.setCreateAt(feedback.getCreateAt());
			feedback.setStar(star);

			feedbackService.updateFeedBack(feedback);
			System.out.println("==> Feedback updated successfully!");

		} catch (Exception e) {
			System.out.println("Error: Feedback updated failed!");
			e.printStackTrace();
		}

		return "redirect:/productDetail.htm?productId=" + feedback.getOrderBillDetail().getProduct().getProductId();
	}
}
