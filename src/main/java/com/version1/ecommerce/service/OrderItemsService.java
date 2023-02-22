package com.version1.ecommerce.service;

import com.version1.ecommerce.model.OrderItems;
import com.version1.ecommerce.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderItemsService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    public void addOrderedProducts(OrderItems orderItem) {
        orderItemsRepository.save(orderItem);
    }


}
