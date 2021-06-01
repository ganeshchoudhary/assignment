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
@Table(name = "VendorDetails")
public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vendorId")
    public long vendorId;

    @Column(name = "vendorName", unique = true)
    public String vendorName;

    @Column(name = "description")
    public String description;

    @Column(name = "contactNumber")
    public String contactNumber;


    @Column(name = "rating")
    public float rating;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendorAddressId", referencedColumnName = "vendorAddressId")
    public VendorAddressEntity vendorAddressEntity;

    @OneToMany(fetch = FetchType.LAZY)
    public List<ItemEntity> itemEntityList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Vendor_Order_detail",
            joinColumns = {@JoinColumn(name = "vendorId")},
            inverseJoinColumns = {@JoinColumn(name = "orderId")}
    )
    public List<OrderEntity> orderEntityList;


    @Column(name = "timestamp", nullable = false)
    private String timestamp;

    @CreatedDate
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;


}
