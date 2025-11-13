package com.bezy.inventorysystem.mappers;

import com.bezy.inventorysystem.dtos.PurchaseOrderDto;
import com.bezy.inventorysystem.dtos.RegisterPurchaseOrderRequest;
import com.bezy.inventorysystem.dtos.UpdatePurchaseOrderRequest;
import com.bezy.inventorysystem.entities.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {
    PurchaseOrderDto toDto(PurchaseOrder purchaseOrder);
    PurchaseOrder toEntity(RegisterPurchaseOrderRequest request);
    void update(UpdatePurchaseOrderRequest request, @MappingTarget PurchaseOrder purchaseOrder);
}
