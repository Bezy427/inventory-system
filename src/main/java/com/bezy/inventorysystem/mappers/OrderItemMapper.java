package com.bezy.inventorysystem.mappers;

import com.bezy.inventorysystem.dtos.OrderItemDto;
import com.bezy.inventorysystem.dtos.RegisterOrderItemRequest;
import com.bezy.inventorysystem.dtos.UpdateOrderItemRequest;
import com.bezy.inventorysystem.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDto toDto(OrderItem orderItem);
    OrderItem toEntity(RegisterOrderItemRequest request);
    void update(UpdateOrderItemRequest request, @MappingTarget OrderItem orderItem);
}
