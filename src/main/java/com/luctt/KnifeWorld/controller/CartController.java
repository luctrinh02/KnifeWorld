package com.luctt.KnifeWorld.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.luctt.KnifeWorld.entities.Cart;
import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.service.CartService;
import com.luctt.KnifeWorld.service.UserService;

@Controller
public class CartController {
	private UserService userService;
	private CartService cartService;
	public CartController(UserService userService, CartService cartService) {
		this.userService = userService;
		this.cartService = cartService;
	}
	
	@GetMapping("/cart")
	public String cart(Model model,HttpServletRequest request) {
		String email = request.getUserPrincipal().getName();
		User u = (User) userService.getByEmail(email);
		List<Cart> carts=cartService.getAll(u);
		model.addAttribute("carts", carts);
		return "user/cart";
	}
}
