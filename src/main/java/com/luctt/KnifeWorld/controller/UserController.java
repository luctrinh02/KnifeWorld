package com.luctt.KnifeWorld.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	@GetMapping("/admin/users")
	public String getUsers(Model model) {
		Page<User> list=service.getByPage(0,"");
		model.addAttribute("list", list);
		return "admin/user/index";
	}
	@GetMapping("/api/admin/users")
	public String getUsers(@RequestParam(name = "page",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(name = "name",defaultValue = "",required = false) String name,Model model
			) {
			Page<User> list=service.getByPage(pageNumber,name);
			model.addAttribute("list", list);
			return "admin/user/indexView";
		}
	@GetMapping("/admin/users/create")
	public String newUser() {
		return "admin/user/create";
	}
	@GetMapping("/admin/users/{id}")
	public String update(Model model,@PathVariable(name = "id") Integer id) {
		User u=service.getById(id);
		model.addAttribute("user", u);
		return "admin/user/update";
	}
}
