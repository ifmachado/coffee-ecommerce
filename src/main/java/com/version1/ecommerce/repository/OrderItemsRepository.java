package com.version1.ecommerce.repository;

import com.version1.ecommerce.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Integer> {
}
