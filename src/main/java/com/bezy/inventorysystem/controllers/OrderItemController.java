package com.bezy.inventorysystem.controllers;

import com.bezy.inventorysystem.dtos.OrderItemDto;
import com.bezy.inventorysystem.dtos.RegisterOrderItemRequest;
import com.bezy.inventorysystem.dtos.UpdateOrderItemRequest;
import com.bezy.inventorysystem.mappers.OrderItemMapper;
import com.bezy.inventorysystem.repositories.OrderItemRepository;
import com.bezy.inventorysystem.services.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderItemController {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper, OrderItemService orderItemService) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
        this.orderItemService = orderItemService;
    }

    @PostMapping("/order-items")
    public ResponseEntity<?> createOrderItem(
            @RequestBody RegisterOrderItemRequest request
    ){
        orderItemService.createOrderItem(request);
        return ResponseEntity.ok("Order item has been created successfully!");
    }

    @PutMapping("/order-items/{id}")
    public ResponseEntity<?> updateOrderItemById(
            @PathVariable Long id,
            @RequestBody UpdateOrderItemRequest request
    ){
        orderItemService.updateOrderItem(id, request);
        return ResponseEntity.ok("Order item has been updated successfully!");
    }

    @GetMapping("/order-items")
    public Iterable<OrderItemDto> getAllOrderItem(){
        return orderItemRepository.findAll()
                .stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @GetMapping("/order-items/{id}")
    public ResponseEntity<?> getOrderItemById(
            @PathVariable Long id
    ){
        var orderItem = orderItemRepository.findById(id).orElse(null);
        if(orderItem == null){
            return ResponseEntity.ok("Order item is not found!");
        }
        return ResponseEntity.ok(orderItemMapper.toDto(orderItem));
    }

    @DeleteMapping("/order-items/{id}")
    public ResponseEntity<?> deleteOrderItemById(
            @PathVariable Long id
    ){
        var orderItem = orderItemRepository.findById(id).orElse(null);
        if(orderItem == null){
            return ResponseEntity.notFound().build();
        }
        orderItemRepository.delete(orderItem);
        return ResponseEntity.ok("Order item has been deleted successfully!");
    }
}
