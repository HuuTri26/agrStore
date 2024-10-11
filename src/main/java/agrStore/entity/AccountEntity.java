package agrStore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private Integer id;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "gmail")
	private String gmail;
	
	@Column(name = "fullName")
	private String fullName;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(name = "password")
	private String password;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "createAt")
	private Date createAt;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "updateAt")
	private Date updateAt;
	
	@ManyToOne
	@JoinColumn(name = "roleId")
	private RoleEntity role;
	
	@OneToOne()
	@JoinColumn(name = "addressId")
	private AddressEntity address;
	
	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private CustomerEntity customer;
	
	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private StaffEntity staff;
	
	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private AdminEntity admin;

	public AccountEntity(Integer id, Boolean status, String avatar, String gmail, String fullName, String phoneNumber,
			String password, Date createAt, Date updateAt, RoleEntity role, AddressEntity address,
			CustomerEntity customer, StaffEntity staff, AdminEntity admin) {
		super();
		this.id = id;
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
		this.customer = customer;
		this.staff = staff;
		this.admin = admin;
	}

	public AccountEntity() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	@PrePersist
	protected void onCreate() {
		this.createAt = new Date();
		if (this.updateAt == null) {
			this.updateAt = new Date();
		}
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updateAt = new Date();
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

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public StaffEntity getStaff() {
		return staff;
	}

	public void setStaff(StaffEntity staff) {
		this.staff = staff;
	}

	public AdminEntity getAdmin() {
		return admin;
	}

	public void setAdmin(AdminEntity admin) {
		this.admin = admin;
	}
	
}
