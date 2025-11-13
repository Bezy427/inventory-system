package com.bezy.inventorysystem.services;

import com.bezy.inventorysystem.dtos.PurchaseMapper;
import com.bezy.inventorysystem.dtos.RegisterPurchaseRequest;
import com.bezy.inventorysystem.dtos.UpdatePurchaseOrderRequest;
import com.bezy.inventorysystem.dtos.UpdatePurchaseRequest;
import com.bezy.inventorysystem.entities.Purchase;
import com.bezy.inventorysystem.entities.PurchaseOrder;
import com.bezy.inventorysystem.repositories.PurchaseRepository;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PurchaseService {
    private PurchaseRepository purchaseRepository;
    private PurchaseMapper purchaseMapper;

    public ResponseEntity<?> createPurchase(
            @RequestBody RegisterPurchaseRequest request
    ){
        Purchase purchase = new Purchase();
        purchase.setId(request.getId());
        purchase.setSupplier(request.getSupplier());
        purchase.setTotalCost(request.getTotalCost());
        purchase.setPurchaseDate(request.getPurchaseDate());
        purchase.setItems(request.getItems());
        purchaseRepository.save(purchase);
        return  ResponseEntity.ok("Purchase has been created successfully!");
    }

    public ResponseEntity<?> updatePurchase(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePurchaseRequest request
    ){
        var purchase = purchaseRepository.findById(id).orElse(null);
        if(purchase == null){
            return ResponseEntity.notFound().build();
        }
        purchaseMapper.update(request, purchase);
        purchaseRepository.save(purchase);
        return   ResponseEntity.ok("Purchase has been updated successfully!");
    }
}
