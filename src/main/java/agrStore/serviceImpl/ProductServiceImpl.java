package agrStore.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agrStore.DAO.ProductDAO;
import agrStore.entity.CartItemEntity;
import agrStore.entity.ProductEntity;
import agrStore.service.ProductService;
import agrStore.utility.Ultility;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDAO ProductDAO;
	
	@Autowired
	Ultility ultility;

	@Override
	public void addProduct(ProductEntity product) {
		product.setProductName(ultility.XSSSanitizeHTML(product.getProductName()));
		product.setDescript(ultility.XSSSanitizeHTML(product.getDescript()));
		
		ProductDAO.addProduct(product);
	}

	@Override
	public void updateProduct(ProductEntity product) {
		product.setProductName(ultility.XSSSanitizeHTML(product.getProductName()));
		product.setDescript(ultility.XSSSanitizeHTML(product.getDescript()));
		
		ProductDAO.updateProduct(product);
	}

	@Override
	public List<ProductEntity> getListProduct() {
		return ProductDAO.getListProduct();
	}

	@Override
	public ProductEntity getProductById(Integer id) {
		return ProductDAO.getProductById(id);
	}

	@Override
	public List<ProductEntity> getRandomListProductByLimit(List<ProductEntity> products, Integer limit) {
		//Trộn ngẫu nhiên các phần tử
		Collections.shuffle(products);
		//Lấy giới hạn danh sách các phần tử
		return products.stream().limit(limit).collect(Collectors.toList());
	}

	@Override
	public List<ProductEntity> getListProductByCategoryId(Integer cId) {
		return ProductDAO.getListProductByCategotyId(cId);
	}

	@Override

	public List<ProductEntity> getProductsByProviderId(Integer providerId) {
		// TODO Auto-generated method stub
		return this.ProductDAO.getProductsByProviderId(providerId);
	}

	@Override
	public void updateQuantityProduct(Integer productId, int quantity) {
		// TODO Auto-generated method stub
		this.ProductDAO.updateProductQuantity(productId, quantity);

	}


	/*
	 * @Override public List<ProductEntity> getListProductByProviderId(Integer pId)
	 * { // TODO Auto-generated method stub return
	 * ProductDAO.getListProductByCategotyId(pId); }
	 */

	@Override
	public List<Object[]> getTheMostPurchasedProduct() {
		// TODO Auto-generated method stub
		return this.ProductDAO.getTheMostPurchasedProduct();
	}
	/*
	 * @Override public List<ProductEntity> getListProductByProviderId(Integer pId)
	 * { return ProductDAO.getProductsByProviderId(pId); }
	 */

	@Override
	public void updateProductQuantityAfterPayment(List<CartItemEntity> selectedItems) {
		System.out.println("==> Update {0} products after complete payment!".formatted(selectedItems.size()));
		for (CartItemEntity item : selectedItems) {
			ProductEntity product = ProductDAO.getProductById(item.getProduct().getProductId());
			product.setQuantity(product.getQuantity() - item.getQuantity());
			
			ProductDAO.updateProduct(product);
			System.out.println("==> Product updated successfully!");
		}
		

	}

}
