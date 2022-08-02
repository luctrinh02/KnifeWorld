package com.luctt.KnifeWorld.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.repository.IProductRepository;
import com.luctt.KnifeWorld.utilities.AppConstraint;
@Service
public class ProductService {
	private IProductRepository repository;

	public ProductService(IProductRepository repository) {
		this.repository = repository;
	}
	
	public Page<Product> getActiveProduct(Integer pageNumber){
		return repository.findActive(PageRequest.of(pageNumber, AppConstraint.numOfRecord,Sort.by("id").descending()));
	}
	public Page<Product> getAll(Integer pageNumer){
		return repository.findAll(PageRequest.of(pageNumer, AppConstraint.numOfRecord,Sort.by("id").descending()));
	}
	public Product getById(Integer id) {
		Optional<Product> optional=repository.findById(id);
		return optional.isPresent()?optional.get():null;
	}
	public Product save(Product p) {
		return repository.save(p);
	}
	public Product changeStatus(Integer id,Integer status,HttpServletRequest request) throws Exception{
		Product p=this.getById(id);
		if(p.getUser().equals((User)request.getSession().getAttribute("user"))) {
			p.setStatus(status);
			return this.save(p);
		}else {
			throw new Exception();
		}
	}
	public Page<Product> search(String key,Integer pageNumber) {
		return repository.search(key,PageRequest.of(pageNumber, AppConstraint.numOfRecord,Sort.by("id").descending()));
	}
	public Page<Product> adminSearch(String key,Integer pageNumber) {
		return repository.adminSearch(key,PageRequest.of(pageNumber, AppConstraint.numOfRecord,Sort.by("id").descending()));
	}
	public List<Product> getByIds(List<Integer> ids){
		return repository.findAllById(ids);
	}
}
