package com.version1.ecommerce.service;

import com.version1.ecommerce.exceptions.OrderNotFoundException;
import com.version1.ecommerce.dto.cart.CartDto;
import com.version1.ecommerce.dto.cart.CartItemDto;
import com.version1.ecommerce.dto.order.PlaceOrderDto;
import com.version1.ecommerce.model.Order;
import com.version1.ecommerce.model.OrderItems;
import com.version1.ecommerce.model.User;
import com.version1.ecommerce.repository.OrderItemsRepository;
import com.version1.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    OrderItemsService orderItemsService;

    public void placeOrder(User user) {
        // first let get cart items for the user
        CartDto cartDto = cartService.listCartItems(user);

        List<CartItemDto> cartItemDtoList = cartDto.getcartItems();

        // create the order and save it
        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date());
        newOrder.setUserId(user.getId());
        newOrder.setTotalPrice(cartDto.getTotalCost());
        orderRepository.save(newOrder);

        for (CartItemDto cartItemDto : cartItemDtoList) {
            // create orderItem and save each one
            OrderItems orderItem = new OrderItems();
            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(cartItemDto.getProduct().getPrice());
            orderItem.setProduct(cartItemDto.getProduct());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setOrder(newOrder);
            // add to order item list
            orderItemsRepository.save(orderItem);
        }
        //
        cartService.deleteUserCartItems(user);
    }


    private Order getOrderFromDto(PlaceOrderDto orderDto, int userId) {
        Order order = new Order(orderDto,userId);
        return order;
    }

    public List<Order> listOrders(int user_id) {
        List<Order> orderList = orderRepository.findAllByUserIdOrderByCreatedDateDesc(user_id);
        return orderList;
    }

    public Order getOrder(Integer orderId) throws OrderNotFoundException{
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()){
            return order.get();
        }
        throw new OrderNotFoundException("Order not found");
    }


}
