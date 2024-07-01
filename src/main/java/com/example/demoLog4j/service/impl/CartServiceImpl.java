package com.example.demoLog4j.service.impl;

import com.example.demoLog4j.dto.CartDto;
import com.example.demoLog4j.entity.CartEntity;
import com.example.demoLog4j.entity.CartItemEntity;
import com.example.demoLog4j.entity.ProductEntity;
import com.example.demoLog4j.entity.UserEntity;
import com.example.demoLog4j.mapper.CartMapper;
import com.example.demoLog4j.repository.CartItemRepository;
import com.example.demoLog4j.repository.CartRepository;
import com.example.demoLog4j.repository.ProductRepository;
import com.example.demoLog4j.repository.UserRepository;
import com.example.demoLog4j.service.CartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService {
    private final Logger logger = LogManager.getLogger(CartServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartDto getCartByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> {
                    logger.error("User not found with id: {}", userId);
                    return new RuntimeException("User not found");
                }
        );
        CartEntity cart = cartRepository.findByUserId(userId);
        if(cart == null){
            logger.info("Creating cart for user with userId: {}", userId);
            cart = new CartEntity();
            cart.setUser(user);
        }
        return cartMapper.toCartDto(cart);
    }

    @Override
    @Transactional
    public CartDto addToCart(Long userId, Long productId, Integer quantity) {
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> {
                    logger.error("User not found with id: {}", userId);
                    return new RuntimeException("User not found");
                }
        );
        ProductEntity product = productRepository.findById(productId).orElseThrow(
                () -> {
                    logger.error("Product not found with id: {}", productId);
                    return new RuntimeException("Product not found");
                }
        );
        CartEntity cart = cartRepository.findByUserId(userId);
        if(cart == null){
            logger.info("Creating cart for user with userId: {}", userId);
            cart = new CartEntity();
            cart.setUser(user);
        }

        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setCart(cart);
        cartItemEntity.setQuantity(quantity);
        cartItemEntity.setProduct(product);

        cart.getCartItems().add(cartItemEntity);

        return cartMapper.toCartDto(cartRepository.save(cart));
    }

    @Override
    public CartDto updateCartItemQuantity(Long userId, Long cartItemId, Integer quantity) {
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> {
                    logger.error("User not found with id: {}", userId);
                    return new RuntimeException("User not found");
                }
        );
        CartEntity cart = cartRepository.findByUserId(userId);
        if(cart == null){
            logger.error("Cart not found for user with userId: {}", userId);
            throw new RuntimeException("Cart not found");
        }

        CartItemEntity cartItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> {
                    logger.error("CartItem not found with id: {}", cartItemId);
                    return new RuntimeException("CartItem not found");
                });
        cartItem.setQuantity(quantity);
        return cartMapper.toCartDto(cartRepository.save(cart));
    }

    @Override
    public CartDto deleteCartItem(Long userId, Long cartItemId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> {
                    logger.error("User not found with id: {}", userId);
                    return new RuntimeException("User not found");
                }
        );
        CartEntity cart = cartRepository.findByUserId(userId);
        if(cart == null){
            logger.error("Cart not found for user with userId: {}", userId);
            throw new RuntimeException("Cart not found");
        }

        CartItemEntity cartItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> {
                    logger.error("CartItem not found with id: {}", cartItemId);
                    return new RuntimeException("CartItem not found");
                });
        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        return cartMapper.toCartDto(cartRepository.save(cart));
    }
}
