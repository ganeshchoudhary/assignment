package com.learning.food.ordering.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CustomerDetails")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerId")
    private long customerId;

    @Column(name = "emailId")
    private String emailId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "createdAt", nullable = false, updatable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
