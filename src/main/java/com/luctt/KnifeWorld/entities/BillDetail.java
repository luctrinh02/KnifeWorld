package com.luctt.KnifeWorld.entities;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table
public class BillDetail {
	@EmbeddedId
	private BillDetailPK billDetailPK;
	@ManyToOne
	@JoinColumn(name = "bill_id",insertable = false,updatable = false)
	private Bill bill;
	@ManyToOne
	@JoinColumn(name = "product_id",insertable = false,updatable = false)
	private Product product;
	private Integer amount;
	private BigDecimal price;
	public BillDetailPK getBillDetailPK() {
		return billDetailPK;
	}
	public void setBillDetailPK(BillDetailPK billDetailPK) {
		this.billDetailPK = billDetailPK;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "BillDetail [billDetailPK=" + billDetailPK+ ", product=" + product + ", amount="
				+ amount + ", price=" + price + "]";
	}
	
}
