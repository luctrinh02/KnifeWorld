package com.luctt.KnifeWorld.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private CommentPK commentPK;
	@Lob
	@NotBlank(message ="Không bỏ trống nội dung")
	private String content;
	@NotBlank(message ="Không bỏ trống đánh giá")
	private Integer rate;
	@ManyToOne
	@JoinColumn(name = "user_id",insertable = false,updatable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name = "product_id",insertable = false,updatable = false)
	private Product product;
	public CommentPK getCommentPK() {
		return commentPK;
	}
	public void setCommentPK(CommentPK commentPK) {
		this.commentPK = commentPK;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "Comment [commentPK=" + commentPK + ", content=" + content + ", rate=" + rate + "]";
	}
	
}
