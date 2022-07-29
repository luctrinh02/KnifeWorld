package com.luctt.KnifeWorld.api;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luctt.KnifeWorld.entities.Comment;
import com.luctt.KnifeWorld.entities.CommentPK;
import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.service.CommentService;
import com.luctt.KnifeWorld.service.ProductService;
import com.luctt.KnifeWorld.utilities.GetMap;

@RestController
@RequestMapping("/api/comments")
public class CommentApi {
	private CommentService commentService;
	private ProductService productService;
	
	public CommentApi(CommentService commentService, ProductService productService) {
		this.commentService = commentService;
		this.productService = productService;
	}

	@GetMapping("")
	public ResponseEntity<?> getByProduct(
			@RequestParam(name = "productId") Integer id
			){
		Product p=productService.getById(id);
		HashMap<String, Object> map=GetMap.getData("ok", commentService.getByProduct(p));
		return ResponseEntity.ok(map);
	}
	@PostMapping("")
	public ResponseEntity<?> save(
			@Valid Comment c,BindingResult result,
			@RequestParam(name ="productId") Integer id,
			HttpServletRequest request
			){
		if(result.hasErrors()) {
			HashMap<String, Object> map=GetMap.getData("ok", result.getAllErrors().get(0));
			return ResponseEntity.ok(map);
		}else {
			Product p=productService.getById(id);
			User u=(User) request.getSession().getAttribute("user");
			CommentPK commentPK=new CommentPK();
			commentPK.setProductId(id);
			commentPK.setUserId(id);
			c.setCommentPK(commentPK);
			c.setProduct(p);
			c.setUser(u);
			commentService.save(c);
			HashMap<String, Object> map=GetMap.getData("ok", commentService.getByProduct(p));
			return ResponseEntity.ok(map);
		}
	}
}
