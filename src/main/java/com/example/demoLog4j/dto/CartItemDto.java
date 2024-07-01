package com.example.demoLog4j.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private Long id;
    @NotNull(message = "Quantity is required")
    private Integer quantity;
    private ProductDto productDto;
}
