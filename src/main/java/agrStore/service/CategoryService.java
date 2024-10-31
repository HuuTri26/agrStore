package agrStore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.CategoryEntity;

@Transactional
@Service
public interface CategoryService {
	public void addCategory(CategoryEntity category);
	public void updateCategory(CategoryEntity category);
	public List<CategoryEntity> getListCategory();
	public CategoryEntity getCategoryById(Integer id);
}
