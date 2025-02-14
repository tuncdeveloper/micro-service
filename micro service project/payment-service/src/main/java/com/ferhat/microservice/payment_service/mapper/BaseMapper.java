package com.ferhat.microservice.payment_service.mapper;

import java.util.List;

public interface BaseMapper <E,D>{
    E mapToEntity(D Dto);
    D mapToDto(E Entity);

    List<E> mapToEntityList(List<D> dtoList);
    List<D> mapToDtoList(List<E> list);
}
