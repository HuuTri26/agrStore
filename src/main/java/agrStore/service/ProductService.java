package agrStore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.CartItemEntity;
import agrStore.entity.ProductEntity;

@Transactional
@Service
public interface ProductService {
	public void addProduct(ProductEntity product);
	public void updateProduct(ProductEntity product);
	public List<ProductEntity> getListProduct();
	public ProductEntity getProductById(Integer id);
	public List<ProductEntity> getListProductByCategoryId(Integer cId);
	public List<ProductEntity> getRandomListProductByLimit(List<ProductEntity> list, Integer limit);
	public List<ProductEntity> getProductsByProviderId(Integer providerId);
	public void updateProductQuantityAfterPayment(List<CartItemEntity> selectedItems);
	
	// hàm cập nhật số lượng sản phẩm khi nhập hàng
	public void updateQuantityProduct(Integer productId, int quantity);
	public List<Object[]> getTheMostPurchasedProduct();
	public List<ProductEntity> searchProductByName(String productName);
}
