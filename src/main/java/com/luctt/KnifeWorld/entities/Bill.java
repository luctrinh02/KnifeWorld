package com.luctt.KnifeWorld.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table
public class Bill implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "total_money")
	private BigDecimal totalMoney;
	@Column(name = "created_date")
	private Date createdDate;
	@ManyToOne
	@JoinColumn(name = "user_id",insertable = false,updatable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name = "status_id",insertable = false,updatable = false)
	private BillStatus status;
	@OneToMany(mappedBy = "bill")
	private List<BillDetail> billDetails;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public BillStatus getStatus() {
		return status;
	}
	public void setStatus(BillStatus status) {
		this.status = status;
	}
	
	public List<BillDetail> getBillDetails() {
		return billDetails;
	}
	public void setBillDetails(List<BillDetail> billDetails) {
		this.billDetails = billDetails;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", totalMoney=" + totalMoney + ", createdDate=" + createdDate + ", user=" + user
				+ ", status=" + status + "]";
	}
	
}
