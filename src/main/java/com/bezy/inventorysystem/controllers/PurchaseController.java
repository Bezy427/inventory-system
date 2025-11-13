package com.bezy.inventorysystem.controllers;

import com.bezy.inventorysystem.dtos.PurchaseDto;
import com.bezy.inventorysystem.dtos.PurchaseMapper;
import com.bezy.inventorysystem.dtos.RegisterPurchaseRequest;
import com.bezy.inventorysystem.dtos.UpdatePurchaseRequest;
import com.bezy.inventorysystem.repositories.PurchaseRepository;
import com.bezy.inventorysystem.services.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PurchaseController {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseRepository purchaseRepository, PurchaseMapper purchaseMapper, PurchaseService purchaseService) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseMapper = purchaseMapper;
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchases")
    public ResponseEntity<?> createPurchase(
           @Valid @RequestBody RegisterPurchaseRequest request
    ){
        purchaseService.createPurchase(request);
        return ResponseEntity.ok("Purchase has made!");
    }

    @PutMapping("/purchases/{id}")
    public ResponseEntity<?> updatePurchaseById(
            @Valid @PathVariable Long id,
            @RequestBody UpdatePurchaseRequest request
    ){
        purchaseService.updatePurchase(id, request);
        return ResponseEntity.ok("Purchase has been updated!");
    }

    @GetMapping("/purchases")
    public Iterable<PurchaseDto> getAllPurchases(){
        return purchaseRepository.findAll()
                .stream()
                .map(purchaseMapper::toDto)
                .toList();
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<PurchaseDto> getPurchaseById(
            @PathVariable Long id
    ){
        var supplier = purchaseRepository.findById(id).orElse(null);
        if(supplier == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(purchaseMapper.toDto(supplier));
    }

    @DeleteMapping("/purchases/{id}")
    public ResponseEntity<?> deletePurchaseById(
            @PathVariable Long id
    ){
        var supplier = purchaseRepository.findById(id).orElse(null);
        if(supplier == null){
            return ResponseEntity.notFound().build();
        }
        purchaseRepository.deleteById(id);
        return ResponseEntity.ok("Purchase has been deleted!");
    }
}
