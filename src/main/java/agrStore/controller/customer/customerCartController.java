package agrStore.controller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import agrStore.entity.AccountEntity;
import agrStore.entity.CartItemEntity;
import agrStore.entity.ProductEntity;
import agrStore.service.CartItemService;
import agrStore.service.ProductService;

@Controller
@RequestMapping("/customer")
public class customerCartController {

	@Autowired
	CartItemService cartItemService;

	@Autowired
	ProductService productService;

	@RequestMapping("/customerCart")
	public String showCustomerCartItems(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "selectedItemIds", required = false) List<Integer> selectedItemIds) {

		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		System.out.println("==> Show list cart item!");
		List<CartItemEntity> cartItems = cartItemService.getListCartItemByCartId(loggedInUser.getCart().getCartId());

		if (selectedItemIds != null) {
			for (Integer selectedItemId : selectedItemIds) {
				CartItemEntity cartItem = cartItemService.getCartItemById(selectedItemId);
				cartItem.setIsSelected(!cartItem.getIsSelected());
				int index = cartItems.indexOf(cartItem);
				if (index != -1) {
					cartItems.set(index, cartItem);
				}
			}
		}
		session.setAttribute("cartItems", cartItems);
		return "customer/customerCart";
	}

	@RequestMapping(value = "/addItemIntoCart", method = RequestMethod.GET)
	public String addItemToCart(HttpServletRequest request, @RequestParam("pId") Integer pId) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");

		try { // Kiểm tra sản phẩm thêm vào có tồn tại trong giỏ hàng hay chưa?
			CartItemEntity item_t = cartItemService.getCartItemByProductIdAndCartId(pId,
					loggedInUser.getCart().getCartId());
			if (item_t != null) { // Nếu tồn tại, tiếm hành tăng số lượng sản phẩm lên 1
				item_t.setQuantity(item_t.getQuantity() + 1);
				cartItemService.updateCartItem(item_t);

			} else { // Nếu không tồn tại thì tạo mới
				ProductEntity product = productService.getProductById(pId);
				CartItemEntity item = new CartItemEntity();
				item.setCart(loggedInUser.getCart());
				item.setProduct(product);
				item.setQuantity(1);

				cartItemService.addCartItem(item);
			}

			System.out.println("==> Add selected product to cart successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: Add selected product to cart failed!");
		}

		return "redirect:/index.htm";
	}

	@RequestMapping(value = "/removeItemFromCart", method = RequestMethod.GET)
	public String removeItemFromCart(HttpServletRequest request, @RequestParam("id") Integer id) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");

		try {
			CartItemEntity cart = cartItemService.getCartItemById(id);
			cartItemService.deleteCartItem(cart);

			System.out.println("==> Delete cart item successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: Delete cart item ");
		}

		return "redirect:/customerCart.htm";
	}

	@RequestMapping(value = "customerCart", params = "delete", method = RequestMethod.GET)
	public String removeSelectedItemFromCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<CartItemEntity> cartItems = (List<CartItemEntity>) session.getAttribute("cartItems");
		try {
			cartItemService.deleteSelectedCartItem(cartItems);

			System.out.println("==> Delete all cart item successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: Delete all cart item failed!");
		}

		return "redirect:/customerCart.htm";
	}

	@RequestMapping(value = "customerCart", params = "update", method = RequestMethod.GET)
	public String updateItemCartQuanity(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<CartItemEntity> cartItems = (List<CartItemEntity>) session.getAttribute("cartItems");

		for (CartItemEntity cartItem : cartItems) {
			String quantityParam = request.getParameter("quantity_" + cartItem.getCartItemId());
			if (quantityParam != null) {
				try {
					int quantity = Integer.parseInt(quantityParam);
					cartItem.setQuantity(quantity);
					cartItemService.updateCartItem(cartItem);
				} catch (NumberFormatException e) {
					System.out.println("Error: Invalid quantity for cartItemId: " + cartItem.getCartItemId());
				}
			}
		}

		session.setAttribute("cartItems", cartItems);

		return "redirect:/customerCart.htm";
	}

	@RequestMapping(value = "customerCart", params = "selectedAll", method = RequestMethod.GET)
	public String toggleSelectAllItems(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<CartItemEntity> cartItems = (List<CartItemEntity>) session.getAttribute("cartItems");

		// Kiểm tra xem có bất kỳ item nào chưa được chọn không
		boolean hasUnselected = cartItems.stream().anyMatch(item -> !item.getIsSelected());
		System.out.println("Has unselected items: " + hasUnselected);

		// Nếu có item chưa được chọn, thì chọn tất cả; nếu không, bỏ chọn tất cả
		for (CartItemEntity item : cartItems) {
			item.setIsSelected(hasUnselected); // Đảo trạng thái của tất cả các item
		}

		session.setAttribute("cartItems", cartItems);

		return "customer/customerCart";
	}

}
