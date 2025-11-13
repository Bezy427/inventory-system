package com.bezy.inventorysystem.mappers;

import com.bezy.inventorysystem.dtos.RegisterSaleRequest;
import com.bezy.inventorysystem.dtos.SaleDto;
import com.bezy.inventorysystem.dtos.UpdateSaleRequest;
import com.bezy.inventorysystem.entities.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    SaleDto toDto(Sale sale);
    Sale toEntity(RegisterSaleRequest request);
    void update(UpdateSaleRequest request, @MappingTarget Sale sale);
}
