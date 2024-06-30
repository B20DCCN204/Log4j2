package com.example.demoLog4j.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
public class CategoryEntity extends BaseEntity{
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;
}
