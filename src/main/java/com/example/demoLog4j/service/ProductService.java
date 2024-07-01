package com.example.demoLog4j.service;

import com.example.demoLog4j.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto saveProduct(ProductDto productDto);
    List<ProductDto> searchProduct(String keyword);
    List<ProductDto> getProductByCategoryId(Long id);
}
