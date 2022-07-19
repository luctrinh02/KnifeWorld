package com.luctt.KnifeWorld.adapter.impl;

import com.luctt.KnifeWorld.adapter.DtoAdapter;
import com.luctt.KnifeWorld.dto.request.UserRequestDto;
import com.luctt.KnifeWorld.entities.User;

public class UserDtoToEntity implements DtoAdapter<UserRequestDto, User> {
	@Override
	public User dtoToEntity(UserRequestDto d) {
		User u=new User();
		u.setAddress(d.getAddress());
		u.setEmail(d.getEmail());
		u.setFullname(d.getFullname());
		u.setPassword(d.getPassword());
		u.setRole(d.getRole());
		u.setStatus(d.getStatus());
		return u;
	}

}
