package agrStore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class CategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryId")
	private Integer categoryId;
	
	@Column(name = "categoryName")
	private String categoryName;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "descript")
	private String descript;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<ProductEntity> productList;

	public CategoryEntity() {
		super();
	}

	public CategoryEntity(Integer categoryId, String categoryName, Boolean status, String image, String descript) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.status = status;
		this.image = image;
		this.descript = descript;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public List<ProductEntity> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductEntity> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
		return "CategoryEntity [categoryId=" + categoryId + ", categoryName=" + categoryName + ", status=" + status
				+ ", image=" + image + ", descript=" + descript + "]";
	}
	
}
