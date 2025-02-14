package com.ferhat.microservice.order_item_service.mapper;

import java.util.List;

public interface BaseMapper <E,D>{

    E  mapToEntity(D Dto);
    D mapToDto(E Entity);
    List<E> mapToEntityList(List<D> Dto);
    List<D> mapToDtoList(List<E> Entity);}
