package com.luctt.KnifeWorld.api;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luctt.KnifeWorld.entities.Cart;
import com.luctt.KnifeWorld.entities.CartPK;
import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.service.CartService;
import com.luctt.KnifeWorld.service.ProductService;
import com.luctt.KnifeWorld.utilities.GetMap;

@RestController
@RequestMapping("/api/cart")
public class CartApi {
	private ProductService productService;
	private CartService cartService;
	public CartApi(ProductService productService, CartService cartService) {
		this.productService = productService;
		this.cartService = cartService;
	}
	@GetMapping("")
	public ResponseEntity<?> getAll(HttpServletRequest request){
		HashMap<String, Object> map=GetMap.getData("ok", cartService.getAll(request));
		return ResponseEntity.ok(map);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> addToCart(@PathVariable(name = "id") Integer id,HttpServletRequest request,@RequestParam(name = "amount") Integer amount){
		Product p=productService.getById(id);
		User u=(User)request.getSession().getAttribute("user");
		if(amount>p.getAmount()) {
			HashMap<String, Object> map=GetMap.getData("error", "Số lượng tối đa chỉ còn "+p.getAmount());
			return ResponseEntity.ok(map);
		}else {
			CartPK cartPK=new CartPK();
			cartPK.setProductId(p.getId());
			cartPK.setUserId(u.getId());
			Optional<Cart> optional=cartService.getById(cartPK);
			if(optional.isPresent()) {
				Cart c=optional.get();
				if(amount+c.getAmount()>p.getAmount()) {
					HashMap<String, Object> map=GetMap.getData("error", "Số lượng tối đa chỉ còn "+p.getAmount());
					return ResponseEntity.ok(map);
				}else {
					c.setAmount(c.getAmount()+amount);
					cartService.save(c);
					HashMap<String, Object> map=GetMap.getData("ok", cartService.getAll(request));
					return ResponseEntity.ok(map);
				}
			}else {
				Cart c=new Cart();
				c.setAmount(amount);
				c.setCartPK(cartPK);
				c.setProduct(p);
				c.setUser(u);
				cartService.save(c);
				HashMap<String, Object> map=GetMap.getData("ok", cartService.getAll(request));
				return ResponseEntity.ok(map);
			}
		}
	}
	@DeleteMapping("")
	public ResponseEntity<?> delete(List<CartPK> ids,HttpServletRequest request){
		try {
			cartService.removes(ids);
			HashMap<String, Object> map=GetMap.getData("ok", cartService.getAll(request));
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			HashMap<String, Object> map=GetMap.getData("ok", "Hệ thống bận. Vui lòng thử lại sau");
			return ResponseEntity.ok(map);
		}
	}
}
