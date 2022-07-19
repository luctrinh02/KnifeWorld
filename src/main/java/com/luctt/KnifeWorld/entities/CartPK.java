package com.luctt.KnifeWorld.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "user_id",insertable = false,updatable = false)
	private Integer userId;
	@Column(name = "product_id",insertable = false,updatable = false)
	private Integer productId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(productId, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartPK other = (CartPK) obj;
		return Objects.equals(productId, other.productId) && Objects.equals(userId, other.userId);
	}
	@Override
	public String toString() {
		return "CartPK [userId=" + userId + ", productId=" + productId + "]";
	}
	
}
