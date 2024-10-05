package agrStore.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "CouponEntity")
public class CouponEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "couponId")
	private Integer couponId;
	
	@Column(name = "couponName")
	private String couponName;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "applyTime")
	private Date applyTime;
	
	@Column(name = "discount")
	private float discount;
	
	@Column(name = "maxValue")
	private float maxValue;
	
	@Column(name = "status")
	private boolean status;

	public CouponEntity() {
		super();
	}

	public CouponEntity(Integer couponId, String couponName, Date applyTime, float discount, float maxValue,
			boolean status) {
		super();
		this.couponId = couponId;
		this.couponName = couponName;
		this.applyTime = applyTime;
		this.discount = discount;
		this.maxValue = maxValue;
		this.status = status;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
