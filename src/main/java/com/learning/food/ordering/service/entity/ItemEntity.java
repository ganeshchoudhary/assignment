package com.learning.food.ordering.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ItemDetails")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "itemId")
    public long itemId;

    @Column(name = "itemName")
    public String itemName;

    @Column(name = "description")
    public String description;

    @Column(name = "timestamp", nullable = false)
    private String timestamp;

    @CreatedDate
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;


}
