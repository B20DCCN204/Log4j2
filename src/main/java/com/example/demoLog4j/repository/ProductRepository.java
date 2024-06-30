package com.example.demoLog4j.repository;

import com.example.demoLog4j.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findProductEntitiesByCategoryId(Long id);
    @Query("SELECT p FROM ProductEntity p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.category.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<ProductEntity> findByNameOrCategoryName(@Param("keyword") String keyword);
}
