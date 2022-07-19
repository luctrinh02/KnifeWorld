package com.luctt.KnifeWorld.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "email",unique = true)
	private String email;
	private String password;
	@Column(name = "address")
	private String address;
	private String fullname;
	private Integer role;
	private Integer status;
	@Column(name = "create_date")
	private Date createdDate;
	@OneToMany(mappedBy = "user")
	private List<Product> products;
	@OneToMany(mappedBy = "user")
	private List<Cart> carts;
	@OneToMany(mappedBy = "user")
	private List<Bill> bills;
	@OneToMany(mappedBy = "user")
	private List<Notication> notications;
	@OneToMany(mappedBy = "user")
	private List<Comment> comments;
	@OneToMany(mappedBy = "user")
	private List<Origin> origins;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<Cart> getCarts() {
		return carts;
	}
	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	public List<Bill> getOrders() {
		return bills;
	}
	public void setOrders(List<Bill> bills) {
		this.bills = bills;
	}
	public List<Notication> getNotications() {
		return notications;
	}
	public void setNotications(List<Notication> notications) {
		this.notications = notications;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Origin> getOrigins() {
		return origins;
	}
	public void setOrigins(List<Origin> origins) {
		this.origins = origins;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public List<Bill> getBills() {
		return bills;
	}
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password 
				+ ", address=" + address + ", fullname=" + fullname + ", status=" + status + ", createdDate="
				+ createdDate + "]";
	}
	
	
	
	
}
