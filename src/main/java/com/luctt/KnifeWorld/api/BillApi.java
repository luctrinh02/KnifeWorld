package com.luctt.KnifeWorld.api;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luctt.KnifeWorld.entities.Bill;
import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.service.BillService;
import com.luctt.KnifeWorld.utilities.GetMap;

@RestController
@RequestMapping("/bills")
public class BillApi {
	private BillService billService;
	
	public BillApi(BillService billService) {
		this.billService = billService;
	}

	@GetMapping("")
	public ResponseEntity<?> getHistory(HttpServletRequest request,
			@RequestParam(name = "page",required = true,defaultValue = "0") Integer page
			){
		UserDetails u=(UserDetails)request.getUserPrincipal();
		System.out.println(u);
//		User u=(User) request.getUserPrincipal();
//		HashMap<String, Object> map=GetMap.getData("ok", billService.getHistory(u, page));
		return ResponseEntity.ok(1);
	}
	@PutMapping("")
	public ResponseEntity<?> remove(@RequestParam(name = "id") Integer id,
			HttpServletRequest request,
			@RequestParam(name = "page",required = true,defaultValue = "0") Integer page
			){
		Bill bill=billService.getById(id);
		if(bill.getStatus()!=1) {
			billService.changeStatus(id, 2);
			User u=(User) request.getSession().getAttribute("user");
			HashMap<String, Object> map=GetMap.getData("ok", billService.getHistory(u, page));
			return ResponseEntity.ok(map);
		}else {
			HashMap<String, Object> map=GetMap.getData("ok", "Không thể hủy đơn do đơn đã được chấp nhận");
			return ResponseEntity.ok(map);
		}
	}
	
}
