package agrStore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Account")
public class AccountEntity {
	@Id
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
	@JoinColumn(name = "RoleId")
	private RoleEntity role;
	
	public AccountEntity(Integer id, Boolean status, String avatar, String gmail, String fullName, String phoneNumber,
			String password, Date createAt, Date updateAt, RoleEntity role) {
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
	
	
	
	
}
