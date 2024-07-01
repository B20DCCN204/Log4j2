package com.example.demoLog4j.service;

import com.example.demoLog4j.dto.CartDto;

public interface CartService {
    CartDto getCartByUserId(Long id);
    CartDto addToCart(Long userId, Long productId, Integer quantity);
    CartDto updateCartItemQuantity(Long userId, Long cartItemId, Integer quantity);
    CartDto deleteCartItem(Long userId, Long cartItemId);
}
