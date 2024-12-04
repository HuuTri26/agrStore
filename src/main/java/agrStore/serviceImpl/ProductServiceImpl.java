package agrStore.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public List<ProductEntity> getListProductByProviderId(Integer pId) {
		// TODO Auto-generated method stub
		return ProductDAO.getListProductByCategotyId(pId);
	}

	@Override
	public List<Object[]> getTheMostPurchasedProduct() {
		// TODO Auto-generated method stub
		return this.ProductDAO.getTheMostPurchasedProduct();
	}

}
