package com.luctt.KnifeWorld.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table
public class Cart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private CartPK cartPK;
	private Integer amount;
	@ManyToOne
	@JoinColumn(name = "product_id",insertable = false,updatable = false)
	private Product product;
	@ManyToOne
	@JoinColumn(name = "user_id",insertable = false,updatable = false)
	private User user;
	public CartPK getCartPK() {
		return cartPK;
	}
	public void setCartPK(CartPK cartPK) {
		this.cartPK = cartPK;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Cart [cartPK=" + cartPK + ", amount=" + amount + ", product=" + product + ", user=" + user + "]";
	}
	
}
