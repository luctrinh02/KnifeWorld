package com.luctt.KnifeWorld.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luctt.KnifeWorld.entities.Cart;
import com.luctt.KnifeWorld.entities.CartPK;
import com.luctt.KnifeWorld.entities.User;

@Repository
public interface ICartRepository extends JpaRepository<Cart,CartPK >{
	List<Cart> findByUser(User user);
}
