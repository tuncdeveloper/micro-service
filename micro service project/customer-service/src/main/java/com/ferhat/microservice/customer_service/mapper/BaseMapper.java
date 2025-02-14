package com.ferhat.microservice.customer_service.mapper;

import java.util.List;

public interface BaseMapper <E,D>{
    D mapToDto(E entity);
    E mapToEntity(D dto);
    List<D> mapToDtoList(List<E> entityList);
    List<E> mapToEntityList(List<D> dtoList);

}
