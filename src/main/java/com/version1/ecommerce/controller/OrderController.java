package com.version1.ecommerce.controller;

import com.version1.ecommerce.exceptions.AuthenticationFailException;
import com.version1.ecommerce.exceptions.ProductNotExistException;
import com.version1.ecommerce.model.Order;
import com.version1.ecommerce.model.User;
import com.version1.ecommerce.service.AuthenticationService;
import com.version1.ecommerce.service.OrderService;
import com.version1.ecommerce.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticationService authenticationService;


    // place order after checkout
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("token") String token)
            throws ProductNotExistException, AuthenticationFailException {
        // validate token
        authenticationService.authenticate(token);
        // retrieve user
        User user = authenticationService.getUser(token);
        // place the order
        orderService.placeOrder(user);
        return new ResponseEntity<>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }

    // get all orders
    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrdersByToken(@RequestParam("token") String token) throws AuthenticationFailException {
        // validate token
        authenticationService.authenticate(token);
        // retrieve user
        User user = authenticationService.getUser(token);
        // get orders
        List<Order> orderDtoList = orderService.listOrders(user.getId());

        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

}

