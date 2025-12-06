package com.code.free.entities.course;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "course", schema = "public")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name="price",nullable = false)
    private Long price;

    @Column(name="created_by",nullable = false)
    private Long createdBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_active",nullable = false)
    @Builder.Default
    private Boolean isActive=true;

    @Column(name = "discount")
    private Integer discount;
}
