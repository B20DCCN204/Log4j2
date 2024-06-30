package com.example.demoLog4j.service.impl;

import com.example.demoLog4j.dto.ProductDto;
import com.example.demoLog4j.entity.ProductEntity;
import com.example.demoLog4j.mapper.ProductMapper;
import com.example.demoLog4j.repository.ProductRepository;
import com.example.demoLog4j.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<ProductDto> getAllProducts() {
        logger.info("Fetching all products");
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream()
                .map(productEntity -> productMapper.toProductDto(productEntity))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        logger.info("Fetching product with id: {}", id);
        ProductEntity productEntity = productRepository.findById(id).orElseGet(
                () -> {
                    logger.error("Product not found with id: {}", id);
                    return null;
                }
        );
        return productMapper.toProductDto(productEntity);
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        ProductEntity productEntity = productMapper.toProductEntity(productDto);
        return productMapper.toProductDto(productRepository.save(productEntity));
    }

    @Override
    public List<ProductDto> searchProduct(String keyword) {
        logger.info("Searching products with keyword: {}", keyword);
        List<ProductEntity> productEntities = productRepository.findByNameOrCategoryName(keyword);
        return productEntities.stream()
                .map(productEntity -> productMapper.toProductDto(productEntity))
                .collect(Collectors.toList());
    }


}
