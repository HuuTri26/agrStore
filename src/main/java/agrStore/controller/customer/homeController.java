
package agrStore.controller.customer;

import java.util.Date;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import agrStore.utility.Ultility;
import agrStore.utility.UltilityImpl;

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
	
	@Autowired
	Ultility ultility;

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

		List<ProductEntity> products = productService.getProductsByProviderId(pId);

		List<ProductEntity> randProducts = productService.getRandomListProductByLimit(products, 12);

		model.addAttribute("randProducts", randProducts);
		model.addAttribute("selectedProviderId", pId);

		return "customer/index";
	}

	private void prepareFeedbackModel(ModelMap model, Integer productId, AccountEntity loggedInUser) {
		// Khởi tạo các giá trị mặc định khi vào trang productDetail
		model.addAttribute("feedback", new FeedbackEntity());
		model.addAttribute("btnMode", "add");
		model.addAttribute("star", 0);

		// Nếu user chưa đăng nhập thì ko làm gì hết
		if (loggedInUser == null) {
			return;
		}

		// Thử tìm các orderBillDts của product này do user này tạo ra
		List<OrderBillDetailEntity> orderBillDts = orderBillDetailService
				.getOrderBillDetailByProductIdAndAccountId(productId, loggedInUser.getAccountId());

		// Nếu như ko có bất kỳ orderBillDts thì nào khớp ko làm hết 
		if (orderBillDts == null || orderBillDts.isEmpty()) {
			return;
		}

		// Nếu có tồm tại thì lấy cái orderBillDt mới nhất (orderTime)
		OrderBillDetailEntity newestOrderBillDt = orderBillDts.stream()
				.max(Comparator.comparing(oDt -> oDt.getOrderBill().getOrderTime())).orElse(null);

		// Nếu ko tồn tại cái nào mới nhất thì ko làm gì hết
		if (newestOrderBillDt == null) {
			return;
		}

		// Cố tìm feedback ứng với orderBillDetail mới nhất vừa tìm đc
		FeedbackEntity existingFeedback = feedbackService
				.getFeedbackByOrderBillDetailId(newestOrderBillDt.getOrderBillDetailId());

		// Nếu tìm thấy, thì tiến hành đổ feedback này ra ngoài view và chuyển sang mode 'update'
		if (existingFeedback != null) {
			//Escape comment để chống XSS
			existingFeedback.setComment(UltilityImpl.XSSEscape4HTML(existingFeedback.getComment()));
			
			model.addAttribute("feedback", existingFeedback);
			model.addAttribute("orderBillDtId", existingFeedback.getOrderBillDetail().getOrderBillDetailId());
			model.addAttribute("btnMode", "update");
			model.addAttribute("star", existingFeedback.getStar());
		} else {
			// Nếu không tìm thấy thì tiến hành tạo 1 feedback mới cho orderBillDt trên, giữ
			// nguyên chế độ khởi tạo mặc định là 'add'
			model.addAttribute("orderBillDtId", newestOrderBillDt.getOrderBillDetailId());
		}
	}

	@RequestMapping(value = "productDetail", method = RequestMethod.GET)
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
				Integer percent_i = (int) ((star_i * 100.0) / feedbackCount); //progress bar ứng với từng sao
				totalStar += (int) (star_i * i);
				model.addAttribute("star_" + i, star_i);
				model.addAttribute("percent_" + i, percent_i);
			}

			Double avgStar = (totalStar * 1.0) / feedbackCount;
			model.addAttribute("avgStar", avgStar);
			model.addAttribute("avgPercent", (int) ((avgStar / 5.0) * 100)); //progress bar của số sao trung bình
			
		} else {
			model.addAttribute("avgStar", 0.0);
			model.addAttribute("avgPercent", 0);
			for (int i = 1; i <= 5; i++) {
				model.addAttribute("star_" + i, 0L);
				model.addAttribute("percent_" + i, 0);
			}
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
