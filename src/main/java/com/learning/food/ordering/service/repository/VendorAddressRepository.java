package com.learning.food.ordering.service.repository;

import com.learning.food.ordering.service.entity.VendorAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorAddressRepository extends JpaRepository<VendorAddressEntity, Long> {
}
