package com.example.demoLog4j.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    @NotNull(message = "You must choose a product")
    private Long productId;
    @NotNull(message = "Quantity is required")
    private Integer quantity;
}
