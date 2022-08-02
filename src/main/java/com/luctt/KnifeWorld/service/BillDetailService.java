package com.luctt.KnifeWorld.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luctt.KnifeWorld.entities.Bill;
import com.luctt.KnifeWorld.entities.BillDetail;
import com.luctt.KnifeWorld.repository.IBillDetailRepository;

@Service
public class BillDetailService {
	@Autowired
	private IBillDetailRepository repository;
	public BillDetail save(BillDetail b) {
		return repository.save(b);
	}
	public List<BillDetail> getByBil(Bill bill){
		return repository.findByBill(bill);
	}
	public List<BillDetail> saveIds(List<BillDetail> bills){
		return repository.saveAll(bills);
	}
}
