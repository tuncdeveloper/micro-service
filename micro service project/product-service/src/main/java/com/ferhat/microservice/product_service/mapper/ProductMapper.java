package com.ferhat.microservice.product_service.mapper;

import com.ferhat.microservice.product_service.dto.ProductDto;
import com.ferhat.microservice.product_service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface ProductMapper extends BaseMapper<Product, ProductDto>{

    Product mapToEntity(ProductDto productDto);
    ProductDto mapToDto(Product product);

    List<Product> mapToEntity(List<Product> products);
    List<ProductDto> mapToDto(List<ProductDto> productDtos);



}
