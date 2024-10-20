package agrStore.DAO;

import java.util.List;

import agrStore.entity.ProductEntity;

public interface ProductDAO {
	public List<ProductEntity> getAllProduct();
	public Integer addProduct(ProductEntity product);
	

}
