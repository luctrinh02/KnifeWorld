package com.luctt.KnifeWorld.dto.response;


public class UserResponseDto {
	private Integer id;
	private String email;
	private String password;
	private String fullname;
	private String role;
	private String status;
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
	public String getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role==0?"Admin":"User";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status==0?"Hiệu lực":"Vô hiệu";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
