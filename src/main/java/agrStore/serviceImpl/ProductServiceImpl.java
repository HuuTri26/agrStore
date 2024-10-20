package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.ProductDAO;
import agrStore.entity.ProductEntity;
import agrStore.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<ProductEntity> getAllProduct() {
		// TODO Auto-generated method stub
		List<ProductEntity> products = this.productDAO.getAllProduct();
		return products;
	}

	@Override
	public Integer addProduct(ProductEntity product) {
		// TODO Auto-generated method stub
		int savedProduct = this.productDAO.addProduct(product);
		return savedProduct;
	}

}
