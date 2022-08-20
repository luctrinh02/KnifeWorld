package com.luctt.KnifeWorld.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.service.ProductService;
import com.luctt.KnifeWorld.utilities.GetFullText;

@Controller
public class ProductController {
	@Autowired
	private ProductService service;

	@GetMapping("/admin/products")
	public String getProduct(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "name", defaultValue = "") String name) {
		Page<Product> list;
		if ("".equals(name)) {
			list = service.adminAll(page);
		} else {
			list = service.adminSearch(GetFullText.getFullText(name), page);
		}
		model.addAttribute("list", list);
		return "admin/product/index";
	}

	@GetMapping("/api/admin/products")
	public String getProduct(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(name = "name", defaultValue = "", required = false) String name, Model model) {
		Page<Product> list;
		if ("".equals(name)) {
			list = service.adminAll(page);
		} else {
			list = service.adminSearch(GetFullText.getFullText(name), page);
		}
		return "admin/product/indexView";
	}

	@GetMapping("/admin/products/create")
	public String getForm() {
		return "admin/product/create";
	}

	@GetMapping("/admin/products/{id}")
	public String getProduct(Model model, @PathVariable("id") Integer id) {
		Product p = service.getById(id);
		model.addAttribute("obj", p);
		return "admin/product/update";
	}

	@GetMapping("/knife-world/products/{id}")
	public String getProduct(@PathVariable("id") Integer id, Model model) {
		Product p = service.getById(id);
		model.addAttribute("product", p);
		return "guest/detail";
	}
}
