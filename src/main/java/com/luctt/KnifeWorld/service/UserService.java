package com.luctt.KnifeWorld.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.luctt.KnifeWorld.entities.User;
import com.luctt.KnifeWorld.repository.IUserRepository;
import com.luctt.KnifeWorld.utilities.AppConstraint;

@Service
public class UserService {
	private IUserRepository repository;

	public UserService(IUserRepository repository) {
		this.repository = repository;
	}

	public List<User> getAll() {
		return repository.findAll();
	}
	public User getById(Integer id) {
		Optional<User> optional=repository.findById(id);
		return optional.isPresent()?optional.get():null;
	}
	public User getByEmail(String email) {
		return repository.findByEmail(email);
	}
	public Page<User> getByPage(Integer page){
		return repository.findAll(PageRequest.of(page, AppConstraint.numOfRecord,Sort.by("id").descending()));
	}
	public User save(User u) {
		return repository.save(u);
	}
	public User changeStatus(Integer id,Integer status,HttpServletRequest request) throws IllegalArgumentException,NullPointerException{
		User u=this.getById(id);
		if(u==null) {
			throw new NullPointerException();
		}else if(u==(User) request.getSession().getAttribute("user") && u.getStatus()==0) {
			throw new IllegalArgumentException();
		}else {
			u.setStatus(status);
			return this.save(u);
		}
	}
}
