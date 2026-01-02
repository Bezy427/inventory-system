package com.bezy.inventorysystem.mappers;

import com.bezy.inventorysystem.dtos.RegisterSupplierRequest;
import com.bezy.inventorysystem.dtos.SupplierDto;
import com.bezy.inventorysystem.entities.Supplier;
import com.bezy.inventorysystem.repositories.UpdateSupplierRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierDto toDto(Supplier supplier);
    Supplier toEntity(RegisterSupplierRequest request);
    void update(UpdateSupplierRequest request, @MappingTarget Supplier supplier);
}
