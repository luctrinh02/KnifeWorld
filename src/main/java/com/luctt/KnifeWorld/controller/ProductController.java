package com.luctt.KnifeWorld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService service;
	@GetMapping("/admin/products")
	public String getProduct(Model model,@RequestParam(name = "page",defaultValue = "0") Integer page,
			@RequestParam(name = "name",defaultValue = "") String name
			) {
		Page<Product> list=service.adminSearch("%"+name+"%", page);
		model.addAttribute("list", list);
		return "admin/product/index";
	}
	@GetMapping("/api/admin/products")
	public String getProduct(@RequestParam(name = "page",defaultValue = "0",required = false) Integer page,
			@RequestParam(name = "name",defaultValue = "",required = false) String name,Model model
			) {
			Page<Product> list=service.adminSearch("%"+name+"%", page);
			model.addAttribute("list", list);
			return "admin/product/indexView";
		}
	@GetMapping("/admin/products/create")
	public String getForm() {
		return "admin/product/create";
	}
}
