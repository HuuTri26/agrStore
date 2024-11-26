package agrStore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.ProductEntity;

@Transactional
@Service
public interface ProductService {
	public void addProduct(ProductEntity product);
	public void updateProduct(ProductEntity product);
	public List<ProductEntity> getListProduct();
	public ProductEntity getProductById(Integer id);
	public List<ProductEntity> getListProductByCategoryId(Integer cId);
	public List<ProductEntity> getListProductByProviderId(Integer pId);
	public List<ProductEntity> getRandomListProductByLimit(List<ProductEntity> list, Integer limit);
}
