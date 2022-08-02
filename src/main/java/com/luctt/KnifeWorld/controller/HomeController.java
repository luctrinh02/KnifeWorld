package com.luctt.KnifeWorld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/home")
	public String home() {
		return "NewFile";
	}
	@GetMapping("/index")
	public String index() {
		return "index";
	}
}
