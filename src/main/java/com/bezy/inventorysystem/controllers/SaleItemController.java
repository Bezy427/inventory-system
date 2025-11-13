package com.bezy.inventorysystem.controllers;

import com.bezy.inventorysystem.dtos.RegisterSaleItemRequest;
import com.bezy.inventorysystem.dtos.SaleItemDto;
import com.bezy.inventorysystem.dtos.UpdateSaleItemRequest;
import com.bezy.inventorysystem.entities.SaleItem;
import com.bezy.inventorysystem.mappers.SaleItemMapper;
import com.bezy.inventorysystem.repositories.SaleItemRepository;
import com.bezy.inventorysystem.services.SaleItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SaleItemController {
    private final SaleItemRepository saleItemRepository;
    private final SaleItemMapper saleItemMapper;
    private final SaleItemService saleItemService;

    public SaleItemController(SaleItemRepository saleItemRepository, SaleItemMapper saleItemMapper, SaleItemService saleItemService) {
        this.saleItemRepository = saleItemRepository;
        this.saleItemMapper = saleItemMapper;
        this.saleItemService = saleItemService;
    }

    @PostMapping("/sale-item")
    public ResponseEntity<?> createSaleItem(
            @RequestBody RegisterSaleItemRequest request
    ){
        saleItemService.createSaleItem(request);
        return ResponseEntity.ok("Sale item has been created!");
    }

    @PutMapping("/sale-item/{id}")
    public ResponseEntity<?> updateSaleItemById(
            @PathVariable Long id,
            @RequestBody UpdateSaleItemRequest request
    ){
        saleItemService.updateSaleItem(id, request);
        return ResponseEntity.ok("Sale item has been updated!");
    }

    @GetMapping("/sale-item")
    public Iterable<SaleItemDto> getAllSaleItem(){
        return saleItemRepository.findAll()
                .stream()
                .map(saleItemMapper::toDto)
                .toList();
    }

    @GetMapping("/sale-item/{id}")
    public ResponseEntity<?> getSaleItemById(
            @PathVariable Long id
    ){
        var salItem = saleItemRepository.findById(id).orElse(null);
        if (salItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(saleItemMapper.toDto(salItem));
    }

    @DeleteMapping("/sale-item/{id}")
    public ResponseEntity<?> deleteSaleItemById(
            @PathVariable Long id
    ){
        saleItemRepository.deleteById(id);
        return ResponseEntity.ok("Sale item has been deleted!");
    }
}
