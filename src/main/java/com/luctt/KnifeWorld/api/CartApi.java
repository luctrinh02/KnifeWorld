package com.luctt.KnifeWorld.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luctt.KnifeWorld.entities.Cart;
import com.luctt.KnifeWorld.entities.CartPK;
import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.service.CartService;
import com.luctt.KnifeWorld.service.ProductService;
import com.luctt.KnifeWorld.service.UserService;
import com.luctt.KnifeWorld.utilities.GetMap;

@RestController
@RequestMapping("/api/cart")
public class CartApi {
	@Autowired
	private UserService userService;
	private ProductService productService;
	private CartService cartService;
	public CartApi(ProductService productService, CartService cartService) {
		this.productService = productService;
		this.cartService = cartService;
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> addToCart(@PathVariable(name = "id") Integer id,HttpServletRequest request,@RequestParam(name = "amount") Integer amount){
		Product p=productService.getById(id);
		String email = request.getUserPrincipal().getName();
		User u = (User) userService.getByEmail(email);
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
					HashMap<String, Object> map=GetMap.getData("ok", "Sản phẩm đã được thêm");
					return ResponseEntity.ok(map);
				}
			}else {
				Cart c=new Cart();
				c.setAmount(amount);
				c.setCartPK(cartPK);
				c.setProduct(p);
				c.setUser(u);
				cartService.save(c);
				HashMap<String, Object> map=GetMap.getData("ok", "Sản phẩm đã được thêm");
				return ResponseEntity.ok(map);
			}
		}
	}
	@DeleteMapping("")
	public ResponseEntity<?> delete(@RequestParam(name = "chk[]",required = false) Integer[] chk,HttpServletRequest request){
		try {
			String email = request.getUserPrincipal().getName();
			User u = (User) userService.getByEmail(email);
			List<CartPK> ids=new ArrayList<>();
			if(chk==null) {
				HashMap<String, Object> map=GetMap.getData("error", "Chưa chọn sản phẩm nào");
				return ResponseEntity.ok(map);
			}
			for(int i=0;i<chk.length;i++) {
				CartPK pk=new CartPK();
				pk.setProductId(chk[i]);
				pk.setUserId(u.getId());
				ids.add(pk);
			}
			cartService.removes(ids);
			HashMap<String, Object> map=GetMap.getData("ok", "Xóa thành công");
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			HashMap<String, Object> map=GetMap.getData("error", "Hệ thống bận. Vui lòng thử lại sau");
			return ResponseEntity.ok(map);
		}
	}
}
