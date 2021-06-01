package com.learning.food.ordering.service.service;

import com.learning.food.ordering.service.constant.CommonConstants;
import com.learning.food.ordering.service.converter.DtoEntityConverter;
import com.learning.food.ordering.service.dto.PageableResponseDto;
import com.learning.food.ordering.service.dto.VendorDto;
import com.learning.food.ordering.service.entity.VendorEntity;
import com.learning.food.ordering.service.enumerate.SortBy;
import com.learning.food.ordering.service.enumerate.SortingOrder;
import com.learning.food.ordering.service.repository.VendorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final Logger logger;
    private final DtoEntityConverter<VendorDto, VendorEntity> vendorConverter;
    private final VendorRepository vendorRepository;

    @Autowired
    VendorServiceImpl(DtoEntityConverter<VendorDto, VendorEntity> vendorConverter, VendorRepository vendorRepository) {
        this.logger = LoggerFactory.getLogger(VendorServiceImpl.class);
        this.vendorConverter = vendorConverter;
        this.vendorRepository = vendorRepository;

    }


    @Override
    public VendorDto addVendor(VendorDto vendorDto) {
        logger.info("Adding new vendor details.");
        VendorEntity vendorEntity = vendorConverter.getEntity(vendorDto);
        vendorEntity.setCreatedAt(LocalDateTime.now());
        vendorEntity.setTimestamp(LocalDateTime.MAX.format(CommonConstants.DATE_TIME_FORMATTER));
        try {
            vendorEntity = vendorRepository.save(vendorEntity);
        } catch (
                DataIntegrityViolationException e) {
            logger.error(e.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "{'message':'vendorName must be unique.'}");
        }
        return vendorConverter.getDto(vendorEntity);
    }

    @Override
    public PageableResponseDto<VendorDto> getVendors(SortBy sortBy, SortingOrder sortingOrder, int pageNo, int pageSize, String searchValue) {
        logger.info("Getting all the vendors detail.");
        Sort sort = Sort.by(sortBy.getValue()).ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<VendorEntity> page;
        if (searchValue == null || searchValue.equals("")) {
            page = vendorRepository.findAll(pageable);
        } else {
            page = vendorRepository.findAllBySearchValue(pageable, searchValue);
        }
        List<VendorEntity> vendorEntityList = page.getContent() != null ? page.getContent() : new ArrayList<>();
        List<VendorDto> vendorDtoList = new ArrayList<>();
        for (VendorEntity vendor : vendorEntityList
        ) {
            vendorDtoList.add(vendorConverter.getDto(vendor));
        }

        return new PageableResponseDto<>(pageNo, page.getTotalPages(), pageSize, vendorDtoList);
    }

    @Override
    public VendorDto getVendor(long vendorId) {
        logger.info("Getting vendor details : " + vendorId);

        VendorEntity vendorEntity = vendorRepository.getByVendorId(vendorId);
        if (vendorEntity == null) {
            logger.error("Invalid vendorId : " + vendorId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "{'Message': 'Invalid vendorId.'}");
        }
        return vendorConverter.getDto(vendorEntity);
    }

    @Override
    public VendorDto updateVendor(long vendorId, VendorDto vendorDto) {
        logger.info("Updating vendor details : " + vendorId);

        VendorEntity vendorEntity = vendorRepository.getByVendorId(vendorId);
        if (vendorEntity == null) {
            logger.error("Invalid vendorId : " + vendorId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "{'Message': 'Invalid vendorId.'}");
        }
        vendorEntity.setVendorName(vendorDto.vendorName);
        vendorEntity.setDescription(vendorDto.description);
        try {
            vendorEntity = vendorRepository.save(vendorEntity);

        } catch (DataIntegrityViolationException e) {
            logger.error(e.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "vendorName must be unique.");
        }
        return vendorConverter.getDto(vendorEntity);
    }

    @Override
    public VendorDto deleteVendor(long vendorId) {
        logger.info("Deleting vendor details : " + vendorId);

        VendorEntity vendorEntity = vendorRepository.getByVendorId(vendorId);
        if (vendorEntity == null) {
            logger.error("Invalid vendorId : " + vendorId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "{'Message': 'Invalid vendorId.'}");
        }
        vendorEntity.setDeleted(true);
        vendorEntity = vendorRepository.save(vendorEntity);
        return vendorConverter.getDto(vendorEntity);

    }
}
