package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.CategoryDAO;
import agrStore.entity.CategoryEntity;
import agrStore.service.CategoryService;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDAO CategoryDAO;

	@Override
	public void addCategory(CategoryEntity category) {
		CategoryDAO.addCategory(category);
		
	}

	@Override
	public void updateCategory(CategoryEntity category) {
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
