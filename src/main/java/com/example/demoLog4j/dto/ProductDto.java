package com.example.demoLog4j.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    @NotNull(message = "You must choose category for this product")
    private Long categoryId;
    @NotEmpty(message = "Product name is required")
    private String name;
    @NotNull(message = "Price is required")
    private Double price;
    @NotNull(message = "Quantity is required")
    private Integer quantity;
    private String description;
}
