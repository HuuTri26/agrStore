package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.CategoryDAO;
import agrStore.entity.CategoryEntity;
import agrStore.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	public List<CategoryEntity> getAllCategory() {
		// TODO Auto-generated method stub
		List<CategoryEntity> categories = this.categoryDAO.getAllCategory();
		return categories;
	}

}
