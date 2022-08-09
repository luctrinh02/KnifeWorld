package com.luctt.KnifeWorld.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.luctt.KnifeWorld.entities.Cart;
import com.luctt.KnifeWorld.entities.CartPK;
import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.repository.ICartRepository;

@Service
public class CartService {
	private ICartRepository repository;
	
	public CartService(ICartRepository repository) {
		this.repository = repository;
	}
	public List<Cart> getAll(User u){
		return repository.findByUser(u);
	}
	public Cart save(Cart cart){
		return repository.save(cart);
	}
	public void remove(CartPK id) {
		repository.deleteById(id);
	}
	public void removes(List<CartPK> ids) {
		repository.deleteAllByIdInBatch(ids);
	}
	public Optional<Cart> getById(CartPK id) {
		return repository.findById(id);
	}
	public List<Cart> getAllByIds(List<CartPK> ids){
		return repository.findAllById(ids);
	}
}
