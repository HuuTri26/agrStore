package agrStore.DAO;

import java.util.List;

import agrStore.entity.ProductEntity;

public interface ProductDAO {
	public void addProduct(ProductEntity product);
	public void updateProduct(ProductEntity product);
	public List<ProductEntity> getListProduct();
	public ProductEntity getProductById(Integer id);
	public List<ProductEntity> getListProductByCategotyId(Integer cId);
	public List<ProductEntity> getProductsByProviderId(Integer providerId);
	public void updateProductQuantity(Integer productId, int quantity);
	public List<Object[]> getTheMostPurchasedProduct();
	public List<ProductEntity> searchProductByName(String productName);

}
