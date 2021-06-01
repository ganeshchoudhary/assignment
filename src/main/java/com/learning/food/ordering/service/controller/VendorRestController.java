package com.learning.food.ordering.service.controller;


import com.learning.food.ordering.service.constant.ApiConstant;
import com.learning.food.ordering.service.dto.PageableResponseDto;
import com.learning.food.ordering.service.dto.ResponseDto;
import com.learning.food.ordering.service.dto.VendorDto;
import com.learning.food.ordering.service.enumerate.SortBy;
import com.learning.food.ordering.service.enumerate.SortingOrder;
import com.learning.food.ordering.service.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Auther Ganesh singh
 * @Description this rest controller is to add, get, update and delete vendor details
 */
@RestController
@RequestMapping(path = ApiConstant.VENDOR_API_ROOT)
public class VendorRestController {

    private final VendorService vendorService;

    @Autowired
    private VendorRestController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping(path = ApiConstant.VENDOR_API, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addVendorDetails(@RequestBody @Valid VendorDto vendorDto) {
        ResponseDto<VendorDto> responseDto = new ResponseDto<>("Successfully added.", 200, vendorService.addVendor(vendorDto));

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(path = ApiConstant.VENDOR_API, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getVendors(@RequestParam(value = "sortBy", defaultValue = "VENDOR_NAME") SortBy sortBy,
                                     @RequestParam(name = "sortingOrder", required = false, defaultValue = "DESC") SortingOrder sortingOrder,
                                     @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
                                     @RequestParam(name = "pageSize", required = false, defaultValue = "100") int pageSize,
                                     @RequestParam(name = "searchValue", required = false) String searchValue) {


        ResponseDto<PageableResponseDto<VendorDto>> responseDto = new ResponseDto<>("Vendor details .", 200, vendorService.getVendors(sortBy, sortingOrder, pageNo, pageSize, searchValue));

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @GetMapping(path = ApiConstant.VENDOR_BY_ID_API, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getVendor(@PathVariable("vendorId") long vendorId) {
        ResponseDto<VendorDto> responseDto = new ResponseDto<>("Vendor details.", 200, vendorService.getVendor(vendorId));
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PutMapping(path = ApiConstant.VENDOR_BY_ID_API, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateVendor(@PathVariable("vendorId") long vendorId, @RequestBody @Valid VendorDto vendorDto) {
        ResponseDto<VendorDto> responseDto = new ResponseDto<>("Successfully updated.", 200, vendorService.updateVendor(vendorId, vendorDto));

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }


    @DeleteMapping(path = ApiConstant.VENDOR_BY_ID_API, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeVendor(@PathVariable("vendorId") long vendorId) {
        ResponseDto<VendorDto> responseDto = new ResponseDto<>("Successfully removed.", 200, vendorService.deleteVendor(vendorId));

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
}
