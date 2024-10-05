package agrStore.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "FeedbackEntity")
public class FeedbackEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedbackId")
	private Integer feedbackId;
	
	@OneToOne(mappedBy = "feedback", fetch = FetchType.LAZY)
	private OrderBillEntity orderBill;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "star")
	private int star;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "createAt")
	private Date createAt;

	public FeedbackEntity() {
		super();
	}

	public FeedbackEntity(Integer feedbackId, String comment, int star, Date createAt) {
		super();
		this.feedbackId = feedbackId;
		this.comment = comment;
		this.star = star;
		this.createAt = createAt;
	}

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public OrderBillEntity getOrderBill() {
		return orderBill;
	}

	public void setOrderBill(OrderBillEntity orderBill) {
		this.orderBill = orderBill;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStar() {
		return star;
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
	
	

}
