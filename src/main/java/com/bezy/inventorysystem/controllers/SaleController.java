package com.bezy.inventorysystem.controllers;

import com.bezy.inventorysystem.dtos.RegisterSaleRequest;
import com.bezy.inventorysystem.dtos.SaleDto;
import com.bezy.inventorysystem.dtos.UpdateSaleRequest;
import com.bezy.inventorysystem.mappers.SaleMapper;
import com.bezy.inventorysystem.repositories.SaleRepository;
import com.bezy.inventorysystem.services.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SaleController {
    private final SaleRepository saleRepository;
    private final SaleService saleService;
    private final SaleMapper saleMapper;

    public SaleController(SaleRepository saleRepository, SaleService saleService, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.saleService = saleService;
        this.saleMapper = saleMapper;
    }

    @PostMapping("/sales")
    public ResponseEntity<?> createSale(
           @Valid @RequestBody RegisterSaleRequest request
    ){
        saleService.createSale(request);
        return ResponseEntity.ok("Sale has been made!");
    }

    @PutMapping("/sales/{id}")
    public ResponseEntity<?> updateSaleById(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSaleRequest request
    ){
        saleService.updateSale(id, request);
        return ResponseEntity.ok("Sale has been updated!");
    }

    @GetMapping("/sales")
    public Iterable<SaleDto> getAllSales(){
        return saleRepository.findAll()
                .stream()
                .map(sale -> saleMapper.toDto(sale))
                .toList();
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<SaleDto> getSaleById(
            @PathVariable Long id
    ){
        var sale = saleRepository.findById(id).orElse(null);
        if (sale == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(saleMapper.toDto(sale));
    }

    @DeleteMapping("/sales/{id}")
    public ResponseEntity<?> deleteSaleById(
            @PathVariable Long id
    ){
        saleRepository.deleteById(id);
        return ResponseEntity.ok("Sale has been deleted!");
    }
}
