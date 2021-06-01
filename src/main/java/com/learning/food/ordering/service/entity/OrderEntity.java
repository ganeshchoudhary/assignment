package com.learning.food.ordering.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "OrderDetails")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId")
    public long orderId;

    @Column(name = "feedback")
    public String feedback;

    @Column(name = "rating")
    public int rating;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    public CustomerEntity customerEntity;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "vendorId", referencedColumnName = "vendorId")
    public VendorEntity vendorEntity;

    @OneToMany(fetch = FetchType.LAZY)
    public List<ItemEntity> itemEntityList;

    @Column(name = "timestamp", nullable = false)
    private String timestamp;

    @CreatedDate
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;
}
