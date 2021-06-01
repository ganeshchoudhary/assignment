package com.learning.food.ordering.service.service;

import com.learning.food.ordering.service.dto.PageableResponseDto;
import com.learning.food.ordering.service.dto.VendorDto;
import com.learning.food.ordering.service.enumerate.SortBy;
import com.learning.food.ordering.service.enumerate.SortingOrder;

public interface VendorService {

    VendorDto addVendor(VendorDto vendorDto);

    PageableResponseDto<VendorDto> getVendors(SortBy sortBy, SortingOrder sortingOrder, int pageNo, int pageSize, String searchValue);

    VendorDto getVendor(long vendorId);

    VendorDto updateVendor(long vendorId, VendorDto vendorDto);

    VendorDto deleteVendor(long vendorId);


}
