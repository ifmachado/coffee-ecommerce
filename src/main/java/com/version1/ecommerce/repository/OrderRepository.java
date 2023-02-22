package com.version1.ecommerce.repository;

import com.version1.ecommerce.model.Cart;
import com.version1.ecommerce.model.Order;
import com.version1.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer> {
    List<Order> findAllByUserIdOrderByCreatedDateDesc(Integer userId);

}

