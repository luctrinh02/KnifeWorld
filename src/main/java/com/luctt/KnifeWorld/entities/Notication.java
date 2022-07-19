package com.luctt.KnifeWorld.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Notication implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noticationPK;
	private String message;
	private String status;
	@ManyToOne
	@JoinColumn(name = "user_id",insertable = false,updatable = false)
	private User user;
	@Column(name = "bill_id")
	private Integer billId;
	public Integer getNoticationPK() {
		return noticationPK;
	}
	public void setNoticationPK(Integer noticationPK) {
		this.noticationPK = noticationPK;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	@Override
	public String toString() {
		return "Notication [noticationPK=" + noticationPK + ", message=" + message + ", status=" + status + ", user="
				+ user + ", billId=" + billId + "]";
	}
	
}
