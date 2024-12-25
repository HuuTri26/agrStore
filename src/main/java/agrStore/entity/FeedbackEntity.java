package agrStore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Feedback")
public class FeedbackEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedbackId")
	private Integer feedbackId;

	@Column(name = "comment")
	private String comment;

	@Column(name = "star")
	private Integer star;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "createAt")
	private Date createAt;

	@ManyToOne()
	@JoinColumn(name = "accountId")
	private AccountEntity account;

	@ManyToOne()
	@JoinColumn(name = "orderBillDetailId")
	private OrderBillDetailEntity orderBillDetail;

	public FeedbackEntity(Integer feedbackId, String comment, int star, Date createAt, AccountEntity account,
			OrderBillDetailEntity orderBillDetail) {
		super();
		this.feedbackId = feedbackId;
		this.comment = comment;
		this.star = star;
		this.createAt = createAt;
		this.account = account;
		this.orderBillDetail = orderBillDetail;
	}

	public FeedbackEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getStar() {
		return star;
	}

	public int getStarPercentage() {
		return (int) ((this.star / 5.0) * 100);
	}

	public void setStar(int star) {
		this.star = star;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public OrderBillDetailEntity getOrderBillDetail() {
		return orderBillDetail;
	}

	public void setOrderBillDetail(OrderBillDetailEntity orderBillDetail) {
		this.orderBillDetail = orderBillDetail;
	}

	@Override
	public String toString() {
		return "FeedbackEntity [feedbackId=" + feedbackId + ", comment=" + comment + ", star=" + star + ", createAt="
				+ createAt + "]";
	}
	
}
