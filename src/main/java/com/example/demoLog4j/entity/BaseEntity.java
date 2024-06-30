package com.example.demoLog4j.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BaseEntity implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by")
    @CreatedBy
    @JsonIgnore
    private String createdBy;

    @Column(name = "created_at")
    @CreatedDate
    @JsonIgnore
    private Date createdAt;

    @Column(name = "updated_by")
    @LastModifiedBy
    @JsonIgnore
    private String updatedBy;

    @Column(name = "updated_at")
    @LastModifiedDate
    @JsonIgnore
    private Date updatedAt;
}
