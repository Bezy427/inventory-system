package com.bezy.inventorysystem.mappers;

import com.bezy.inventorysystem.dtos.RegisterSaleItemRequest;
import com.bezy.inventorysystem.dtos.SaleItemDto;
import com.bezy.inventorysystem.dtos.UpdateSaleItemRequest;
import com.bezy.inventorysystem.entities.SaleItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SaleItemMapper {
    SaleItemDto toDto(SaleItem saleItem);
    SaleItem toEntity(RegisterSaleItemRequest request);
    void update(UpdateSaleItemRequest request, @MappingTarget SaleItem saleItem);
}
