package com.example.OnlineFoodOrdering.controller;

import com.example.OnlineFoodOrdering.Service.CartService;
import com.example.OnlineFoodOrdering.Service.UserService;
import com.example.OnlineFoodOrdering.model.Cart;
import com.example.OnlineFoodOrdering.model.CartItem;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.request.AddCartItemRequest;
import com.example.OnlineFoodOrdering.request.UpdateCartItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    @PostMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestBody AddCartItemRequest request,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {
        CartItem cartItem = cartService.addItemToCart(request, jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(
            @RequestBody UpdateCartItemRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        CartItem cartItem = cartService.updateCartItemQuantity(request.getCartItemId(), request.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/remove/{id}")
    public ResponseEntity<Cart> removeCartItem(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Cart cart = cartService.removeItemFromCart(id, jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/cart-item/clear")
    public ResponseEntity<Cart> clearCart(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

}
