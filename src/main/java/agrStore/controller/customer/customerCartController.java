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
import agrStore.utility.ServerLogger;

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

		List<CartItemEntity> selectedCartItems = cartItemService.getSelectedCartItems(cartItems);

		Double totalPrice = cartItemService.getTotalPriceofCartItems(selectedCartItems);
		Integer selectedItemCount = selectedCartItems.size();

		session.setAttribute("cartItems", cartItems);
		session.setAttribute("totalPrice", totalPrice);
		session.setAttribute("selectedCartItems", selectedCartItems);
		model.addAttribute("selectedItemCount", selectedItemCount);
		return "customer/customerCart";
	}

	@RequestMapping(value = "/addItemIntoCart", method = RequestMethod.GET)
	public String addItemToCart(HttpServletRequest request, @RequestParam("pId") Integer pId,
			@RequestParam(value = "quantity", required = false) Integer quantity) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");

		try {
			// Mặc định set bằng 1 nếu ko có tham số truyền vào
			int itemQuantity = (quantity != null && quantity > 0) ? quantity : 1;

			ProductEntity product = productService.getProductById(pId);
			if (product == null) {
				System.out.println("Error: Product not found!");
				return "redirect:/index.htm";
			}

			if (itemQuantity > product.getQuantity()) {
//	        	session.setAttribute("errorMessage", "Insufficient product quantity in stock.");
				System.out.println("Error: Insufficient product quantity in stock!");
				return "redirect:/index.htm";
			}

			CartItemEntity existingCartItem = cartItemService.getCartItemByProductIdAndCartId(pId,
					loggedInUser.getCart().getCartId());

			if (existingCartItem != null) {
				int newQuantity = existingCartItem.getQuantity() + itemQuantity;

				if (newQuantity > product.getQuantity()) {
//					session.setAttribute("errorMessage", "Cannot add more items than available in stock.");
					System.out.println("Error: Cannot add more items than available in stock!");
					return "redirect:/index.htm";
				}

				existingCartItem.setQuantity(newQuantity);
				cartItemService.updateCartItem(existingCartItem);
			} else {
				CartItemEntity newCartItem = new CartItemEntity();
				newCartItem.setCart(loggedInUser.getCart());
				newCartItem.setProduct(product);
				newCartItem.setQuantity(itemQuantity);

				cartItemService.addCartItem(newCartItem);
			}

			System.out.println("==> Product added to cart successfully!");
			ServerLogger.writeActionLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "ADD", existingCartItem);
			return "redirect:/index.htm";

		} catch (Exception e) {
			ServerLogger.writeErrorLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "ADD", e);
			System.out.println("Error: product add to cart failed!");

			return "redirect:/index.htm";
		}

	}

	@RequestMapping(value = "/customerCart", params = "delete", method = RequestMethod.GET)
	public String removeSelectedItemFromCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		List<CartItemEntity> selectedCartItems = (List<CartItemEntity>) session.getAttribute("selectedCartItems");
		try {
			cartItemService.deleteCartItem(selectedCartItems);

			System.out.println("==> Delete all cart item successfully!");
			ServerLogger.writeActionLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "DELETE", selectedCartItems);
		} catch (Exception e) {
			ServerLogger.writeErrorLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "DELETE", e);
			e.printStackTrace();
			System.out.println("Error: Delete all cart item failed!");
		}

		return "redirect:/customer/customerCart.htm";
	}

	@RequestMapping(value = "/customerCart", params = "update", method = RequestMethod.GET)
	public String updateItemCartQuanity(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		List<CartItemEntity> cartItems = (List<CartItemEntity>) session.getAttribute("cartItems");
		
		
		for (CartItemEntity cartItem : cartItems) {
			String quantityParam = request.getParameter("quantity_" + cartItem.getCartItemId());
			if (quantityParam != null) {
				try {
					int quantity = Integer.parseInt(quantityParam);
					ProductEntity product = productService.getProductById(cartItem.getProduct().getProductId());
					cartItem.setQuantity(quantity);
					
					if(quantity > 0 && quantity <= product.getQuantity()) {
						cartItemService.updateCartItem(cartItem);
						ServerLogger.writeActionLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "UPDATE", cartItems);
					}else {
						System.out.println("Error: Insufficient product quantity in stock!");
					}
					
				} catch (NumberFormatException e) {
					ServerLogger.writeErrorLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "UPDATE", e);
					System.out.println("Error: Invalid quantity for cartItemId: " + cartItem.getCartItemId());
				}
			}
		}

		session.setAttribute("cartItems", cartItems);

		return "redirect:/customer/customerCart.htm";
	}

	@RequestMapping(value = "/customerCart", params = "selectedAll", method = RequestMethod.GET)
	public String toggleSelectAllItems(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<CartItemEntity> cartItems = (List<CartItemEntity>) session.getAttribute("cartItems");

		// Kiểm tra xem có bất kỳ item nào chưa được chọn không
		boolean hasUnselected = cartItems.stream().anyMatch(item -> !item.getIsSelected());
		System.out.println("Has unselected items: " + hasUnselected);

		// Nếu có item chưa được chọn, thì chọn tất cả; nếu không, bỏ chọn tất cả
		for (CartItemEntity item : cartItems) {
			item.setIsSelected(hasUnselected); // Đảo trạng thái của tất cả các item
		}
		Double totalPrice = Double.valueOf(0);
		if (hasUnselected) {
			totalPrice = cartItemService.getTotalPriceofCartItems(cartItems);
		}
		Integer selectedItemCount = cartItems.size();

		session.setAttribute("cartItems", cartItems);
		session.setAttribute("totalPrice", totalPrice);
		session.setAttribute("selectedCartItems", cartItems);
		model.addAttribute("selectedItemCount", selectedItemCount);
		return "customer/customerCart";
	}

}
