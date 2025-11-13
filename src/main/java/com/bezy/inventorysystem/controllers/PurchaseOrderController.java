package com.bezy.inventorysystem.controllers;

import com.bezy.inventorysystem.dtos.PurchaseOrderDto;
import com.bezy.inventorysystem.dtos.RegisterPurchaseOrderRequest;
import com.bezy.inventorysystem.dtos.UpdatePurchaseOrderRequest;
import com.bezy.inventorysystem.entities.PurchaseOrder;
import com.bezy.inventorysystem.mappers.PurchaseOrderMapper;
import com.bezy.inventorysystem.repositories.PurchaseOrderRepository;
import com.bezy.inventorysystem.services.PurchaseOrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PurchaseOrderController {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;
    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderMapper purchaseOrderMapper, PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderMapper = purchaseOrderMapper;
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping("/purchase-order")
    public ResponseEntity<?> createPurchaseOrder(
            @Valid @RequestBody RegisterPurchaseOrderRequest request
    ){
        purchaseOrderService.createPurchaseOrder(request);
        return ResponseEntity.ok("Purchase order has been created successfully!");
    }

    @PutMapping("/purchase-order/{id}")
    public ResponseEntity<?> updatePurchaseOrderById(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePurchaseOrderRequest request
    ){
        purchaseOrderService.updatePurchaseOrder(id, request);
        return ResponseEntity.ok("Purchase order has been updated successfully!");
    }

    @GetMapping("/purchase-order")
    public Iterable<PurchaseOrderDto> getAllPurchaseOrders(){
        return purchaseOrderRepository.findAll()
                .stream()
                .map(purchaseOrderMapper::toDto)
                .toList();
    }

    @GetMapping("/purchase-order/{id}")
    public ResponseEntity<?> getPurchaseOrderById(
            @PathVariable Long id
    ){
        var purchaseOrder = purchaseOrderRepository.findById(id).orElse(null);
        if (purchaseOrder == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(purchaseOrderMapper.toDto(purchaseOrder));
    }

    @DeleteMapping("/purchase-order/{id}")
    public ResponseEntity<?> deletePurchaseOrderById(
            @PathVariable Long id
    ){
        var purchaseOrder = purchaseOrderRepository.findById(id).orElse(null);
        if (purchaseOrder == null) {
            return ResponseEntity.notFound().build();
        }
        purchaseOrderRepository.delete(purchaseOrder);
        return ResponseEntity.ok("Purchase order has been deleted successfully!");
    }
}
