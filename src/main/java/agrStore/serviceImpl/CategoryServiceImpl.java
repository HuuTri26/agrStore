package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.CategoryDAO;
import agrStore.entity.CategoryEntity;
import agrStore.service.CategoryService;
import agrStore.utility.Ultility;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDAO CategoryDAO;

	@Autowired
	Ultility ultility;
	
	@Override
	public void addCategory(CategoryEntity category) {
		category.setCategoryName(ultility.XSSSanitizeHTML(category.getCategoryName()));
		category.setDescript(ultility.XSSSanitizeHTML(category.getDescript()));
		
		CategoryDAO.addCategory(category);
		
	}

	@Override
	public void updateCategory(CategoryEntity category) {
		category.setCategoryName(ultility.XSSSanitizeHTML(category.getCategoryName()));
		category.setDescript(ultility.XSSSanitizeHTML(category.getDescript()));
		
		CategoryDAO.updateCategory(category);
		
	}

	@Override
	public List<CategoryEntity> getListCategory() {
		return CategoryDAO.getListCategory();
	}

	@Override
	public CategoryEntity getCategoryById(Integer id) {
		return CategoryDAO.getCategotyById(id);
	}
	
}
