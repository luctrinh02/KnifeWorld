package com.luctt.KnifeWorld.api;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luctt.KnifeWorld.dto.request.UserRequestDto;
import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.service.UserService;
import com.luctt.KnifeWorld.utilities.GetMap;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserApi {
	private UserService service;

	public AdminUserApi(UserService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUsers(@RequestParam(name = "page",defaultValue = "0",required = false) Integer pageNumber
			,@PathVariable(name = "id",required = false) Integer id
			) {
		if(id==null) {
			Page<User> page=service.getByPage(pageNumber);
			HashMap<String, Object> map=GetMap.getData("ok", page);
			return ResponseEntity.status(HttpStatus.OK).body(map);
		}else {
			User u=service.getById(id);
			HashMap<String, Object> map=GetMap.getData("ok", u);
			return ResponseEntity.status(HttpStatus.OK).body(map);
		}
	}
	@PostMapping("")
	public ResponseEntity<?> addUser(@Valid UserRequestDto dto,BindingResult result) {
		if(result.hasErrors()) {
			List<ObjectError> list=result.getAllErrors();
			HashMap<String, Object> map=GetMap.getData("ok", list);
			return ResponseEntity.status(200).body(map);
		}else {
			User u=dto.dtoToEntity();
			try {
				u=service.save(u);
				Page<User> page=service.getByPage(0);
				HashMap<String, Object> map=GetMap.getData("ok", page);
				return ResponseEntity.status(200).body(map);
			} catch (Exception e) {
				HashMap<String, Object> map=GetMap.getData("error", "Email bị trùng lặp");
				return ResponseEntity.status(200).body(map);
			}
		}
	}
	@PatchMapping("{id}")
	public ResponseEntity<?> changeStatus(@PathVariable(name = "id") Integer id,@RequestParam(name = "status",required = true) Integer status
			,@RequestParam(name = "page",required = false,defaultValue = "0") Integer pageNumer,HttpServletRequest request
			){
		try {
			service.changeStatus(id,status,request);
			Page<User> page=service.getByPage(pageNumer);
			HashMap<String, Object> map=GetMap.getData("ok", page);
			return ResponseEntity.ok(map);
		} catch (NullPointerException e) {
			HashMap<String, Object> map=GetMap.getData("ok", "Không tồn tại người dùng này");
			return ResponseEntity.ok(map);
		} catch (IllegalArgumentException e) {
			HashMap<String, Object> map=GetMap.getData("ok", "Không thể tự vô hiệu chính mình");
			return ResponseEntity.ok(map);
		}
	}
	@PutMapping("")
	public ResponseEntity<?> update(@Valid UserRequestDto dto,BindingResult result,@RequestParam(name = "id") Integer id) {
		if(result.hasErrors()) {
			List<ObjectError> list=result.getAllErrors();
			HashMap<String, Object> map=GetMap.getData("ok", list);
			return ResponseEntity.status(200).body(map);
		}else {
			User u=dto.dtoToEntity();
			try {
				u.setId(id);
				u=service.save(u);
				Page<User> page=service.getByPage(0);
				HashMap<String, Object> map=GetMap.getData("ok", page);
				return ResponseEntity.status(200).body(map);
			} catch (Exception e) {
				HashMap<String, Object> map=GetMap.getData("error", "Email bị trùng lặp");
				return ResponseEntity.status(200).body(map);
			}
		}
	}
	
}
