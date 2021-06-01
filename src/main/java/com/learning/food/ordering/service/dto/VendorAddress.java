package com.learning.food.ordering.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorAddress {

    public long addressId;

    public String addressLine1;

    public String addressLine2;

    public String city;

    public String pincode;

    public String country;


}
