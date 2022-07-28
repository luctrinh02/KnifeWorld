package com.luctt.KnifeWorld.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luctt.KnifeWorld.entities.User;
@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT u FROM User u WHERE u.email=?1 and u.status=0")
	User findByEmail(String email);
}
