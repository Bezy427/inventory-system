package com.bezy.inventorysystem.mappers;

import com.bezy.inventorysystem.dtos.RegisterStockMovementRequest;
import com.bezy.inventorysystem.dtos.StockMovementDto;
import com.bezy.inventorysystem.dtos.UpdateStockMovementRequest;
import com.bezy.inventorysystem.entities.StockMovement;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {
    StockMovementDto toDto(StockMovement stockMovement);
    StockMovement toEntity(RegisterStockMovementRequest request);
    void update(UpdateStockMovementRequest request, @MappingTarget StockMovement stockMovement);
}
