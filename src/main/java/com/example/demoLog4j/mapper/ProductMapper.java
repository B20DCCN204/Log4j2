package com.example.demoLog4j.mapper;

import com.example.demoLog4j.dto.ProductDto;
import com.example.demoLog4j.entity.CategoryEntity;
import com.example.demoLog4j.entity.ProductEntity;
import com.example.demoLog4j.repository.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    private Logger logger = LogManager.getLogger(ProductMapper.class);
    public ProductDto toProductDto(ProductEntity productEntity){
        if(productEntity != null){
            ProductDto productDto = modelMapper.map(productEntity, ProductDto.class);
            productDto.setCategoryId(productEntity.getCategory().getId());
            return productDto;
        }
        logger.error("Parameter is null");
        return null;
    }

    public ProductEntity toProductEntity(ProductDto productDto){
        if(productDto != null){
            ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
            CategoryEntity category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                    () -> {
                        logger.error("Category not found with id: {}", productDto.getCategoryId());
                        return new RuntimeException("Category not found");
                    }
            );
            productEntity.setCategory(category);
            return productEntity;
        }
        logger.error("Parameter is null");
        return null;
    }
}
