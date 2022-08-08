package com.luctt.KnifeWorld.dto.request;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.luctt.KnifeWorld.adapter.DtoAdapter;
import com.luctt.KnifeWorld.entities.User;

public class UserRequestDto implements DtoAdapter<UserRequestDto, User>{
	private Integer id;
	@NotBlank(message = "Không bỏ trống email")
	@Email(message = "Không đúng định dạng email")
	private String email;
	@NotBlank(message = "Không bỏ trống mật khẩu")
	@Size(min = 6,message = "Mật khẩu >= 6 ký tự")
	private String password;
	@NotBlank(message = "Không bỏ trống họ tên")
	private String fullname;
	private Integer role;
	private Integer status;
	@NotBlank(message = "Không bỏ trống địa chỉ")
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public User dtoToEntity() {
		User u=new User();
		u.setId(this.getId());
		u.setAddress(this.getAddress());
		u.setCreatedDate(new Date());
		u.setEmail(this.getEmail());
		u.setFullname(this.getFullname());
		u.setPassword(new BCryptPasswordEncoder().encode(this.getPassword()));
		u.setRole(this.getRole()==null?1:this.getRole());
		u.setStatus(0);
		return u;
	}
	
	
}
