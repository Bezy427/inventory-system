package com.bezy.inventorysystem.services;

import com.bezy.inventorysystem.dtos.RegisterPurchaseOrderRequest;
import com.bezy.inventorysystem.dtos.UpdatePurchaseOrderRequest;
import com.bezy.inventorysystem.entities.PurchaseOrder;
import com.bezy.inventorysystem.mappers.PurchaseOrderMapper;
import com.bezy.inventorysystem.repositories.PurchaseOrderRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PurchaseOrderService {
    private PurchaseOrderRepository purchaseOrderRepository;
    private PurchaseOrderMapper purchaseOrderMapper;

    public ResponseEntity<?> createPurchaseOrder(
            @Valid @RequestBody RegisterPurchaseOrderRequest request
    ){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCostPerUnit(request.getCostPerUnit());
        purchaseOrder.setProduct(request.getProduct());
        purchaseOrder.setQuantity(request.getQuantity());
        purchaseOrder.setStatus(request.getStatus());
        purchaseOrder.setId(request.getId());
        purchaseOrder.setTotalCost(request.getTotalCost());
        purchaseOrder.setPurchase(request.getPurchase());
        purchaseOrderRepository.save(purchaseOrderMapper.toEntity(request));
        return  ResponseEntity.ok("Purchase order has been created successfully!");
    }

    public ResponseEntity<?> updatePurchaseOrder(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePurchaseOrderRequest request
    ){
        var purchaseOrder = purchaseOrderRepository.findById(id).orElse(null);
        if (purchaseOrder == null) {
            return ResponseEntity.notFound().build();
        }
        purchaseOrderMapper.update(request, purchaseOrder);
        purchaseOrderRepository.save(purchaseOrder);
        return ResponseEntity.ok("Purchase order has been updated successfully!");
    }
}
