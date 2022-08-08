package com.luctt.KnifeWorld.api;

import java.util.HashMap;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.service.ProductService;
import com.luctt.KnifeWorld.utilities.GetMap;

@Controller
@RequestMapping("/api/products")
public class ProductApi {
	private ProductService service;
	
	public ProductApi(ProductService service) {
		this.service = service;
	}

	@GetMapping("")
	public String getProducts(@RequestParam(name = "page",required = false,defaultValue = "0") Integer pageNumber,
			@RequestParam(name = "keyWord",required = false) String keyWord,Model model
			){
		Page<Product> page;
		if(keyWord==null) {
			page=service.getActiveProduct(pageNumber);
		}else {
			page=service.search(keyWord,pageNumber);
		}
		model.addAttribute("products", page);
		return "views/home";
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable(name = "id") Integer id){
		Product p=service.getById(id);
		HashMap<String, Object> map=GetMap.getData("ok", p);
		return ResponseEntity.ok(map);
	}
}
