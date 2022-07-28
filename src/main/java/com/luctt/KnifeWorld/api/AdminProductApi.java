package com.luctt.KnifeWorld.api;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luctt.KnifeWorld.dto.request.ProductRequestDto;
import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.service.ProductService;
import com.luctt.KnifeWorld.utilities.GetMap;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductApi {
	private ProductService service;

	public AdminProductApi(ProductService service) {
		this.service = service;
	}

	@GetMapping("")
	public ResponseEntity<?> getAll(
			@RequestParam(name = "page", required = true, defaultValue = "0") Integer pageNumber,
			@RequestParam(name = "keyWord", required = false) String keyWord) {
		if (keyWord == null) {
			Page<Product> page = service.getAll(pageNumber);
			HashMap<String, Object> map = GetMap.getData("ok", page);
			return ResponseEntity.ok(map);
		} else {
			Page<Product> page = service.adminSearch(keyWord, pageNumber);
			HashMap<String, Object> map = GetMap.getData("ok", page);
			return ResponseEntity.ok(map);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> insert(@Valid ProductRequestDto dto, BindingResult result) {
		if (result.hasErrors()) {
			HashMap<String, Object> map = GetMap.getData("error", result.getAllErrors());
			return ResponseEntity.ok(map);
		} else {
			try {
				Product p = dto.dtoToEntity();
				p = service.save(p);
				Page<Product> page = service.getAll(0);
				HashMap<String, Object> map = GetMap.getData("ok", page);
				return ResponseEntity.ok(map);
			} catch (Exception e) {
				HashMap<String, Object> map = GetMap.getData("error", "Lỗi dữ liệu");
				return ResponseEntity.ok(map);
			}
		}
	}

	@PutMapping("")
	public ResponseEntity<?> update(@Valid ProductRequestDto dto, BindingResult result,
			@RequestParam(name = "id") Integer id) {
		if (result.hasErrors()) {
			HashMap<String, Object> map = GetMap.getData("error", result.getAllErrors());
			return ResponseEntity.ok(map);
		} else {
			try {
				Product p = dto.dtoToEntity();
				p.setId(id);
				p = service.save(p);
				Page<Product> page = service.getAll(0);
				HashMap<String, Object> map = GetMap.getData("ok", page);
				return ResponseEntity.ok(map);
			} catch (Exception e) {
				HashMap<String, Object> map = GetMap.getData("error", "Lỗi dữ liệu");
				return ResponseEntity.ok(map);
			}
		}
	}

	@PatchMapping("{id}")
	public ResponseEntity<?> changeStatus(@PathVariable(name = "id") Integer id,
			@RequestParam(name = "status", required = true) Integer status,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer pageNumer,
			HttpServletRequest request) {
		try {
			service.changeStatus(id, status, request);
			Page<Product> page = service.getAll(pageNumer);
			HashMap<String, Object> map = GetMap.getData("ok", page);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			HashMap<String, Object> map = GetMap.getData("error", "Bạn không có quyền này!");
			return ResponseEntity.ok(map);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
		Product p = service.getById(id);
		if (p.getUser().equals((User) request.getSession().getAttribute("user"))) {
			HashMap<String, Object> map = GetMap.getData("ok", p);
			return ResponseEntity.ok(map);
		} else {
			HashMap<String, Object> map = GetMap.getData("error", "Bạn không có quyền sửa");
			return ResponseEntity.ok(map);
		}
	}
}
