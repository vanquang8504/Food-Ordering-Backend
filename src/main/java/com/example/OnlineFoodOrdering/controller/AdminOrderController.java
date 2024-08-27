package com.example.OnlineFoodOrdering.controller;

import com.example.OnlineFoodOrdering.Service.OrderService;
import com.example.OnlineFoodOrdering.Service.UserService;
import com.example.OnlineFoodOrdering.model.Order;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.repository.OrderRepository;
import com.example.OnlineFoodOrdering.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminOrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    @GetMapping("/order/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long restaurantId,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        System.out.println(order_status);
        List<Order> orders = orderService.getRestaurantOrder(restaurantId, order_status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @PathVariable String orderStatus,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.updateOrder(orderId,orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
