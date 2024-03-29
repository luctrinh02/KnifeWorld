package com.luctt.KnifeWorld.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.multipart.MultipartFile;

import com.luctt.KnifeWorld.dto.request.ProductRequestDto;
import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.service.ProductService;
import com.luctt.KnifeWorld.service.UserService;
import com.luctt.KnifeWorld.utilities.GetMap;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductApi {
	@Autowired
	private ServletContext app;
	private ProductService service;
	private UserService userService;

	public AdminProductApi(ProductService service, UserService userService) {
		this.service = service;
		this.userService = userService;
	}

	@PostMapping("")
	public ResponseEntity<?> insert(@Valid ProductRequestDto dto, BindingResult result, HttpServletRequest request,
			@RequestParam(name = "img") MultipartFile img) throws IllegalStateException, IOException {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			HashMap<String, Object> map = GetMap.getData("error", list);
			return ResponseEntity.ok(map);
		} else {
			try {
				String email = request.getUserPrincipal().getName();
				User u = (User) userService.getByEmail(email);
				Product p = dto.dtoToEntity();
				p.setUser(u);
				if (!img.isEmpty()) {
					String fileName = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
					File file = new File(
							new File("src\\main\\resources\\static\\imgUpload").getAbsolutePath() + "/" + fileName);
					if (!file.exists()) {
						file.mkdirs();
					}
					img.transferTo(file);
					p.setImage(fileName);
				} else {
					return null;
				}
				p = service.save(p);
				HashMap<String, Object> map = GetMap.getData("ok", "Thêm thành công");
				return ResponseEntity.ok(map);
			} catch (Exception e) {
				e.printStackTrace();
				HashMap<String, Object> map = GetMap.getData("error2", "Lỗi dữ liệu");
				return ResponseEntity.ok(map);
			}
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid ProductRequestDto dto, BindingResult result,
			@PathVariable(name = "id") Integer id,@RequestParam(name = "img") MultipartFile img) {
		if (result.hasErrors()) {
			HashMap<String, Object> map = GetMap.getData("error", result.getAllErrors());
			return ResponseEntity.ok(map);
		} else {
			try {
				Product p = service.getById(id);
				p.setAmount(Integer.parseInt(dto.getAmount()));
				p.setBladeLength(Integer.parseInt(dto.getBladeLength()));
				p.setBladeWide(Integer.parseInt(dto.getBladeWide()));
				p.setLastChangedDate(new Date());
				p.setDescription(dto.getDescription());
				p.setHiltLength(Integer.parseInt(dto.getHiltLength()));
				p.setMaterial(dto.getMaterial());
				p.setName(dto.getName());
				p.setOrigin(dto.getOrigin());
				p.setPrice(new BigDecimal(dto.getPrice()));
				p.setStatus(0);
				p.setWeight(Integer.parseInt(dto.getWeight()));
				if (!img.isEmpty()) {
					String fileName = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
					File file = new File(
							new File("src\\main\\resources\\static\\imgUpload").getAbsolutePath() + "/" + fileName);
					if (!file.exists()) {
						file.mkdirs();
					}
					img.transferTo(file);
					p.setImage(fileName);
				} 
				p = service.save(p);
				HashMap<String, Object> map = GetMap.getData("ok", "Sửa thành công");
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
			return null;
		} catch (Exception e) {
			HashMap<String, Object> map = GetMap.getData("error", "Bạn không có quyền này!");
			return ResponseEntity.ok(map);
		}
	}
	

	@GetMapping("/detail")
	public ResponseEntity<?> getProductById(HttpServletRequest request,@RequestParam("id") Integer id){
		String email =request.getUserPrincipal().getName();
		User u=(User) userService.getByEmail(email);
		Product p=service.getById(id);
		if(p.getUser().equals(u)) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.ok(false);
		}
	}
}
