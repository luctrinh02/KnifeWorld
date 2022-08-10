package com.luctt.KnifeWorld.api;



import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.service.ProductService;

@Controller
@RequestMapping("/api/products")
public class ProductApi {
	private ProductService service;
	
	public ProductApi(ProductService service) {
		this.service = service;
	}

	@GetMapping("")
	public String getProducts(@RequestParam(name = "page",required = false,defaultValue = "0") Integer pageNumber,
			@RequestParam(name = "name",required = false) String name,Model model
			){
		Page<Product> page;
			page=service.search("%"+name+"%",pageNumber);
		model.addAttribute("products", page);
		return "views/home";
	}
}
