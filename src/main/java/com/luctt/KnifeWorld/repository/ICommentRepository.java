package com.luctt.KnifeWorld.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luctt.KnifeWorld.entities.Comment;
import com.luctt.KnifeWorld.entities.CommentPK;
import com.luctt.KnifeWorld.entities.Product;
@Repository
public interface ICommentRepository extends JpaRepository<Comment, CommentPK>{
	List<Comment> findByProduct(Product product);
}
