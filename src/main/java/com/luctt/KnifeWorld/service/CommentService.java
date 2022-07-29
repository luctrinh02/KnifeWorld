package com.luctt.KnifeWorld.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luctt.KnifeWorld.entities.Comment;
import com.luctt.KnifeWorld.entities.Product;
import com.luctt.KnifeWorld.repository.ICommentRepository;
@Service
public class CommentService {
	private ICommentRepository repository;

	public CommentService(ICommentRepository repository) {
		this.repository = repository;
	}
	public Comment save(Comment c) {
		return this.repository.save(c);
	}
	public List<Comment> getByProduct(Product p){
		return this.repository.findByProduct(p);
	}
}
