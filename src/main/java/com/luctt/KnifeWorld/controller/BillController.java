package com.luctt.KnifeWorld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.luctt.KnifeWorld.entities.Bill;
import com.luctt.KnifeWorld.entities.BillDetail;
import com.luctt.KnifeWorld.service.BillDetailService;
import com.luctt.KnifeWorld.service.BillService;

@Controller
public class BillController {
	@Autowired
	private BillService service;
	@Autowired
	private BillDetailService billDetailService;
	@GetMapping("/bills")
	public String getBill(Model model) {
		Page<Bill> page=service.getAll(0);
		model.addAttribute("list", page);
		return "user/history";
	}
	@GetMapping("/api/bills")
	public String getBills(Model model,@RequestParam("page") Integer num) {
		Page<Bill> page=service.getAll(num);
		model.addAttribute("list", page);
		return "user/historyView";
	}
	@GetMapping("/api/bills/{id}")
	public String getDetail(Model model,@PathVariable("id") Integer id) {
		Bill bill=service.getById(id);
		List<BillDetail> billDetails=billDetailService.getByBil(bill);
		model.addAttribute("obj", bill);
		model.addAttribute("billDetails", billDetails);
		return "user/detail";
	}
	@GetMapping("/admin/bills")
	public String getall(Model model) {
		Page<Bill> bills=service.getAll(0);
		model.addAttribute("list", bills);
		return "admin/bill/index";
	}
}
