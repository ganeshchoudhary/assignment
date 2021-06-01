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
@Table(name = "VendorAddressDetails")
public class VendorAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vendorAddressId")
    public long vendorAddressId;

    @Column(name = "addressLine1")
    public String addressLine1;

    @Column(name = "addressLine2")
    public String addressLine2;

    @Column(name = "city")
    public String city;

    @Column(name = "state")
    public String state;

    @Column(name = "timestamp", nullable = false)
    private String timestamp;

    @CreatedDate
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;
}
