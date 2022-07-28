package com.luctt.KnifeWorld.dto.request;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.luctt.KnifeWorld.adapter.DtoAdapter;
import com.luctt.KnifeWorld.entities.Product;

public class ProductRequestDto implements DtoAdapter<ProductRequestDto, Product>{
	private Integer id;
	@NotBlank(message = "Không bỏ trống tên")
	private String name;
	@NotBlank(message = "Không bỏ trống chất liệu")
	private String material;
	@Positive(message = "Là số nguyên lớn hơn 0")
	@Min(value = 0,message = "Không nhỏ hơn 0")
	private String bladeLength;
	@Positive(message = "Là số nguyên lớn hơn 0")
	@Min(value = 0,message = "Không nhỏ hơn 0")
	private String hiltLength;
	@Positive(message = "Là số nguyên lớn hơn 0")
	@Size(max = 10, message = "Số tiền quá lớn")
	private String price;
	@Positive(message = "Là số nguyên lớn hơn 0")
	@Size(max = 4, message = "Số lượng quá lớn")
	private String amount;
	private Integer status;
	@Positive(message = "Là số nguyên lớn hơn 0")
	@Size(max = 5, message = " Quá nặng")
	private String weight;
	@Positive(message = "Là số nguyên lớn hơn 0")
	@Size(max = 5, message = " Quá dày")
	private String bladeWide;
	@NotBlank(message = "Không bỏ trống hình ảnh")
	private String image;
	private Integer manufacturerId;
	private Integer originId;
	private Integer userId;
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
	public String getBladeLength() {
		return bladeLength;
	}
	public void setBladeLength(String bladeLength) {
		this.bladeLength = bladeLength;
	}
	public String getHiltLength() {
		return hiltLength;
	}
	public void setHiltLength(String hiltLength) {
		this.hiltLength = hiltLength;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public Integer getOriginId() {
		return originId;
	}
	public void setOriginId(Integer originId) {
		this.originId = originId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public Product dtoToEntity() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
