package com.ferhat.microservice.product_service.mapper;

import java.util.List;

public interface BaseMapper <E,D>{

    E mapToEntity(D Entity);
    D mapToDto(E Dto);

    List<E> mapToEntityList(List<D> entityList);
    List<D> mapToDtoList(List<E> dtoList);

}
