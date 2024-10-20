package agrStore.service;

import java.util.List;

import agrStore.entity.ProductEntity;

public interface ProductService {
	
	public List<ProductEntity> getAllProduct();
	public Integer addProduct(ProductEntity product);
}
