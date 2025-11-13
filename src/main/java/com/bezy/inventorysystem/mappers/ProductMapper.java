package com.bezy.inventorysystem.mappers;

import com.bezy.inventorysystem.dtos.ProductDto;
import com.bezy.inventorysystem.dtos.RegisterProductRequest;
import com.bezy.inventorysystem.dtos.UpdateProductRequest;
import com.bezy.inventorysystem.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);
    Product toEntity(RegisterProductRequest request);
    void update(UpdateProductRequest request, @MappingTarget Product product);
}
