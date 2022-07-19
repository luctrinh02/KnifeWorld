package com.luctt.KnifeWorld.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BillDetailPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "bill_id",insertable = false,updatable = false)
	private Integer billId;
	@Column(name = "product_id",insertable = false,updatable = false)
	private Integer productId;
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(billId, productId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillDetailPK other = (BillDetailPK) obj;
		return Objects.equals(billId, other.billId) && Objects.equals(productId, other.productId);
	}
	@Override
	public String toString() {
		return "BillDetailPK [billId=" + billId + ", productId=" + productId + "]";
	}
	
	
}
