package com.bezy.inventorysystem.controllers;

import com.bezy.inventorysystem.dtos.RegisterSupplierRequest;
import com.bezy.inventorysystem.dtos.SupplierDto;
import com.bezy.inventorysystem.mappers.SupplierMapper;
import com.bezy.inventorysystem.repositories.SupplierRepository;
import com.bezy.inventorysystem.repositories.UpdateSupplierRequest;
import com.bezy.inventorysystem.services.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SupplierController {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final SupplierService supplierService;

    public SupplierController(SupplierRepository supplierRepository, SupplierMapper supplierMapper, SupplierService supplierService) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
        this.supplierService = supplierService;
    }

    @PostMapping("/suppliers")
    public ResponseEntity<?> createSupplier(
            @RequestBody RegisterSupplierRequest request
    ){
        supplierService.createSupplier(request);
        return ResponseEntity.ok("Supplier has been created successfully!");
    }

    @PutMapping("/suppliers/{id}")
    public ResponseEntity<?> updateSupplierById(
            @PathVariable Long id,
            @RequestBody UpdateSupplierRequest request
    ){
        supplierService.updateSupplier(id, request);
        return ResponseEntity.ok("Supplier has been updated successfully!");
    }

    @GetMapping("/suppliers")
    public Iterable<SupplierDto> getAllSuppliers(){
        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toDto)
                .toList();
    }

    @GetMapping("/suppliers/{id}")
    public ResponseEntity<SupplierDto> getSupplierById(
            @PathVariable Long id
    ){
        var supplier = supplierRepository.findById(id).orElse(null);
        if(supplier == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(supplierMapper.toDto(supplier));
    }

    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<?> deleteSupplierById(
            @PathVariable Long id
    ){
        var supplier = supplierRepository.findById(id).orElse(null);
        if(supplier == null){
            return ResponseEntity.notFound().build();
        }
        supplierRepository.delete(supplier);
        return ResponseEntity.ok("Supplier has been deleted successfully!");
    }
}
