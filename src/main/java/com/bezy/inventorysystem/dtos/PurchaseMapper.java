package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    PurchaseDto toDto(Purchase purchase);
    Purchase toEntity(RegisterPurchaseRequest request);
    void update(UpdatePurchaseRequest request, @MappingTarget Purchase purchase);
}
