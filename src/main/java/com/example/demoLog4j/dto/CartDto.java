package com.example.demoLog4j.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDto {
    private Long id;
    private List<CartItemDto> cartItemDtosList;
    private Double totalPrice;
}
