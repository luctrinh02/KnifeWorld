package com.luctt.KnifeWorld.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luctt.KnifeWorld.entities.User;
@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT u FROM User u WHERE u.email=?1 and u.status=0")
	User findByEmail(String email);
	@Query("select u from User u where u.fullname like ?1")
	Page<User> findByFullnameLike(String name,Pageable pageable);
}
