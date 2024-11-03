package agrStore.DAO;

import java.util.List;

import agrStore.entity.CategoryEntity;

public interface CategoryDAO {
	public void addCategory(CategoryEntity category);
	public void updateCategory(CategoryEntity category);
	public List<CategoryEntity> getListCategory();
	public CategoryEntity getCategotyById(Integer id);
}
