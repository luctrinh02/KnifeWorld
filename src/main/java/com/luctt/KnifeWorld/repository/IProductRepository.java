package com.luctt.KnifeWorld.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luctt.KnifeWorld.entities.Product;
@Repository
public interface IProductRepository extends JpaRepository<Product, Integer>{
	@Query("SELECT p FROM Product p WHERE p.status=0 and p.amount>0")
	Page<Product> findActive(Pageable page);
	@Query("SELECT p FROM Product p WHERE p.status=0 and p.amount>0")
	Page<Product> search(String keyWord,Pageable page);
	@Query("SELECT p FROM Product p WHERE p.status=0")
	Page<Product> adminSearch(String keyWord,Pageable page);
}
