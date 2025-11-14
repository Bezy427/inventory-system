package com.bezy.inventorysystem.services;

import com.bezy.inventorysystem.dtos.RegisterOrderItemRequest;
import com.bezy.inventorysystem.dtos.UpdateOrderItemRequest;
import com.bezy.inventorysystem.entities.OrderItem;
import com.bezy.inventorysystem.mappers.OrderItemMapper;
import com.bezy.inventorysystem.repositories.OrderItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class OrderItemService {
    private OrderItemRepository orderItemRepository;
    private OrderItemMapper orderItemMapper;

    public ResponseEntity<?> createOrderItem(
            @RequestBody RegisterOrderItemRequest request
    ){
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(request.getProduct());
        orderItem.setQuantity(request.getQuantity());
        orderItem.setPrice(request.getPrice());
        orderItem.setId(request.getId());
        orderItem.setPurchaseOrder(request.getPurchaseOrder());
        orderItemRepository.save(orderItemMapper.toEntity(request));
        return  ResponseEntity.ok("Order item has been created successfully!");
    }

    public ResponseEntity<?> updateOrderItem(
            @PathVariable Long id,
            @RequestBody UpdateOrderItemRequest request
    ){
        var orderItem = orderItemRepository.findById(id).orElse(null);
        if(orderItem == null){
            return ResponseEntity.notFound().build();
        }
        orderItemMapper.update(request, orderItem);
        orderItemRepository.save(orderItem);
        return ResponseEntity.ok("Order item has been updated successfully!");
    }
}
