package com.ferhat.microservice.product_service.service;


import com.ferhat.microservice.product_service.dto.ProductDto;
import com.ferhat.microservice.product_service.exception.ProductNotFoundException;
import com.ferhat.microservice.product_service.mapper.ProductMapper;
import com.ferhat.microservice.product_service.model.Product;
import com.ferhat.microservice.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.mapToDtoList(products);
    }

    public ProductDto getProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return productMapper.mapToDto(product);
    }

    @Transactional
    public ProductDto createProduct(ProductDto productDTO) {
        Product product = productMapper.mapToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.mapToDto(savedProduct);
    }

    @Transactional
    public ProductDto updateProduct(UUID id, ProductDto productDTO) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setStockQuantity(productDTO.getStockQuantity());

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.mapToDto(updatedProduct);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        return productMapper.mapToDtoList(products);
    }


    @Transactional
    public void deleteProduct(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }
}

