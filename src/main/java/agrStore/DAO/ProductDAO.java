package agrStore.DAO;

import java.util.List;

import agrStore.entity.ProductEntity;

public interface ProductDAO {
	public void addProduct(ProductEntity product);
	public void updateProduct(ProductEntity product);
	public List<ProductEntity> getListProduct();
	public ProductEntity getProductById(Integer id);
	public List<ProductEntity> getListProductByCategotyId(Integer cId);
}
