package com.example.demoLog4j.mapper;

import com.example.demoLog4j.dto.CartDto;
import com.example.demoLog4j.dto.CartItemDto;
import com.example.demoLog4j.entity.CartEntity;
import com.example.demoLog4j.entity.CartItemEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CartItemMapper cartItemMapper;
    private final Logger logger = LogManager.getLogger(CartMapper.class);

    public CartDto toCartDto(CartEntity cartEntity){
        if(cartEntity != null){
            CartDto cart = modelMapper.map(cartEntity, CartDto.class);
            Double total = 0.0;
            for(CartItemEntity cartItemEntity : cartEntity.getCartItems()){
                total += cartItemEntity.getQuantity()*cartItemEntity.getProduct().getPrice();
            }
            cart.setTotalPrice(total);
            List<CartItemDto> cartItemDtos = cartEntity.getCartItems().stream()
                    .map(cartItemEntity -> cartItemMapper.toCartItemDto(cartItemEntity))
                    .collect(Collectors.toList());
            cart.setCartItemDtosList(cartItemDtos);
            return cart;
        }
        logger.error("Parameter is null");
        return null;
    }

}
