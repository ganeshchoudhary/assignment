package com.learning.food.ordering.service.converter;

import com.learning.food.ordering.service.dto.VendorDto;
import com.learning.food.ordering.service.entity.VendorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendorEntityDtoConverter implements DtoEntityConverter<VendorDto, VendorEntity> {

    private final ModelMapper modelMapper;

    @Autowired
    VendorEntityDtoConverter() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public VendorEntity getEntity(VendorDto dto) {
        return modelMapper.map(dto, VendorEntity.class);
    }

    @Override
    public VendorDto getDto(VendorEntity entity) {
        return modelMapper.map(entity, VendorDto.class);
    }
}
