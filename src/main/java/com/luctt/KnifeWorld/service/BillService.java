package com.luctt.KnifeWorld.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.luctt.KnifeWorld.entities.Bill;
import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.repository.IBillRepository;
import com.luctt.KnifeWorld.utilities.AppConstraint;

@Service
public class BillService {
	private IBillRepository repository;
	
	public BillService(IBillRepository repository) {
		this.repository = repository;
	}

	public Page<Bill> getAll(Integer page){
		return this.repository.findAll(PageRequest.of(page, AppConstraint.numOfRecord,Sort.by("id").descending()));
	}
	
	public Page<Bill> getHistory(User u,Integer page){
		return this.repository.findByUser(u,PageRequest.of(page, AppConstraint.numOfRecord,Sort.by("id").descending()));
	}
	public Bill getById(Integer id){
		Optional<Bill> optional=repository.findById(id);
		return optional.isPresent()?optional.get():null;
	}
	public Bill changeStatus(Integer id,Integer status) {
		Bill b=this.getById(id);
		b.setStatus(status);
		return repository.save(b);
	}
	public Bill save(Bill bill) {
		return repository.save(bill);
	}
}
