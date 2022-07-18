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

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.springframework.stereotype.Indexed;


@Entity
@Indexed
@Table
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Field(index = Index.YES,analyze = Analyze.YES,store = Store.NO)
	private String name;
	@Field(index = Index.YES,analyze = Analyze.YES,store = Store.NO)
	private String material;
	
	@Field(index = Index.YES,analyze = Analyze.YES,store = Store.NO)
	@Column(name = "blade_length")
	private Double bladeLength;
	
	@Field(index = Index.YES,analyze = Analyze.YES,store = Store.NO)
	@Column(name="hilt_length")
	private Double hilt_length;
	
	private BigDecimal price;
	
	private Integer amount;
	
	@Column(name ="created_date")
	private Date createdDate;
	
	@Column(name="last_changed_date")
	private Date lastChangedDate;
	
	private Integer status;
	
	private Double weight;
	
	@Column(name = "blade_wide")
	private Double bladeWide;
	
	@ManyToOne
	@JoinColumn(name = "manufacturer_id",insertable = false,updatable = false)
	private Manufacturer manufacturer;
	
	@ManyToOne
	@JoinColumn(name = "origin_id",insertable = false,updatable = false)
	private Origin origin;
	
	@ManyToOne
	@JoinColumn(name = "created_by",insertable = false,updatable = false)
	private User user;
	
	@OneToMany(mappedBy = "product")
	private List<Cart> carts;
	
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy = "product")
	private List<Comment> comments;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Double getBladeLength() {
		return bladeLength;
	}

	public void setBladeLength(Double bladeLength) {
		this.bladeLength = bladeLength;
	}

	public Double getHilt_length() {
		return hilt_length;
	}

	public void setHilt_length(Double hilt_length) {
		this.hilt_length = hilt_length;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastChangedDate() {
		return lastChangedDate;
	}

	public void setLastChangedDate(Date lastChangedDate) {
		this.lastChangedDate = lastChangedDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getBladeWide() {
		return bladeWide;
	}

	public void setBladeWide(Double bladeWide) {
		this.bladeWide = bladeWide;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", material=" + material + ", bladeLength=" + bladeLength
				+ ", hilt_length=" + hilt_length + ", price=" + price + ", amount=" + amount + ", createdDate="
				+ createdDate + ", lastChangedDate=" + lastChangedDate + ", status=" + status + ", weight=" + weight
				+ ", bladeWide=" + bladeWide + ", manufacturer=" + manufacturer + ", origin=" + origin + ", user="
				+ user + "]";
	}
}
