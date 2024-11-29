package agrStore.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Account")
public class AccountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accountId")
	private Integer accountId;

	@Column(name = "status")
	private Boolean status;

	@Column(name = "avatar", columnDefinition = "nvarchar")
	private String avatar;

	@Column(name = "gmail", columnDefinition = "nvarchar")
	private String gmail;

	@Column(name = "fullName", columnDefinition = "nvarchar")
	private String fullName;

	@Column(name = "phoneNumber", columnDefinition = "nvarchar")
	private String phoneNumber;

	@Column(name = "password", columnDefinition = "nvarchar")
	private String password;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "createAt")
	private Date createAt;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "updateAt")
	private Date updateAt;

	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	private CartEntity cart;

	@ManyToOne
	@JoinColumn(name = "roleId")
	private RoleEntity role;

	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST})
	@JoinColumn(name = "addressId")
	private AddressEntity address;

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	private List<OrderBillEntity> orderBillList;

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	private List<ImportBillEntity> importBillList;

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	private List<FeedbackEntity> feedbackList;

	public AccountEntity() {
		super();
	}

	public AccountEntity(Integer accountId, Boolean status, String avatar, String gmail, String fullName,
			String phoneNumber, String password, Date createAt, Date updateAt, RoleEntity role, AddressEntity address) {
		super();
		this.accountId = accountId;
		this.status = status;
		this.avatar = avatar;
		this.gmail = gmail;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.role = role;
		this.address = address;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public CartEntity getCart() {
		return cart;
	}

	public void setCart(CartEntity cart) {
		this.cart = cart;
	}

	public List<OrderBillEntity> getOrderBillList() {
		return orderBillList;
	}

	public void setOrderBillList(List<OrderBillEntity> orderBillList) {
		this.orderBillList = orderBillList;
	}

	public List<ImportBillEntity> getImportBillList() {
		return importBillList;
	}

	public void setImportBillList(List<ImportBillEntity> importBillList) {
		this.importBillList = importBillList;
	}

	public List<FeedbackEntity> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<FeedbackEntity> feedbackList) {
		this.feedbackList = feedbackList;
	}

}
