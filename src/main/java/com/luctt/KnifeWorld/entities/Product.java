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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private String material;
	
	@Column(name = "blade_length")
	private String bladeLength;
	
	@Column(name="hilt_length")
	private String hilt_length;
	
	private BigDecimal price;
	
	private Integer amount;
	@Temporal(TemporalType.DATE)
	@Column(name ="created_date")
	private Date createdDate;
	@Temporal(TemporalType.DATE)
	@Column(name="last_changed_date")
	private Date lastChangedDate;
	
	private Integer status;
	
	private String weight;
	
	@Column(name = "blade_wide")
	private String bladeWide;
	
	private String image;
	private String description;
	@ManyToOne
	@JoinColumn(name = "manufacturer_id",insertable = false,updatable = false)
	private Manufacturer manufacturer;
	
	@ManyToOne
	@JoinColumn(name = "origin_id",insertable = false,updatable = false)
	private Origin origin;
	
	@ManyToOne
	@JoinColumn(name = "created_by",insertable = false,updatable = false)
	private User user;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<Cart> carts;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<BillDetail> billDetails;
	@JsonIgnore
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


	public List<BillDetail> getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(List<BillDetail> billDetails) {
		this.billDetails = billDetails;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

	public String getBladeLength() {
		return bladeLength;
	}

	public void setBladeLength(String bladeLength) {
		this.bladeLength = bladeLength;
	}

	public String getHilt_length() {
		return hilt_length;
	}

	public void setHilt_length(String hilt_length) {
		this.hilt_length = hilt_length;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBladeWide() {
		return bladeWide;
	}

	public void setBladeWide(String bladeWide) {
		this.bladeWide = bladeWide;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
