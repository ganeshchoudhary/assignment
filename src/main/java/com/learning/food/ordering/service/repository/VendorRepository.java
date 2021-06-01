package com.learning.food.ordering.service.repository;

import com.learning.food.ordering.service.entity.VendorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Long> {

    @Query("select ve from VendorEntity ve where ve.deleted = false and lower(ve.vendorName) like concat('%',lower(:searchValue),'%') ")
    Page<VendorEntity> findAllBySearchValue(Pageable pageable, @Param("searchValue") String searchValue);
    @Query("select ve from VendorEntity ve where ve.deleted = false and ve.vendorId = :vendorId")
    VendorEntity getByVendorId(@Param("vendorId") long vendorId);
}

