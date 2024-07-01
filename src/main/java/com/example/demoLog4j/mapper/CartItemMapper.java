package com.example.demoLog4j.mapper;

import com.example.demoLog4j.dto.CartItemDto;
import com.example.demoLog4j.entity.CartItemEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductMapper productMapper;
    private final Logger logger = LogManager.getLogger(CartItemMapper.class);
    CartItemDto toCartItemDto(CartItemEntity cartItemEntity){
        if(cartItemEntity != null){
            CartItemDto cartItemDto = modelMapper.map(cartItemEntity, CartItemDto.class);
            cartItemDto.setProductDto(productMapper.toProductDto(cartItemEntity.getProduct()));
            return cartItemDto;
        }
        logger.error("Parameter is null");
        return null;
    }
}
