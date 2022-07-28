package com.luctt.KnifeWorld.api;

import java.util.HashMap;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.service.ProductService;
import com.luctt.KnifeWorld.utilities.GetMap;

@RestController
@RequestMapping("/api/products")
public class ProductApi {
	private ProductService service;
	
	public ProductApi(ProductService service) {
		this.service = service;
	}

	@GetMapping("")
	public ResponseEntity<?> getProducts(@RequestParam(name = "page",required = false,defaultValue = "0") Integer pageNumber,
			@RequestParam(name = "keyWord",required = false) String keyWord
			){
		if(keyWord==null) {
			Page<Product> page=service.getActiveProduct(pageNumber);
			HashMap<String, Object> map=GetMap.getData("ok", page);
			return ResponseEntity.ok(map);
		}else {
			Page<Product> page=service.search(keyWord,pageNumber);
			HashMap<String, Object> map=GetMap.getData("ok", page);
			return ResponseEntity.ok(map);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable(name = "id") Integer id){
		Product p=service.getById(id);
		HashMap<String, Object> map=GetMap.getData("ok", p);
		return ResponseEntity.ok(map);
	}
}