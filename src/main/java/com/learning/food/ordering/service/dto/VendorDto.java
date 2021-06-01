package com.learning.food.ordering.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorDto {

    public long vendorId;

    @NotEmpty(message = "vendor name must not be empty.")
    public String vendorName;

    @NotEmpty(message = "description must not be empty.")
    public String description;

    public float rating;

    public VendorAddress vendorAddress;

}
