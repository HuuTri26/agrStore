package agrStore.entity;


import java.util.Date;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Product")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productId")
	private Integer id;
	

	@Column(name = "productName")
	private String productName;

	@Column(name = "price")
	private double price;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "descript")
	private String descript;

	@Column(name = "image")
	private String image;
	
	@Column(name = "unit")
	private String unit;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "createAt")
	private Date createAt;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "updateAt")
	private Date updateAt;
	
	@OneToMany()
	@JoinColumn(name = "providerId")
	private List<ProductEntity> providers;
	
//	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
//	private List<CartItemEntity> cartItems;

}
