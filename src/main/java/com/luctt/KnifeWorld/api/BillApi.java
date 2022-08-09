package com.luctt.KnifeWorld.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luctt.KnifeWorld.entities.Bill;
import com.luctt.KnifeWorld.entities.BillDetail;
import com.luctt.KnifeWorld.entities.BillDetailPK;
import com.luctt.KnifeWorld.entities.CartPK;
import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.service.BillDetailService;
import com.luctt.KnifeWorld.service.BillService;
import com.luctt.KnifeWorld.service.CartService;
import com.luctt.KnifeWorld.service.ProductService;
import com.luctt.KnifeWorld.service.UserService;
import com.luctt.KnifeWorld.utilities.GetMap;

@RestController
@RequestMapping("/bills")
public class BillApi {
	private BillService billService;
	private CartService cartService;
	private UserService userService;
	private ProductService productService;
	private BillDetailService billDetailService;
	private BigDecimal total;
	
	public BillApi(BillService billService, CartService cartService, UserService userService,
			ProductService productService, BillDetailService billDetailService) {
		this.billService = billService;
		this.cartService = cartService;
		this.userService = userService;
		this.productService = productService;
		this.billDetailService = billDetailService;
	}
	@GetMapping("")
	public ResponseEntity<?> getHistory(HttpServletRequest request,
			@RequestParam(name = "page",required = true,defaultValue = "0") Integer page
			){
		String email =request.getUserPrincipal().getName();
		User u=(User) userService.getByEmail(email);
		HashMap<String, Object> map=GetMap.getData("ok", billService.getHistory(u, page));
		return ResponseEntity.ok(map);
	}
	@PutMapping("")
	public ResponseEntity<?> remove(@RequestParam(name = "id") Integer id,
			HttpServletRequest request,
			@RequestParam(name = "page",required = true,defaultValue = "0") Integer page
			){
		Bill bill=billService.getById(id);
		if(bill.getStatus()!=1) {
			billService.changeStatus(id, 2);
			String email =request.getUserPrincipal().getName();
			User u=(User) userService.getByEmail(email);
			List<BillDetail> details=billDetailService.getByBil(bill);
			for(BillDetail billDetail:details) {
				Product p=billDetail.getProduct();
				p.setAmount(p.getAmount()+billDetail.getAmount());
				productService.save(p);
			}
			HashMap<String, Object> map=GetMap.getData("ok", billService.getHistory(u, page));
			return ResponseEntity.ok(map);
		}else {
			HashMap<String, Object> map=GetMap.getData("error", "Không thể hủy đơn do đơn đã được chấp nhận");
			return ResponseEntity.ok(map);
		}
	}
	@PostMapping("")
	public ResponseEntity<?> add(@RequestParam(name = "chk[]",required = false) Integer[] chk
			,@RequestParam(name = "amount[]",required = false,defaultValue = "0") Integer[] amountArr,HttpServletRequest request){
		String email =request.getUserPrincipal().getName();
		User u=(User) userService.getByEmail(email);
		List<Integer> productId=Arrays.asList(chk);
		List<Integer> amount=Arrays.asList(amountArr);
		Bill bill=new Bill();
		bill.setCreatedDate(new Date());
		bill.setStatus(0);
		bill.setTotalMoney(total);
		bill.setUser(u);
		bill=billService.save(bill);
		List<Product> list=productService.getByIds(productId);
		List<CartPK> cartPKs=new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			Product p=list.get(i);
			CartPK cartPK=new CartPK();
			cartPK.setProductId(p.getId());
			cartPK.setUserId(u.getId());
			BillDetailPK pk=new BillDetailPK();
			pk.setBillId(bill.getId());
			pk.setProductId(p.getId());
			BillDetail detail=new BillDetail();
			detail.setBillDetailPK(pk);
			detail.setAmount(amount.get(i));
			detail.setPrice(p.getPrice());
			detail.setBill(bill);
			p.setAmount(p.getAmount()-amount.get(i));
			productService.save(p);
			billDetailService.save(detail);
			cartPKs.add(cartPK);
		}
		cartService.removes(cartPKs);
		HashMap<String, Object> map=GetMap.getData("ok", "Đặt hàng thành công");
		return ResponseEntity.ok(map);
	}
	@GetMapping("/check")
	public ResponseEntity<?> check(@RequestParam(name = "chk[]",required = false) Integer[] chk
			,@RequestParam(name = "amount[]",required = false,defaultValue = "0") Integer[] amountArr){
		List<Integer> productId=Arrays.asList(chk);
		List<Integer> amount=Arrays.asList(amountArr);
		total=new BigDecimal(0);
		List<Product> list=productService.getByIds(productId);
		HashMap<String, Object> map;
		for(Integer x:amount) {
			if(x<=0) {
				map=GetMap.getData("error", "Số lượng sản phẩm phải lớn hơn 0");
				return ResponseEntity.ok(map);
			}
		}
		for(int i=0;i<productId.size();i++) {
			Product p=list.get(i);
			if(amount.get(i)>p.getAmount()) {
				map=GetMap.getData("error", "Sản phẩm id:"+p.getId()+"-"+p.getName()+" chỉ còn "+p.getAmount());
				return ResponseEntity.ok(map);
			}else {
				BigDecimal amountBigDecimal=new BigDecimal(amount.get(i));
				total=total.add(amountBigDecimal.multiply(p.getPrice()));
			}
		}
		map=GetMap.getData("ok", total);
		return ResponseEntity.ok(map);
		
	}
	
}
