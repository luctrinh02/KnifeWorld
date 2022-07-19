package com.luctt.KnifeWorld.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequestDto {
	@NotBlank(message = "Không bỏ trống email")
	@Email(message = "Không đúng định dạng email")
	private String email;
	@NotBlank(message = "Không bỏ trống mật khẩu")
	@Size(min = 6,message = "Mật khẩu >= 6 ký tự")
	private String password;
	private String fullname;
	private Integer role;
	private Integer status;
	private String address;
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
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}