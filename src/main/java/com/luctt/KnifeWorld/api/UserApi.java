package com.luctt.KnifeWorld.api;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luctt.KnifeWorld.dto.request.UserRequestDto;
import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.service.UserService;
import com.luctt.KnifeWorld.utilities.GetMap;

@RestController
public class UserApi {
	private UserService service;

	public UserApi(UserService service) {
		this.service = service;
	}
	@PostMapping("/knife-world/registry")
	public ResponseEntity<?> addUser(@Valid UserRequestDto dto,BindingResult result) {
		if(result.hasErrors()) {
			List<ObjectError> list=result.getAllErrors();
			HashMap<String, Object> map=GetMap.getData("error", list);
			return ResponseEntity.status(200).body(map);
		}else {
			User u=dto.dtoToEntity();
			try {
				u=service.save(u);
				HashMap<String, Object> map=GetMap.getData("ok", "Đăng ký thành công!");
				return ResponseEntity.status(200).body(map);
			} catch (Exception e) {
				HashMap<String, Object> map=GetMap.getData("emailError", "Email bị trùng lặp");
				return ResponseEntity.status(200).body(map);
			}
		}
	}
}
