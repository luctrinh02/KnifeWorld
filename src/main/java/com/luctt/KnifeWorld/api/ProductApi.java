package com.luctt.KnifeWorld.api;



import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.service.ProductService;
import com.luctt.KnifeWorld.utilities.GetFullText;

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
		Page<Product> list;
		if ("".equals(name)) {
			list = service.getActiveProduct(pageNumber);
		} else {
			list = service.search(GetFullText.getFullText(name), pageNumber);
		}
		model.addAttribute("products", list);
		return "views/home";
	}
}
