package com.luctt.KnifeWorld.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.service.ProductService;

@Controller
@RequestMapping("knife-world")
public class HomeController {
	@Autowired
	private ProductService service;
	@GetMapping("")
	public String getProducts(Model model){
		Page<Product> page=service.getActiveProduct(0);
		model.addAttribute("products", page);
		return "guest/home";
	}
}
