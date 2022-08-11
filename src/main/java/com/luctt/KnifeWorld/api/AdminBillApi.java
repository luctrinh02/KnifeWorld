package com.luctt.KnifeWorld.api;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luctt.KnifeWorld.entities.Bill;
import com.luctt.KnifeWorld.entities.BillDetail;
import com.luctt.KnifeWorld.service.BillDetailService;
import com.luctt.KnifeWorld.service.BillService;
import com.luctt.KnifeWorld.utilities.GetMap;

@Controller
@RequestMapping("/api/admin/bills")
public class AdminBillApi {
	@Autowired
	private BillService billService;
	@Autowired
	private BillDetailService billDetailService;
	@GetMapping("")
	public String getBill(Model model,@RequestParam("page") Integer num) {
		 Page<Bill> page=billService.getAll(num);
		 model.addAttribute("list", page);
		 return "admin/bill/indexView";
	 }
	@PatchMapping("/{id}")
	public ResponseEntity<?> changeStatus(@PathVariable("id") Integer id,@RequestParam("status") Integer status){
		Bill bill=billService.getById(id);
		List<BillDetail> details=billDetailService.getByBil(bill);
		HashMap<String, Object> map;
		for(BillDetail x:details) {
			if(x.getAmount()>x.getProduct().getAmount()) {
				map=GetMap.getData("error", "Sản phẩm không đủ số lượng!");
				return ResponseEntity.ok(map);
			}
		}
		bill.setStatus(status);
		billService.save(bill);
		if(status==1) {
			map=GetMap.getData("ok", "Đơn đã được chấp nhận");
		}else {
			map=GetMap.getData("ok", "Đơn đã được hủy");
		}
		return ResponseEntity.ok(map);
	}
	@GetMapping("/{id}")
	public String getDetail(Model model,@PathVariable("id") Integer id) {
		Bill bill=billService.getById(id);
		List<BillDetail> billDetails=billDetailService.getByBil(bill);
		model.addAttribute("obj", bill);
		model.addAttribute("billDetails", billDetails);
		return "user/detail";
	}
}
