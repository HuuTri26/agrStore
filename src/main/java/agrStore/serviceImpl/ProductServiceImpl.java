package agrStore.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agrStore.DAO.ProductDAO;
import agrStore.entity.ProductEntity;
import agrStore.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDAO ProductDAO;

	@Override
	public void addProduct(ProductEntity product) {
		ProductDAO.addProduct(product);
	}

	@Override
	public void updateProduct(ProductEntity product) {
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

}
