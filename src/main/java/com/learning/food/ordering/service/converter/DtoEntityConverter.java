package com.learning.food.ordering.service.converter;

public interface DtoEntityConverter<R, V> {

    V getEntity(R dto);

    R getDto(V entity);

}
