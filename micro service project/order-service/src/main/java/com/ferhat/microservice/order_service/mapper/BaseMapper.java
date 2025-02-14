package com.ferhat.microservice.order_service.mapper;

import jakarta.persistence.Entity;

import java.util.List;

public interface BaseMapper <E,D>{

    E mapToEntity(D Dto);
    D mapToDto(E Entity);

    List<E> mapToEntityList(List<D> dtoList);
    List<D> mapToDtoList(List<E> list);

}
