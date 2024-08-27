package com.example.OnlineFoodOrdering.controller;

import com.example.OnlineFoodOrdering.Service.OrderService;
import com.example.OnlineFoodOrdering.Service.PaymentService;
import com.example.OnlineFoodOrdering.Service.UserService;
import com.example.OnlineFoodOrdering.model.CartItem;
import com.example.OnlineFoodOrdering.model.Order;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.request.OrderRequest;
import com.example.OnlineFoodOrdering.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final PaymentService paymentService;

    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createOrder(
            @RequestBody OrderRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(request,user);
        PaymentResponse response = paymentService.createPaymentLink(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getUserOrder(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
